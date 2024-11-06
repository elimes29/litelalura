package com.alura.literalura.modelo;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatoLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatoPersona> autor,
        @JsonAlias("languages") List<String> idioma,
        @JsonAlias("download_count") Long numeroDeDescargas
) {
}
