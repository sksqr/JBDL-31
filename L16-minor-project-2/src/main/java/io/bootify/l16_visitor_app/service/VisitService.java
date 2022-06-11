package io.bootify.l16_visitor_app.service;

import io.bootify.l16_visitor_app.domain.Flat;
import io.bootify.l16_visitor_app.domain.User;
import io.bootify.l16_visitor_app.domain.Visit;
import io.bootify.l16_visitor_app.domain.Visitor;
import io.bootify.l16_visitor_app.model.VisitDTO;
import io.bootify.l16_visitor_app.model.VisitStatus;
import io.bootify.l16_visitor_app.repos.FlatRepository;
import io.bootify.l16_visitor_app.repos.UserRepository;
import io.bootify.l16_visitor_app.repos.VisitRepository;
import io.bootify.l16_visitor_app.repos.VisitorRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VisitService {

    private static Logger LOGGER = LoggerFactory.getLogger(VisitService.class);

    @Autowired
    private FlatRepository flatRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    private String pendingVisitPrefix= "Pending_Flat_";

    private final VisitRepository visitRepository;
    private final VisitorRepository visitorRepository;

    public VisitService(final VisitRepository visitRepository,
            final VisitorRepository visitorRepository) {
        this.visitRepository = visitRepository;
        this.visitorRepository = visitorRepository;
    }

    public List<VisitDTO> findAll() {
        return visitRepository.findAll()
                .stream()
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .collect(Collectors.toList());
    }

    public List<VisitDTO> getPendingVisits(Long userId){
        LOGGER.info("Fetching all pending visits for user:{}",userId);
        User user = userRepository.findById(userId).get();
        Flat flat = user.getFlat();
        String key = pendingVisitPrefix+flat.getId();
        List<VisitDTO> visitDTOList = (List<VisitDTO>) redisTemplate.opsForValue().get(key);
        if(visitDTOList == null){
         visitDTOList = visitRepository.findByFlatAndStatus(flat,VisitStatus.PENDING)
                    .stream()
                    .map(visit -> mapToDTO(visit, new VisitDTO()))
                    .collect(Collectors.toList());
         redisTemplate.opsForValue().set(key,visitDTOList);
        }
        return visitDTOList;
    }


    public VisitDTO get(final Long id) {
        return visitRepository.findById(id)
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VisitDTO visitDTO) {
        visitDTO.setStatus(VisitStatus.PENDING);
        final Visit visit = new Visit();
        mapToEntity(visitDTO, visit);
        return visitRepository.save(visit).getId();
    }

    public void markEntry(Long visitId){
        Visit visit = visitRepository.findById(visitId).get();
        if(visit!=null && visit.getStatus().equals(VisitStatus.APPROVED)) {
            visit.setInTime(LocalDateTime.now());
        }
        else {
          throw   new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is not updated");
        }
    }

    @Transactional
    public void updateVisit(Long visitId,Long userId, VisitStatus status){
        LOGGER.info("Updating visit {} status to {}",visitId,status);
        Visit visit = visitRepository.findById(visitId).get();
        Flat flat = visit.getFlat();
        User user = userRepository.findById(userId).get();
        Flat userFlat =user.getFlat();
        if(flat == userFlat && visit.getStatus().equals(VisitStatus.PENDING)){
            visit.setStatus(status);
            visitRepository.save(visit);
            String key = pendingVisitPrefix+flat.getId();
            redisTemplate.delete(key);
        }
        else{
            LOGGER.error("Invalid update visit request by user {} for visit {}",userId,visitId);
            throw   new ResponseStatusException(HttpStatus.BAD_REQUEST, "Flat is not mapped OR Status is not PENDING");
        }

    }



    public void markExit(Long visitId){
        Visit visit = visitRepository.findById(visitId).get();
        if(visit!=null && visit.getStatus().equals(VisitStatus.APPROVED)) {
            visit.setOutTime(LocalDateTime.now());
            visit.setStatus(VisitStatus.COMPLETED);
        }
        else {
          throw   new ResponseStatusException(HttpStatus.BAD_REQUEST, "Status is not updated");
        }
    }


    public void update(final Long id, final VisitDTO visitDTO) {
        final Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(visitDTO, visit);
        visitRepository.save(visit);
    }


    public void delete(final Long id) {
        visitRepository.deleteById(id);
    }

    private VisitDTO mapToDTO(final Visit visit, final VisitDTO visitDTO) {
        visitDTO.setId(visit.getId());
        visitDTO.setStatus(visit.getStatus());
        visitDTO.setInTime(visit.getInTime());
        visitDTO.setOutTime(visit.getOutTime());
        visitDTO.setPurpose(visit.getPurpose());
        visitDTO.setUrlOfImage(visit.getUrlOfImage());
        visitDTO.setNoOfPeople(visit.getNoOfPeople());
        visitDTO.setVisitor(visit.getVisitor() == null ? null : visit.getVisitor().getId());
        visitDTO.setFlatId(visit.getFlat().getId());
        return visitDTO;
    }

    private Visit mapToEntity(final VisitDTO visitDTO, final Visit visit) {
        visit.setStatus(visitDTO.getStatus());
        visit.setInTime(visitDTO.getInTime());
        visit.setOutTime(visitDTO.getOutTime());
        visit.setPurpose(visitDTO.getPurpose());
        visit.setUrlOfImage(visitDTO.getUrlOfImage());
        visit.setNoOfPeople(visitDTO.getNoOfPeople());
        Flat flat = flatRepository.findById(visitDTO.getFlatId()).get();
        visit.setFlat(flat);
        if (visitDTO.getVisitor() != null && (visit.getVisitor() == null || !visit.getVisitor().getId().equals(visitDTO.getVisitor()))) {
            final Visitor visitor = visitorRepository.findById(visitDTO.getVisitor())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "visitor not found"));
            visit.setVisitor(visitor);
        }
        return visit;
    }

}
