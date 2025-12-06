package es.uab.tqs.parchis.model;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
 
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

    @Test 
    void testPartida(){
        Juego juego = new Juego();

        Jugador j1 = mock(Jugador.class);
        Jugador j2 = mock(Jugador.class);

        juego.añadirJugador(j1);
        juego.añadirJugador(j2);

        when(j1.haGanado()).thenReturn(true); 
        when(j2.haGanado()).thenReturn(false);

        when(j1.jugar(anyInt(), any())).thenReturn(true);
        when(j2.jugar(anyInt(), any())).thenReturn(true);

        // Ejecutar iniciar en un hilo para no bloquear por Scanner
        Thread t = new Thread(juego::partida);
        t.start();
        try {
            t.join(1000); // esperar máximo 1s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        verify(j1, atLeastOnce()).jugar(anyInt(), any());
        verify(j1, atLeastOnce()).haGanado();
    }

    @Test 
    void testPartidaConMenosDeDosJugadores(){
        Juego juego = new Juego();
        Jugador j1 = new Jugador("Alice", Ficha.ColorFicha.COLOR_ROJO);
        juego.añadirJugador(j1);

        Exception ex = assertThrows(IllegalStateException.class, juego::partida);
        assertEquals("Se necessitan al menos 2 jugadores para empezar", ex.getMessage());
    }

}