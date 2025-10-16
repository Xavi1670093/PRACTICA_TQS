package es.uab.tqs.parchis.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PosicionTest {

    @Test
    void posicionInicializaTest() {
        int nCasilla = 5;
        boolean seguro = true;

        Posicion posicion = new Posicion(nCasilla, seguro);

        assertEquals(nCasilla, posicion.getNumero());
        assertTrue(posicion.esSeguro());
    }

    
}
