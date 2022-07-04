package com.project.pettrip.config.modelmapper;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Classe de configuração do Modelmapper.
 */
@Configuration
public class ModelMapperConfig {

    /**
     * Método de configuração para criação de nova instância do Modelmapper.
     *
     * @return uma instância de Modelmapper.
     */
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
