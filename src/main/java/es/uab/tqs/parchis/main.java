package es.uab.tqs.parchis;

import es.uab.tqs.parchis.model.Tablero;

public class Main {
    public static void Main(String[] args) {
       
        // Crear el tablero
        Tablero tablero = new Tablero();

        // Inicializar las fichas
        tablero.inicializa();

        // Mostrar el tablero en consola
        tablero.mostrar();
    }
}