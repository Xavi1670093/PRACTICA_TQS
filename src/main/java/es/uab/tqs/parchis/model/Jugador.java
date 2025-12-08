package es.uab.tqs.parchis.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Jugador{
    private final String nombre;
    private Ficha.ColorFicha color;
    private final List<Ficha> fichas;
    private boolean movimientoHecho;

    public Jugador(String nombre, Ficha.ColorFicha color ) {
        //PRECONDICIONES
        assert nombre != null: "nombre es null";
        assert !nombre.isBlank() : "El nombre está vacío";
        assert color != null : "Color es null";

        this.nombre = nombre;
        this.color = color;
        this.fichas = new ArrayList<>();

        //POSTCONDICIONES
        assert this.nombre.equals(nombre) : "nombre no inicializado correctamente";
        assert this.color == color : "color no inicializado correctamente";
        assert this.fichas.isEmpty() : "lista de fichas debería empezar vacía";
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
    
    public void eliminarFicha(Ficha ficha) {
        fichas.remove(ficha);
    }

    public void añadirFicha(Ficha ficha){
        //PRECONDICIONES
        assert ficha != null : "ficha es null";
        if (ficha.getColor() != this.color)
            throw new IllegalArgumentException("La ficha no coincide con el color del jugador");
        
        fichas.add(ficha);
        
        //POSTCONDICIÓN
        assert fichas.contains(ficha) : "Ficha no añadidad correctamente";
    }

    public boolean jugar(int numDado, Tablero tablero) {
        //PRECONDICIONES
        assert numDado >= 1 && numDado <= 6 : "valor de dado inválido";
        assert tablero != null : "tablero es null";
        assert !fichas.isEmpty() : "jugador sin fichas no puede jugar";

        List<Ficha> fichasMovibles = new ArrayList<>();

        // --- Construir lista de fichas realmente movibles ---
        for (Ficha ficha : fichas) {

            if(ficha.getPosicion() == null){
                continue;
            }
            int[] pos = tablero.obtenerIndice(ficha.getPosicion().getNumero());
            if (pos != null) {
                Ficha fichaEnTablero = tablero.getFicha(pos[0], pos[1]);
                if (fichaEnTablero.getColor() == this.color && tablero.movimientPosible(fichaEnTablero, numDado)&& fichaEnTablero.getPosicion() != null) {
                    fichasMovibles.add(fichaEnTablero);
                }
            }
        }

        if (fichasMovibles.isEmpty()) {
            System.out.println(nombre + ", no hay fichas que puedas mover con " + numDado);
            return false;
        }

        Scanner scanner = new Scanner(System.in);
        int fichaEscogida = -1;

        // --- Bucle para elegir ficha ---
        while (fichaEscogida < 1 || fichaEscogida > fichasMovibles.size()) {
            System.out.println(nombre + ", elige una ficha para mover con dado " + numDado + ":");
            for (int i = 0; i < fichasMovibles.size(); i++) {
                Ficha f = fichasMovibles.get(i);
                if (f.getPosicion().getNumero() < 0) System.out.println((i + 1) + ": Ficha en posición inicial");
                else if (f.getPosicion().getNumero() > 68) System.out.println((i + 1) + ": Ficha en posiciones finales");
                else System.out.println((i + 1) + ": Ficha en posición " + f.getPosicion().getNumero() + (f.isBarrera() ? " (barrera)" : ""));
            }

            System.out.print("Introduce el número de la ficha a mover: ");
            if (scanner.hasNextInt()) {
                fichaEscogida = scanner.nextInt();
            } else {
                scanner.next();
            }
        }

        // --- Marcar movimiento hecho ---
        movimientoHecho = true;
        Ficha fichaSeleccionada = fichasMovibles.get(fichaEscogida - 1);

        // --- Mover la ficha real que está en el tablero ---
        tablero.mouFicha(fichaSeleccionada, numDado);
        if (fichaSeleccionada.getPosicion().getNumero() == 999) {
            System.out.println("Ficha movida a la meta y retirada del tablero.");
        } else {
        System.out.println("Ficha movida a posición " + fichaSeleccionada.getPosicion().getNumero());
        }
        //POSTCONDICIONES
        assert movimientoHecho : "movimientoHecho debería haberse marcado como true";
        assert fichaSeleccionada.getPosicion() != null : "la posición de la ficha no puede ser null después de mover";
       
        return true;
    }




    public boolean haGanado() {
        //PRECONDICIÓN
        assert !fichas.isEmpty() : "Jugador sin fichas no puede ganar";

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

        //POSTCONDICIÓN
        assert true : "Todas las fichas deben estar en la meta";

        return true;
    }
}