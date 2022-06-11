package io.bootify.l16_visitor_app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class AddressDTO {

    private Long id;

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

}
