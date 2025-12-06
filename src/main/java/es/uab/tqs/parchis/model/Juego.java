package es.uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;

public class Juego {
    private final List<Jugador> jugadores;
    private final Tablero tablero;
    private final Dado dado;

    public Juego(){
        this.jugadores = new ArrayList<>();
        this.tablero = new Tablero();
        this.dado = new Dado();
        tablero.inicializa();
    }

    public List<Jugador> getJugadores(){
        assert jugadores != null && !jugadores.isEmpty() : "Lista de jugadores vacía";
        return jugadores;
    }


    public void añadirJugador(Jugador jugador){

    }

    
}