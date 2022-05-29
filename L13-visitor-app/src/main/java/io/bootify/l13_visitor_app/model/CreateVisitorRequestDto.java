package io.bootify.l13_visitor_app.model;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
public class CreateVisitorRequestDto {


    @NotNull
    @Size(max = 255)
    private String line1;

    @Size(max = 255)
    private String line2;

    @NotNull
    @Size(max = 6)
    private String pincode;

    @Size(max = 255)
    private String city;

    @Size(max = 255)
    private String state;

    @Size(max = 255)
    private String country;



    @NotNull
    @Size(max = 255)
    private String name;

    @NotNull
    @Size(max = 255)
    private String email;

    @NotNull
    @Size(max = 255)
    private String phone;

    @NotNull
    @Size(max = 255)
    private String idNumber;

}
