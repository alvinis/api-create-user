package com.jencys.apicreateuser.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "regex")
@Getter
@Setter
public class RegexProperties {
    private String password;
    private String email;
}
