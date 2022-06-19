package com.gfg.userservice.request;

import lombok.Data;

@Data
public class UserCreationRequestDTO {

    private String email;
    private String phone;
    private String name;
    private String kycId;

}
