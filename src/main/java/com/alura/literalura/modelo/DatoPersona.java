package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoPersona(
        @JsonAlias("birth_year") Integer fechaNecimiento,
        @JsonAlias("death_year") Integer fechaMuerte,
        @JsonAlias("name") String nombre
) {
}
