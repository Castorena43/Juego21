package com.example.juego21;

public class Persona {
    private String nombre;

    public Persona(String nombre, int numero) {
        this.nombre = nombre;
        this.numero = numero;
    }

    private int numero;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntuacion() {
        return numero;
    }

    public void setPuntuacion(int puntuacion) {
        this.numero = puntuacion;
    }
}
