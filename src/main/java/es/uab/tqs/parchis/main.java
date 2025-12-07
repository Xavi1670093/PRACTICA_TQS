package es.uab.tqs.parchis;

import es.uab.tqs.parchis.model.*;
import es.uab.tqs.parchis.view.JuegoViewSwing;
import es.uab.tqs.parchis.controller.juegoController;

import java.util.List;

public class Main {

    public static void main(String[] args) {

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
        JuegoViewSwing vista = new JuegoViewSwing();

        Juego juego = new Juego(List.of(j1, j2), tablero, dado);
        juegoController controller = new juegoController(juego, vista);

        controller.iniciarPartida();
    }
}