package com.alura.literatura.principal;

import com.alura.literatura.model.*;
import com.alura.literatura.repository.AutorRepository;
import com.alura.literatura.repository.LibroRepository;
import com.alura.literatura.service.ConsumoAPI;
import com.alura.literatura.service.ConvierteDatos;

import java.util.*;

public class Principal {
    private Scanner teclado = new Scanner(System.in);
    private ConsumoAPI consumoAPI = new ConsumoAPI();
    private final String URL_BASE = "https://gutendex.com/books/?search=";
    private ConvierteDatos conversor = new ConvierteDatos();
    private LibroRepository libroRepository;
    private AutorRepository autorRepository;

    public Principal(LibroRepository libroRepository, AutorRepository autorRepository) {
        this.libroRepository = libroRepository;
        this.autorRepository = autorRepository;
    }


    public void mostrarMenu(){
        var opcion = -1;
        while (opcion != 0) {
            var menu = """
                    ------------------------------------------------
                    1 - Buscar libro por titulo
                    2 - Buscar un autor
                    3 - Top 10 libros de un autor
                    4 - Listar libros registrados
                    5 - Listar autores registrados
                    6 - Listar autores vivos en un determinado año
                    7 - Listar libros por idioma
                                  
                    0 - Salir
                    ------------------------------------------------
                    """;
            System.out.println(menu);
            opcion = -1;
            try{
                opcion = teclado.nextInt();
            } catch (InputMismatchException e){}

            teclado.nextLine();

            switch (opcion) {
                case 1:
                    buscarLibro();
                    break;
                case 2:
                    buscarPorAutor();
                    break;
                case 3:
                    top10DeAutor();
                    break;
                case 4:
                    listarLibros();
                    break;
                case 5:
                    listarAutores();
                    break;
                case 6:
                    listarAutoresEnUnAno();
                    break;
                case 7:
                    listarLibrosPorIdioma();
                    break;
                case 0:
                    System.out.println("Cerrando la aplicación...");
                    break;
                default:
                    System.out.println("Opción inválida");
            }
        }
    }


    private void buscarLibro(){
        System.out.println("Escribe el nombre del libro que desees buscar: ");
        var nombreLibro = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreLibro.replace(" ", "+"));

        DatosRespuestaAPI datosRespuestaAPI = conversor.obtenerDatos(json, DatosRespuestaAPI.class);
        System.out.println(datosRespuestaAPI + "\n");

        Optional<DatosLibro> libroBuscado = datosRespuestaAPI.libros().stream()
                .filter(l -> l.titulo().toUpperCase().contains(nombreLibro.toUpperCase()))
                .findFirst();

        if (libroBuscado.isPresent()){
            var datos = libroBuscado.get();
            System.out.println("Titulo: " + datos.titulo() +
                    "\nIdioma: " + datos.idiomas() +
                    "\nAutor: " + datos.autores().get(0).nombre() +
                    "\nDescargas: " + datos.descargas()
            );


            Libro libro = new Libro(datos);
            Autor autor = new Autor(datos.autores().get(0));

            if (!autorRepository.existsByNombre(autor.getNombre())){
                autorRepository.save(autor);
            } else {
                autor = autorRepository.findByNombre(autor.getNombre()).orElse(null);
            }

            libro.setAutor(autor);

            if (!libroRepository.existsByTitulo(libro.getTitulo())){
                libroRepository.save(libro);
            }

        } else {
            System.out.println("Libro no encontrado");
        }

