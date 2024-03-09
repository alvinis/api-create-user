package com.jencys.apicreateuser.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class UserRequestDTO {
    @NotNull @NotEmpty @NotBlank
    private String name;
    @NotNull @NotEmpty @NotBlank
    private String email;
    @NotNull @NotEmpty @NotBlank
    private String password;
    @NotNull
    private List<PhoneDTO> phones;
}
