package com.alura.literalura.modelo;

import jakarta.persistence.*;

import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor", // Nombre de la tabla intermedia
            joinColumns = @JoinColumn(name = "libro_id"), // Clave foránea de Libro
            inverseJoinColumns = @JoinColumn(name = "autor_id") // Clave foránea de Persona
    )
    private List<Persona> autor;
    private String idioma;
    private Long numeroDeDescargas;

    public Libro() {
    }

    public Libro (DatoLibro datoLibro){
        this.titulo = datoLibro.titulo();
        this.autor = datoLibro.autor().stream()
                .map(a -> new Persona(a))
                .collect(Collectors.toList());
        this.idioma = datoLibro.idioma().get(0);
        this.numeroDeDescargas=datoLibro.numeroDeDescargas();
    }

    public Long getNumeroDeDescargas() {
        return numeroDeDescargas;
    }

    public void setNumeroDeDescargas(Long numeroDeDescargas) {
        this.numeroDeDescargas = numeroDeDescargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Persona> getAutor() {
        return autor;
    }

    public void setAutor(List<Persona> autor) {
        this.autor = autor;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", idioma='" + idioma + '\'' +
                ", numeroDeDescargas=" + numeroDeDescargas ;
    }
}
