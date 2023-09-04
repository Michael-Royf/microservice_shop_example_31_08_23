package com.michael.orderService.config;

import com.michael.orderService.exceptions.decoder.CustomErrorDecoder;
import feign.codec.ErrorDecoder;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public ErrorDecoder errorDecoder (){
        return new CustomErrorDecoder();
    }
}
