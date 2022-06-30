package com.project.pettrip.config.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // Para informar que a classe é de configuração.
public class ModelMapperConfig {

    @Bean // indica que o método inicializa, configura um Bean do tipo ModelMapper
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
