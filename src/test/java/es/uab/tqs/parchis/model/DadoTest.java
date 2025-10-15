package es.uab.tqs.parchis.model;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class DadoTest {

    @Test
    void dadoLanzaValorEntre1y6() {
        Dado dado = new Dado();

        for (int i = 0; i < 100; i++) {
            int valor = dado.lanzar();
            assertThat(valor).isBetween(dado.getMin(), dado.getMax());
        }
    }
}
