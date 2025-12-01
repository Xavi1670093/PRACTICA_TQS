package es.uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Jugador{
    private final string nombre;
    private Ficha.ColorFicha color;
    private List<Ficha> fichas;
    private final List<Ficha> fichas;

    public Jugador(String nombre, Ficha.ColorFicha color ) {
        Objects.requireNonNull(nombre, "El nombre del jugador no puede ser null");
        assert !nombre.isBlank() : "El nombre del jugador no puede estar vacío";

        this.nombre = nombre;
        this.color = color;
        this.fichas = new ArrayList<>();
 
        for (int i = 0; i < 4; i++) {
            fichas.add(new Ficha(this));
        }

        assert fichas.size() == 4 : "Un jugador debe tener exactamente 4 fichas";
    }

    public String getNombre(){
        return nombre;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }

    public boolean jugar(int numDado, Tablero tablero){
        
    }
}