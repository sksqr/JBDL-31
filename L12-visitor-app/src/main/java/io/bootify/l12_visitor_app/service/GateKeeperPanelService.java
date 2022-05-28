package io.bootify.l12_visitor_app.service;

import io.bootify.l12_visitor_app.domain.Visitor;
import io.bootify.l12_visitor_app.model.AddressDTO;
import io.bootify.l12_visitor_app.model.CreateVisitorRequestDto;
import io.bootify.l12_visitor_app.model.UserDTO;
import io.bootify.l12_visitor_app.model.VisitorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GateKeeperPanelService {

    @Autowired
    private AddressService addressService;

    @Autowired
    private VisitorService visitorService;

    public Long create(final CreateVisitorRequestDto createVisitorRequestDto) {
        AddressDTO addressDTO = new AddressDTO();
        addressDTO.setLine1(createVisitorRequestDto.getLine1());
        addressDTO.setLine2(createVisitorRequestDto.getLine2());
        addressDTO.setPincode(createVisitorRequestDto.getPincode());
        addressDTO.setCity(createVisitorRequestDto.getCity());
        addressDTO.setState(createVisitorRequestDto.getState());

        Long addressId = addressService.create(addressDTO);

        VisitorDTO visitorDTO =  VisitorDTO.builder().name(createVisitorRequestDto.getName())
                .email(createVisitorRequestDto.getEmail())
                .address(addressId)
                .idNumber(createVisitorRequestDto.getIdNumber())
                .phone(createVisitorRequestDto.getPhone()).
                build();

        return  visitorService.create(visitorDTO);
    }

}
