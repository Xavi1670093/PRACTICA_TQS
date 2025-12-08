package es.uab.tqs.parchis;

import es.uab.tqs.parchis.model.Tablero;
import es.uab.tqs.parchis.model.Jugador;
import es.uab.tqs.parchis.model.Juego;
import es.uab.tqs.parchis.model.Ficha;
import es.uab.tqs.parchis.model.Dado;
import es.uab.tqs.parchis.view.JuegoViewSwing;
import es.uab.tqs.parchis.controller.juegoController;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();

        Scanner scanner = new Scanner(System.in);

        // --- Inicializar tablero ---
        Tablero tablero = new Tablero();
        tablero.inicializa();

        // --- Preguntar número de jugadores ---
        int numJugadores;
        do {
            System.out.print("Introduce el número de jugadores (2-4): ");
            numJugadores = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer
        } while (numJugadores < 2 || numJugadores > 4);

        List<Jugador> jugadores = new ArrayList<>();
        List<Ficha.ColorFicha> coloresDisponibles = new ArrayList<>();
        coloresDisponibles.add(Ficha.ColorFicha.COLOR_ROJO);
        coloresDisponibles.add(Ficha.ColorFicha.COLOR_AMARILLO);
        coloresDisponibles.add(Ficha.ColorFicha.COLOR_VERDE);
        coloresDisponibles.add(Ficha.ColorFicha.COLOR_AZUL);

        // --- Pedir datos de cada jugador ---
        for (int i = 0; i < numJugadores; i++) {
            System.out.print("Introduce el nombre del jugador " + (i + 1) + ": ");
            String nombre = scanner.nextLine();

            Ficha.ColorFicha color = null;
            while (color == null) {
                System.out.println("Colores disponibles:");
                for (int j = 0; j < coloresDisponibles.size(); j++) {
                    System.out.println((j + 1) + ": " + coloresDisponibles.get(j));
                }
                System.out.print("Elige el color (1-" + coloresDisponibles.size() + "): ");
                int colorIndex = scanner.nextInt() - 1;
                scanner.nextLine(); // limpiar buffer
                if (colorIndex >= 0 && colorIndex < coloresDisponibles.size()) {
                    color = coloresDisponibles.remove(colorIndex);
                } else {
                    System.out.println("Opción inválida, intenta de nuevo.");
                }
            }

            Jugador jugador = new Jugador(nombre, color);

            // Asignar fichas del tablero al jugador
            for (Ficha f : tablero.getFichasPorColor(color)) {
                jugador.añadirFicha(f);
            }

            jugadores.add(jugador);
        }

        // --- Inicializar juego ---
        Dado dado = new Dado();
        JuegoViewSwing vista = new JuegoViewSwing(tablero);
        Juego juego = new Juego(jugadores, tablero, dado);
        tablero.setJuego(juego);

        juegoController controller = new juegoController(juego, vista);

        controller.iniciarPartida();

        // --- Bucle principal ---
        while (!juego.isTerminado()) {
            System.out.println("\nPulsa ENTER para tirar el dado...");
            scanner.nextLine(); // el usuario pulsa ENTER

            System.out.print("\033[H\033[2J");
            System.out.flush();

            controller.jugarTurno();
        }

        System.out.println("Juego terminado. Ganador: " + juego.getGanador().getNombre());
    }
}
