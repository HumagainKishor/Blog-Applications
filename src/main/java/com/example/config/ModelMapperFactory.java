package com.example.config;

import io.micronaut.context.annotation.Bean;
import io.micronaut.context.annotation.Factory;
import org.modelmapper.ModelMapper;

@Factory

public class ModelMapperFactory {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
