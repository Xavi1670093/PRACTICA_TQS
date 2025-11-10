package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class FichaTest {
    @Test
    void enterBoard(){
        ficha f = new ficha();
        f.enterBoard(0);
        assertEquals(0, f.getPosition());
    }
}
