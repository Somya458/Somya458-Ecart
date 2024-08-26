package com.api.admin.app.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
@Setter
@Getter

@Configuration
public class ErrorCodeConfig {

    private final ReloadableResourceBundleMessageSource messageSource;

    public ErrorCodeConfig(ReloadableResourceBundleMessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private Map<String, String> errorCodeMap = new HashMap<>();

    @PostConstruct
    public void init() {
        errorCodeMap.put("1001", messageSource.getMessage("error.1001", null, null));
        errorCodeMap.put("1002", messageSource.getMessage("error.1002", null, null));
        errorCodeMap.put("1003", messageSource.getMessage("error.1003", null, null));
    }

    public String getErrorMessage(String errorCode) {
        return errorCodeMap.getOrDefault(errorCode, "Unknown error");
        
    }
}
