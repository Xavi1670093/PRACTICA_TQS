package es.uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


public class Jugador{
    private final String nombre;
    private Ficha.ColorFicha color;
    private final List<Ficha> fichas;
    private boolean movimientoHecho;

    public Jugador(String nombre, Ficha.ColorFicha color ) {
        Objects.requireNonNull(nombre, "El nombre del jugador no puede ser null");
        assert !nombre.isBlank() : "El nombre del jugador no puede estar vacío";

        this.nombre = nombre;
        this.color = color;
        this.fichas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public Ficha.ColorFicha getColor(){
        return color;
    }

    public List<Ficha> getFichas() {
        return fichas;
    }
    
    public boolean getMovimientoHecho() {
        return movimientoHecho;
    }

    public void setMovimientoHecho(boolean movimientoHecho) {
        this.movimientoHecho = movimientoHecho;
    }
    
    public void añadirFicha(Ficha ficha){
        if(ficha.getColor() != this.color)
            throw new IllegalArgumentException("La ficha no coincide con el color del jugador");
        fichas.add(ficha);
    }

    public boolean jugar(int numDado, Tablero tablero){
        List<Ficha> fichasMovibles = new ArrayList<>();
        for (Ficha ficha : fichas) {
            if (ficha.getColor() == this.color && !ficha.isBarrera() && tablero.movimientPosible(ficha, numDado)) {
                fichasMovibles.add(ficha);
            }
        }

        if (fichasMovibles.isEmpty()) {
            System.out.println(nombre + ", no hay fichas que puedas mover con " + numDado);
            return false;
        } 

        Scanner scanner = new Scanner(System.in);
        int fichaEscogida = -1;

        // Bucle hasta que el jugador elija una ficha válida
        while (fichaEscogida < 1 || fichaEscogida > fichasMovibles.size()) {
            System.out.println(nombre + ", elige una ficha para mover con dado " + numDado + ":");
            for (int i = 0; i < fichasMovibles.size(); i++) {
                Ficha f = fichasMovibles.get(i);
                System.out.println((i + 1) + ": Ficha en posición " + f.getPosicion().getNumero());
            }

            System.out.print("Introduce el número de la ficha a mover: ");
            if (scanner.hasNextInt()) {
                fichaEscogida = scanner.nextInt();
            } else {
                scanner.next(); // descartar entrada no válida
            }
            
        }           
        movimientoHecho = true;
        Ficha fichaSeleccionada = fichasMovibles.get(fichaEscogida - 1);
        tablero.mouFicha(fichaSeleccionada, numDado);
        System.out.println("Ficha movida a posición " + fichaSeleccionada.getPosicion().getNumero());
        return true;
    }

    public boolean haGanado() {
        int meta;
        switch(color) {
            case COLOR_ROJO -> meta = 84;
            case COLOR_AZUL -> meta = 76;
            case COLOR_VERDE -> meta = 92;
            case COLOR_AMARILLO -> meta = 100;
            default -> throw new IllegalStateException("Color no válido");
        }
        for (Ficha f : fichas) {
            if (f.getPosicion().getNumero() != meta) {
                return false;
            }
        }
        return true;
    }
}