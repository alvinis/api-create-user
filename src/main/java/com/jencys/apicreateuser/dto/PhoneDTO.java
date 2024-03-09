package com.jencys.apicreateuser.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PhoneDTO {
    @NotNull @NotEmpty @NotBlank
    private String number;
    @NotNull @NotEmpty @NotBlank
    private String cityCode;
    @NotNull @NotEmpty @NotBlank
    private String countryCode;
}