        System.out.println("\n");
    }


    private void buscarPorAutor(){
        System.out.println("Escribe el nombre del autor que desees buscar: ");
        var nombreAutor = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreAutor.toLowerCase().replace(" ", "+"));

        DatosRespuestaAPI datosRespuestaAPI = conversor.obtenerDatos(json, DatosRespuestaAPI.class);
        System.out.println(datosRespuestaAPI + "\n");

        Optional<DatosLibro> datosLibro = datosRespuestaAPI.libros().stream().findFirst();
        if (datosLibro.isPresent()){
            var datos = datosLibro.get();
            var datosAutor = datos.autores().get(0);

            System.out.println("Nombre: " + datosAutor.nombre() +
            "\nFecha de nacimiento: " + datosAutor.fechaNacimiento() +
            "\nFecha de muerte: " + datosAutor.fechaMuerte());

            Autor autor = new Autor(datosAutor);

            if (!autorRepository.existsByNombre(autor.getNombre())){
                autorRepository.save(autor);
            }

        } else {
            System.out.println("Autor no encontrado");
        }

        System.out.println("\n");
    }


    private void top10DeAutor(){
        System.out.println("Escribe el nombre del autor que desees buscar: ");
        var nombreAutor = teclado.nextLine();
        var json = consumoAPI.obtenerDatos(URL_BASE + nombreAutor.toLowerCase().replace(" ", "+"));

        DatosRespuestaAPI datosRespuestaAPI = conversor.obtenerDatos(json, DatosRespuestaAPI.class);
        System.out.println(datosRespuestaAPI + "\n");

        Optional<DatosLibro> primerResultado = datosRespuestaAPI.libros().stream().findFirst();
        if (primerResultado.isPresent()){
            Optional<DatosAutor> primerAutorEncontrado = primerResultado.get().autores().stream().findFirst();

            if (primerAutorEncontrado.isPresent()){
                var autorEncontrado = primerAutorEncontrado.get();

                System.out.println("Top 10 libros de " + autorEncontrado.nombre() + ": ");

                List<DatosLibro> top10 = datosRespuestaAPI.libros().stream()
                        .filter(l -> l.autores().stream().findFirst().isPresent())
                        .filter(l -> l.autores().stream().findFirst().get().nombre().equals(autorEncontrado.nombre()))
                        .sorted(Comparator.comparing(DatosLibro::descargas).reversed())
                        .limit(10)
                        .toList();

                top10.forEach(l -> System.out.println("- " + l.titulo()));
                top10.forEach(datos -> {
                    Libro libro = new Libro(datos);
                    Autor autor = new Autor(datos.autores().get(0));

                    if (!autorRepository.existsByNombre(autor.getNombre())){
                        autorRepository.save(autor);
                    } else {
                        autor = autorRepository.findByNombre(autor.getNombre()).orElse(null);
                    }

                    libro.setAutor(autor);

                    if (!libroRepository.existsByTitulo(libro.getTitulo())){
                        libroRepository.save(libro);
                    }
                });
            } else {
                System.out.println("No se encontraron datos del autor");
            }
        } else {
            System.out.println("No se encontraron resultados para esa búsqueda");
        }

        System.out.println("\n");
    }


    private void listarLibros(){
        List<Libro> libros = libroRepository.findAll();

        System.out.println("Libros registrados: ");
        libros.forEach(l -> System.out.println("- " + l.getTitulo()));

        System.out.println("\n");
    }


    private void listarAutores(){
        List<Autor> autores = autorRepository.findAll();

        System.out.println("Autores registrados: ");
        autores.forEach(a -> System.out.println("- " + a.getNombre()));

        System.out.println("\n");
    }


    private void listarAutoresEnUnAno(){
        var numero = -1;
        while (numero < 0){
            System.out.println("Ingrese el año: ");
            try{
                numero = teclado.nextInt();
            } catch (InputMismatchException e){
                System.out.println("Invalido");
                numero = -1;
            }
            teclado.nextLine();
        }


        List<Autor> autores = autorRepository.autoresVivosEnUnAno(numero);

        System.out.println("Autores vivos en " + numero + ": ");
        autores.forEach(a -> System.out.println("- " + a.getNombre()));

        System.out.println("\n");
    }


    private void listarLibrosPorIdioma(){
        var menu = """
                    Idiomas disponibles:
                    en - INGLES
                    es - ESPANOL
                    fr - FRANCES
                    de - ALEMAN
                    """;
        System.out.println(menu);

        Idioma idioma;
        var opcion = "";
        while (!(opcion.equalsIgnoreCase("en") ||
                opcion.equalsIgnoreCase("es") ||
                opcion.equalsIgnoreCase("fr") ||
                opcion.equalsIgnoreCase("de"))) {

            System.out.println("Elige el idioma que desea consultar: ");
            opcion = teclado.nextLine();
        }

        idioma = Idioma.fromString(opcion);

        List<Libro> libros = libroRepository.librosPorIdioma(idioma);

        System.out.println("Libros en " + idioma + ": ");
        libros.forEach(l -> System.out.println("- " + l.getTitulo()));

        System.out.println("\n");
    }

}
