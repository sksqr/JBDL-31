package io.bootify.l16_visitor_app.rest;

import io.bootify.l16_visitor_app.domain.User;
import io.bootify.l16_visitor_app.model.VisitDTO;
import io.bootify.l16_visitor_app.model.VisitStatus;
import io.bootify.l16_visitor_app.service.VisitService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user-panel")
public class UserPanelController {

    @Autowired
    private VisitService visitService;

    @PostMapping("/approve-visit/{visitId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> approve(@PathVariable final Long visitId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        visitService.updateVisit(visitId, user.getId(), VisitStatus.APPROVED);
        return new ResponseEntity<>("Approved", HttpStatus.OK);
    }

    @PostMapping("/reject-visit/{visitId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> reject(@PathVariable final Long visitId) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        visitService.updateVisit(visitId,user.getId(), VisitStatus.REJECTED);
        return new ResponseEntity<>("Rejected", HttpStatus.OK);
    }


    @GetMapping("/get-pending-visits")
    public ResponseEntity<List<VisitDTO>> getPendingVisits() {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ResponseEntity.ok(visitService.getPendingVisits(user.getId()));
    }
    /*
    user_1: data
    flat_1: data
     */

}
