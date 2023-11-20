package com.example;

import io.micronaut.context.annotation.Bean;
import io.micronaut.runtime.Micronaut;
import org.modelmapper.ModelMapper;

public class Application {

    public static void main(String[] args) {
        Micronaut.run(Application.class, args);
    }
}