package com.jencys.apicreateuser.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.UUID;

@Builder
@Getter
public class UserResponse {
    private UUID id;
    private Date created;
    private Date modified;
    private Date lastLogin;
    private String token;
    private Boolean isActive;
}
