package com.example.taas.nextupinfrastructure.config;

import com.example.taas.nextupinfrastructure.error.DefaultErrorController;
import com.example.taas.nextupinfrastructure.error.GlobalErrorHandler;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.webmvc.autoconfigure.error.ErrorMvcAutoConfiguration;
import org.springframework.boot.webmvc.error.ErrorAttributes;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;

@AutoConfiguration(before = ErrorMvcAutoConfiguration.class)
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class InfrastructureAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    GlobalErrorHandler globalErrorHandler() { return new GlobalErrorHandler();}

    @Bean
    @ConditionalOnMissingBean(org.springframework.boot.webmvc.error.ErrorController.class)
    DefaultErrorController defaultErrorController(ErrorAttributes errorAttributes) { return new DefaultErrorController(errorAttributes);}

    @Bean
    @Profile("dev")
    @ConditionalOnMissingBean
    WebConfiguration webConfiguration() { return new WebConfiguration(); }
}
