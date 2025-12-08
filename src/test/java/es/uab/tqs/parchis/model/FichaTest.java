package es.uab.tqs.parchis.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Test unitario para la clase Ficha.
 * Verifica la correcta inicialización y el comportamiento básico de getters y setters.
 */
class FichaTest {

    private Ficha fichaAmarilla;
    private Ficha fichaRoja;

    @BeforeEach
    void setUp() {
        // Ficha ocupada de color amarillo
        fichaAmarilla = new Ficha(Ficha.ColorFicha.COLOR_AMARILLO, Ficha.TipoFicha.TIPO_OCUPADO, null, false);
        // Ficha vacía de color rojo
        fichaRoja = new Ficha(Ficha.ColorFicha.COLOR_ROJO, Ficha.TipoFicha.TIPO_EMPTY, null, false);
    }

    @Test
    void testConstructorInicializaCorrectamente() {
        // Verificar que el constructor asigna correctamente los valores iniciales
        assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, fichaAmarilla.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, fichaAmarilla.getTipo());
        assertNull(fichaAmarilla.getPosicion());

        assertEquals(Ficha.ColorFicha.COLOR_ROJO, fichaRoja.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_EMPTY, fichaRoja.getTipo());
        assertNull(fichaRoja.getPosicion());
    }

    @Test
    void testSetTipoCambiaTipoFicha() {
        // Cambiar el tipo de ficha y verificar que se actualiza correctamente
        fichaAmarilla.setTipo(Ficha.TipoFicha.TIPO_EMPTY);
        assertEquals(Ficha.TipoFicha.TIPO_EMPTY, fichaAmarilla.getTipo());
    }

    @Test
    void testSetColorCambiaColorFicha() {
        // Cambiar el color de ficha y verificar que se actualiza correctamente
        fichaRoja.setColor(Ficha.ColorFicha.COLOR_VERDE);
        assertEquals(Ficha.ColorFicha.COLOR_VERDE, fichaRoja.getColor());
    }

    @Test
    void testGettersRetornanValoresEsperados() {
        // Re-verificación de los getters para la ficha amarilla
        assertEquals(Ficha.ColorFicha.COLOR_AMARILLO, fichaAmarilla.getColor());
        assertEquals(Ficha.TipoFicha.TIPO_OCUPADO, fichaAmarilla.getTipo());
        assertNull(fichaAmarilla.getPosicion());
    }

    @Test
    void testConstructorVacioNoLanzaErrores() {
        // Verificar que el constructor vacío inicializa los atributos como null
        Ficha fichaVacia = new Ficha();
        assertNull(fichaVacia.getColor());
        assertNull(fichaVacia.getTipo());
        assertNull(fichaVacia.getPosicion());
    }
}
