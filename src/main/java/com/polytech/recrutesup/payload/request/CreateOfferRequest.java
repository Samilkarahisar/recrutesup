package com.polytech.recrutesup.payload.request;

import com.polytech.recrutesup.entities.Attachment;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;


@Getter
@Setter
public class CreateOfferRequest {

    @NotNull
    @Size(max = 40)
    private String label;

    @NotNull
    @Size(max = 500)
    private String description;

    @NotNull
    @Size(max = 40)
    private String address;

    @NotNull
    @Size(max = 40)
    private String city;

    @NotNull
    @Size(max = 40)
    private String mailAddress;

    private List<Attachment> attachmentList;

    private Long userId;
}
