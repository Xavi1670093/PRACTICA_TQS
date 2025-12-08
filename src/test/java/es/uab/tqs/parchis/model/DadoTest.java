package es.uab.tqs.parchis.model;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;

/**
 * Test unitario de la clase Dado.
 * Se comprueba que el dado siempre lanza valores válidos entre su mínimo y máximo.
 */
public class DadoTest {

    @Test
    void dadoLanzaValorEntre1y6() {
        Dado dado = new Dado(); // Instancia del dado

        // Ejecutar múltiples lanzamientos para comprobar aleatoriedad y rango
        for (int i = 0; i < 100; i++) {
            int valor = dado.lanzar();

            // Verificar que el valor esté dentro del rango permitido
            assertThat(valor)
                .isBetween(dado.getMin(), dado.getMax());
        }
    }
}