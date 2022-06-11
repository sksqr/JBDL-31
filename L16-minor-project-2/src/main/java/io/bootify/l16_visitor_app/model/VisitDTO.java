package io.bootify.l16_visitor_app.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class VisitDTO implements Serializable {

    private Long id;

    private VisitStatus status;

    private LocalDateTime inTime;

    private LocalDateTime outTime;

    @Size(max = 255)
    private String purpose;

    @Size(max = 255)
    private String urlOfImage;

    private Integer noOfPeople;

    @NotNull
    private Long flatId;

    @NotNull
    private Long visitor;

}
