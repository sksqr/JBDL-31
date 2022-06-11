package io.bootify.l16_visitor_app.rest;

import io.bootify.l16_visitor_app.model.CreateVisitorRequestDto;
import io.bootify.l16_visitor_app.model.VisitDTO;
import io.bootify.l16_visitor_app.model.VisitorDTO;
import io.bootify.l16_visitor_app.service.GateKeeperPanelService;
import io.bootify.l16_visitor_app.service.VisitService;
import io.bootify.l16_visitor_app.service.VisitorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;

@RestController
@RequestMapping("/api/gatekeeper-panel")
public class GateKeeperPanelController {


    static private Logger LOGGER = LoggerFactory.getLogger(GateKeeperPanelController.class);

    static final String basePath = "/static";
    static final String relativePath = "/vms/";


    @Autowired
    private VisitorService visitorService;

    @Autowired
    private VisitService visitService;

    @Autowired
    private GateKeeperPanelService gateKeeperPanelService;

    @GetMapping("/getVisitorByIdNumber")
    public ResponseEntity<VisitorDTO> getVisitor(@RequestParam final String idNumber) {
        return ResponseEntity.ok(visitorService.getByIdNumber(idNumber));
    }

    @PostMapping("/create-visitor")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVisitor(@RequestBody @Valid final CreateVisitorRequestDto createVisitorRequestDto) {
        return new ResponseEntity<>(gateKeeperPanelService.create(createVisitorRequestDto), HttpStatus.CREATED);
    }


    @PostMapping("/create-visit")
    @ApiResponse(responseCode = "201")
    public ResponseEntity<Long> createVisit(@RequestBody @Valid final VisitDTO visitDTO) {
        return new ResponseEntity<>(visitService.create(visitDTO), HttpStatus.CREATED);
    }


    @PostMapping("/entry/{visitId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> markEntry(@PathVariable final Long visitId ) {
        visitService.markEntry(visitId);
        return new ResponseEntity<>("Updated", HttpStatus.CREATED);
    }

    @PostMapping("/exit/{visitId}")
    @ApiResponse(responseCode = "200")
    public ResponseEntity<String> markExit(@PathVariable final Long visitId ) {
        visitService.markExit(visitId);
        return new ResponseEntity<>("Updated", HttpStatus.CREATED);
    }


    /*
    upload image
     */


    @PostMapping("/image/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {
        String message = "";
        try {
            String path = relativePath+"testfile_"+System.currentTimeMillis()+"_"+file.getOriginalFilename();
            String uploadPath = basePath+path;
            file.transferTo(new File(uploadPath));
            message = "Image URL : " + path;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            LOGGER.error("Exception occurred: {}",e);
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }




}
