package com.project.pettrip;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Classe de ponto de início da aplicação Pet Trip.
 */
@SpringBootApplication
public class PettripApplication {

    /**
     * Ponto de início da aplicação.
     *
     * @param args argumentos de input.
     */
    public static void main(String[] args) {
		SpringApplication.run(PettripApplication.class, args);
	}
}
