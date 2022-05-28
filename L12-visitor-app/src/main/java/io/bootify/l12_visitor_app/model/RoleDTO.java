package io.bootify.l12_visitor_app.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class RoleDTO {

    private Long id;

    @NotNull
    @Size(max = 255)
    private String role;

}
