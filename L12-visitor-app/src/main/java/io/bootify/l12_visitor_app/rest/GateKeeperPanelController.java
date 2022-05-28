package io.bootify.l12_visitor_app.rest;

import io.bootify.l12_visitor_app.model.CreateVisitorRequestDto;
import io.bootify.l12_visitor_app.model.VisitDTO;
import io.bootify.l12_visitor_app.model.VisitorDTO;
import io.bootify.l12_visitor_app.service.GateKeeperPanelService;
import io.bootify.l12_visitor_app.service.VisitService;
import io.bootify.l12_visitor_app.service.VisitorService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/gatekeeper-panel")
public class GateKeeperPanelController {

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



}
