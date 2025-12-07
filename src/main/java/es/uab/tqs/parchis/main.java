package es.uab.tqs.parchis;

import es.uab.tqs.parchis.model.Tablero;
import es.uab.tqs.parchis.model.Jugador;
import es.uab.tqs.parchis.model.Juego;
import es.uab.tqs.parchis.model.Ficha;
import es.uab.tqs.parchis.model.Dado;
import es.uab.tqs.parchis.view.JuegoViewSwing;
import es.uab.tqs.parchis.controller.juegoController;

import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.print("\033[H\033[2J");
        System.out.flush();
        Tablero tablero = new Tablero();
        tablero.inicializa();

        Jugador j1 = new Jugador("Leidy", Ficha.ColorFicha.COLOR_ROJO);
        Jugador j2 = new Jugador("Xavi", Ficha.ColorFicha.COLOR_AZUL);

        for (Ficha f : tablero.getFichasPorColor(Ficha.ColorFicha.COLOR_ROJO)) {
            j1.añadirFicha(f);
        }

        for (Ficha f : tablero.getFichasPorColor(Ficha.ColorFicha.COLOR_AZUL)) {
            j2.añadirFicha(f);
        }

        Dado dado = new Dado();
        JuegoViewSwing vista = new JuegoViewSwing(tablero);

        Juego juego = new Juego(List.of(j1, j2), tablero, dado);
        juegoController controller = new juegoController(juego, vista);

        controller.iniciarPartida();

        Scanner scanner = new Scanner(System.in);

        // --- BUCLE PRINCIPAL DEL JUEGO ---
        while (!juego.isTerminado()) {

            System.out.println("\nPulsa ENTER para jugar el siguiente turno...");
            scanner.nextLine(); // el usuario pulsa ENTER

            System.out.print("\033[H\033[2J");
            System.out.flush();
            
            controller.jugarTurno();
            
        }

        System.out.println("Juego terminado. Ganador: " + juego.getGanador().getNombre());
    }
}
