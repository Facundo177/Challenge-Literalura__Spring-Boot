package com.alura.literatura.model;

public enum Idioma {

    INGLES("en"),
    ESPANOL("es"),
    FRANCES("fr"),
    ALEMAN("de");

    private String idiomaLibro;

    Idioma(String idiomaLibro){
        this.idiomaLibro = idiomaLibro;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.idiomaLibro.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        System.out.println("No se ha encontrado el idioma: " + text);
        return null;
    }


}
