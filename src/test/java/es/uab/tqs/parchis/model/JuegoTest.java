package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
 
public class JuegoTest{

    @Test
    void testAñadirJugador(){
        Juego juego = new Juego();
        Jugador j1 = new Jugador("Alice", Ficha.ColorFicha.COLOR_ROJO);
        Jugador j2 = new Jugador("Rosa", Ficha.ColorFicha.COLOR_VERDE);
        Jugador j3 = new Jugador("Marc", Ficha.ColorFicha.COLOR_AMARILLO);
        Jugador j4 = new Jugador("Alex", Ficha.ColorFicha.COLOR_AZUL);

        juego.añadirJugador(j1);
        juego.añadirJugador(j2);
        juego.añadirJugador(j3);
        juego.añadirJugador(j4);

        //CASO 4 JUGADORES CORRECTAMENTE AÑADIDOS
        assertEquals(4, juego.getJugadores().size());

        //CASO NO SE PUEDE AÑADIR JUGADORES DUPLICADOS
        assertThrows(AssertionError.class, () -> juego.añadirJugador(j1));

        //CASO NO SE PUEDE AÑADRI MÁS DE 4 JUGADORES
        Exception ex = assertThrows(IllegalArgumentException.class, () -> juego.añadirJugador(new Jugador("J5", Ficha.ColorFicha.COLOR_ROJO)));
        assertEquals("No se pueden añadir más de 4 jugadores", ex.getMessage());

    }

}