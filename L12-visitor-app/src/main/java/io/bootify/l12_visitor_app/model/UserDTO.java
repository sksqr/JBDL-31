package io.bootify.l12_visitor_app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.*;


@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String name;

    @Size(max = 255)
    private String email;

    @Size(max = 255)
    private String phone;

    private Long address;

    @NotNull
    private Long roleId;

    private Long flat;

}
