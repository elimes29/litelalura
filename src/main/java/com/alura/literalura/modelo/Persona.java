package com.alura.literalura.modelo;

import jakarta.persistence.*;

import java.util.List;
@Entity
@Table(name = "autores")
public class Persona {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer fechaNecimiento;
    private Integer fechaMuerte;
    @Column(unique = true)
    private String nombre;
    @ManyToMany (mappedBy = "autor")
    private List<Libro> libros;

    public Persona() {
    }

    public Persona(DatoPersona datoPersona) {
        this.fechaNecimiento = datoPersona.fechaNecimiento();
        this.fechaMuerte = datoPersona.fechaMuerte();
        this.nombre = datoPersona.nombre();
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getFechaNecimiento() {
        return fechaNecimiento;
    }

    public void setFechaNecimiento(Integer fechaNecimiento) {
        this.fechaNecimiento = fechaNecimiento;
    }

    public Integer getFechaMuerte() {
        return fechaMuerte;
    }

    public void setFechaMuerte(Integer fechaMuerte) {
        this.fechaMuerte = fechaMuerte;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return
                "id=" + id +
                ", fechaNecimiento=" + fechaNecimiento +
                ", fechaMuerte=" + fechaMuerte +
                ", nombre='" + nombre ;
    }
}
