package com.alura.literalura.principal;

import com.alura.literalura.modelo.DatoLibro;
import com.alura.literalura.modelo.Libro;
import com.alura.literalura.modelo.Persona;
import com.alura.literalura.repositorio.LibroRepositorio;
import com.alura.literalura.repositorio.PersonaRepositorio;
import com.alura.literalura.servicio.ConsumoApi;
import com.alura.literalura.servicio.ConvierteDatos;
import com.alura.literalura.modelo.DatoApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;


import java.util.*;


public class Menu {
    public ConvierteDatos convierteDatos = new ConvierteDatos();
    public ConsumoApi consumoApi = new ConsumoApi();
    public Scanner teclado = new Scanner(System.in);
    private LibroRepositorio repositorioLibro;
    private PersonaRepositorio repositorioPersona;
    private final String URL1 = "https://gutendex.com/books";
    private final String URL2 = "?search=";

    public Menu(LibroRepositorio repositoryLibro, PersonaRepositorio repositoryPersona) {
        this.repositorioLibro = repositoryLibro;
        this.repositorioPersona = repositoryPersona;
    }

    public void mostrarMenu() {
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    **********************************************************
                    1 - Buscar libro por título (lo agrega a la base de datos).
                    2 - Listar libros en base de datos.
                    3 - Listar autores registrados.
                    4 - Listras autores vivios en determinado año.
                    5 - Exhibir cantidad de libros en un determinado idioma.
                    6 - Top 10 de libros más descargados.
                    7 - Estadisticas.
                    
                    0 - Salir
                    **********************************************************
                    """;
            System.out.println(menu);
// Leer la entrada como String
            String entrada = teclado.nextLine();

            try {
                // Intentar convertir la entrada en un número
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Introduzca un número.");
                continue; // Vuelve a mostrar el menú si la entrada no es válida
            }

            switch (opcion) {
                case 1:
                    buscarLibroPorTitulo();
                    break;
                case 2:
                    mostraLibros();
                    break;
                case 3:
                    mostrarAutores();
                    break;
                case 4:
                    mostrarAutoresVivosEnAnhoDeterminado();
                    break;
                case 5:
                    mostrarLibrosPorIdioma();
                    break;
                case 6:
                    mostrarTop10Descarados();
                    break;
                case 7:
                    //mostrarEstadisticas();
                    break;

                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }

    }

    private void buscarLibroPorTitulo() {
        System.out.println("Ingrese el título a buscar");
        String titulo = teclado.nextLine();
        String json = consumoApi.obtenerDatos(URL1 + URL2 + titulo.replace(" ", "+"));

        // Buscando en API
        DatoApi datos = convierteDatos.obtenerDatos(json, DatoApi.class);

        // Si no es encontrado
        if (datos.libros() == null || datos.libros().isEmpty()) {
            System.out.println("El título: \"" + titulo + "\" no fue encontrado.");
            return; // Termina el método aquí si el JSON está vacío o nulo.
        }

        // Tomando el primer libro encontrado
        DatoLibro libroEncontrado = datos.libros().get(0);
        // Convirtiendo de DatoLibro a Libro
        Libro libro = new Libro(libroEncontrado);

        // Guardando los autores y asignando IDs
        List<Persona> autores = new ArrayList<>();
        for (Persona autor : libro.getAutor()) {
            Optional<Persona> autorExistente = repositorioPersona.findByNombre(autor.getNombre());
            if (!autorExistente.isPresent()) {
                try {
                    System.out.println("Guardando a " + autor.getNombre());
                    Persona autorGuardado = repositorioPersona.save(autor); // Guardar autor nuevo
                    autores.add(autorGuardado); // Agregar autor guardado a la lista
                } catch (DataIntegrityViolationException e) {
                    System.out.println("Autor con este nombre ya existe.");
                }
            } else {
                // Si el autor ya está en la base de datos, agrega el existente a la lista
                autores.add(autorExistente.get());
            }
        }

        // Asignar la lista de autores al libro
        libro.setAutor(autores);

        // Confirmando que el libro no exista
        Optional<Libro> libroExistente = repositorioLibro.findByTitulo(libro.getTitulo());
        if (libroExistente.isPresent()) {
            System.out.println("El libro con este título ya existe.");
        } else {
            try {
                repositorioLibro.save(libro); // Guardar el libro
                System.out.println("Libro guardado exitosamente.");
            } catch (DataIntegrityViolationException e) {
                System.out.println("Error al guardar el libro.");
            }
        }
    }

    private void mostraLibros() {
        List<Libro> libros = repositorioLibro.findAll();

        if (libros.size() == 0){
            System.out.println("Aún no hay libros en la base de datos.");
        }else {
            libros.stream()
                    .forEach(System.out::println);
        }

    }
    private void mostrarAutores() {
        List<Persona> autores = repositorioPersona.findAll();
        if (autores.size() == 0){
            System.out.println("Aún no hay autores en la base de datos.");
        }else {
            autores.stream().forEach(System.out::println);
        }
    }

    private void mostrarAutoresVivosEnAnhoDeterminado() {
        System.out.println("Ingrese el año a buscar de autores vivos");
        Integer anho = teclado.nextInt();
        teclado.nextLine();
        List<Persona> autoresVivos = repositorioPersona
                .findByFechaMuerteGreaterThanAndFechaNecimientoLessThan(anho, anho);
        if (autoresVivos.size()>0){
            autoresVivos.stream()
                    .forEach(System.out::println);
        }else {
            System.out.println("No hay autores vivios para esa fecha");
        }

    }

    private void mostrarLibrosPorIdioma() {


        while (true){
            var menu = """
                    **********************************************************
                    Ingrese la letra correspondiente al idioma que desea bucar
                    1 - Inglés.
                    2 - Español.
                    3 - Francés.
                    4 - Portugués.
                    **********************************************************
                    """;
            System.out.println(menu);

            // Leer la entrada como String
            String entrada = teclado.nextLine();
            Integer opcion;
            String idioma = "";
            String idiomaLargo = "";
            try {
                // Intentar convertir la entrada en un número
                opcion = Integer.parseInt(entrada);
            } catch (NumberFormatException e) {
                System.out.println("Opción no válida. Introduzca un número.");
                continue; // Vuelve a mostrar el menú si la entrada no es válida
            }

            if (opcion>0 && opcion<5){
                //Seleccionamos idioma
                switch (opcion) {
                    case 1:
                        idioma="en";
                        idiomaLargo = "inglés";
                        break;
                    case 2:
                        idioma="es";
                        idiomaLargo = "español";
                        break;
                    case 3:
                        idioma="fr";
                        idiomaLargo = "francés";
                        break;
                    case 4:
                        idioma="pt";
                        idiomaLargo = "portugués";
                        break;
                }
                //Hacemos la consulta y mostramos resultados
                List<Libro> librosIdioma = repositorioLibro.findByIdiomaLike(idioma);
                if (librosIdioma.size()>0){
                    System.out.println("Hay " + librosIdioma.size() + " libros del idioma " + idiomaLargo + " y se listan abajo:");
                    librosIdioma.stream()
                            .forEach(System.out::println);
                }else{
                    System.out.println("No hay libros con idioma " + idiomaLargo + ".");
                }
                //Salimos del While
                break;
            }else {
                System.out.println("Opción inválida");
            }

        }
    }

    private void mostrarTop10Descarados() {
        List<Libro> librosTop = repositorioLibro.findTop10ByOrderByNumeroDeDescargasDesc();
        System.out.println("10 libros más descargados");
        librosTop.stream()
                .forEach(System.out::println);
    }

//    private void mostrarEstadisticas() {
//        LongSummaryStatistics estadisticas = new LongSummaryStatistics();
//        List<Libro> libros = repositorioLibro.findAll();
//        libros.stream()
//                .
//    }


}