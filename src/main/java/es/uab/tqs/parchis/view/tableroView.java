package es.uab.tqs.parchis.view;

import es.uab.tqs.parchis.model.*;

import javax.swing.*;
import java.awt.*;

public class TableroView extends JPanel {

    private final Tablero tablero;
    private static final int TILE = 30; // tamaño casilla

    public TableroView(Tablero tablero) {
        this.tablero = tablero;
        setPreferredSize(new Dimension(19 * TILE, 19 * TILE));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Ficha[][] fichas = tablero.getTablero();
        int[][] nums = tablero.getNumerosTablero();

        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {

                int x = j * TILE;
                int y = i * TILE;

                // --- 1. Pintar fondo normal según zona ---
                Color fondo = obtenerColorZona(i, j);
                g.setColor(fondo);
                g.fillRect(x, y, TILE, TILE);

                // --- 2. PINTAR CASILLAS ESPECIALES SEGÚN NÚMERO ---
                int numero = nums[i][j];

                //CASILLAS AMARILLAS
                if((numero >= 113 && numero <= 120) ||(numero == 5 || numero == 68 || numero == 12) ){
                    g.setColor(Color.YELLOW);
                    g.fillRect(x, y, TILE, TILE);
                }

                //CASILLAS AZULES
                if((numero >= 89 && numero <= 96) ||(numero == 17 || numero == 22 || numero == 29) ){
                    g.setColor(new Color(120, 160, 255));
                    g.fillRect(x, y, TILE, TILE);
                }

                //CASILLAS ROJAS
                if((numero >= 97 && numero <= 104) ||(numero == 34 || numero == 39 || numero == 46) ){
                    g.setColor(new Color(255, 120, 120));
                    g.fillRect(x, y, TILE, TILE);
                }

                //CASILLAS VERDE
                if((numero >= 105 && numero <= 112) ||(numero == 51 || numero == 56 || numero == 63) ){
                    g.setColor(new Color(120, 230, 120));
                    g.fillRect(x, y, TILE, TILE);
                }

                // --- 3. Pintar número si pertenece al circuito ---
                if (numero > 0 && numero <= 68) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.PLAIN, 11));
                    g.drawString(String.valueOf(numero), x + TILE/4, y + TILE/2);
                }

                // --- 4. Pintar ficha si la hay ---
                Ficha f = fichas[i][j];
                if (f.getTipo() == Ficha.TipoFicha.TIPO_OCUPADO) {
                    g.setColor(colorFicha(f.getColor()));
                    g.fillOval(x + 5, y + 5, TILE - 10, TILE - 10);

                    if (f.isBarrera()) {
                        Graphics2D g2 = (Graphics2D) g;
                        g2.setColor(Color.BLACK);
                        g2.setStroke(new BasicStroke(3));
                        g2.drawOval(x + 5, y + 5, TILE - 10, TILE - 10);
                    }
                }

                // --- 5. Borde negro ---
                g.setColor(Color.BLACK);
                g.drawRect(x, y, TILE, TILE);
            }
        }
    }


    // =================================================
    // COLORES DE ZONAS DEL TABLERO
    // =================================================
    private Color obtenerColorZona(int fila, int col) {

        // -----------------------------------
        // ZONA CASA ROJA (arriba izquierda)
        // -----------------------------------
        if (fila >= 1 && fila <= 7 && col >= 1 && col <= 7) return new Color(255, 160, 160);

        // -----------------------------------
        // ZONA CASA AZUL (arriba derecha)
        // -----------------------------------
        if (fila >= 1 && fila <= 7 && col >= 11 && col <= 17) return new Color(160, 200, 255);

        // -----------------------------------
        // ZONA CASA VERDE (abajo izquierda)
        // -----------------------------------
        if (fila >= 11 && fila <= 17 && col >= 1 && col <= 7) return new Color(160, 255, 160);

        // -----------------------------------
        // ZONA CASA AMARILLA (abajo derecha)
        // -----------------------------------
        if (fila >= 11 && fila <= 17 && col >= 11 && col <= 17) return new Color(255, 240, 160);

        // -----------------------------------
        // ZONAS DE ENTRADAS FINALES
        // -----------------------------------
        if (fila >= 0 && fila < 19 && col == 9) return new Color(255, 200, 200);   // camino rojo
        if (fila == 9 && col >= 0 && col < 19) return new Color(200, 200, 255);    // camino azul
        if (fila >= 0 && fila < 19 && col == 9) return new Color(200, 255, 200);   // camino verde
        if (fila == 9 && col >= 0 && col < 19) return new Color(255, 255, 200);    // camino amarillo

        return Color.WHITE;
    }

    // =================================================
    // COLORES DE LAS FICHAS
    // =================================================
    private Color colorFicha(Ficha.ColorFicha c) {
        return switch (c) {
            case COLOR_ROJO -> Color.RED;
            case COLOR_VERDE -> Color.GREEN.darker();
            case COLOR_AZUL -> Color.BLUE;
            case COLOR_AMARILLO -> new Color(255, 215, 0);
            default -> Color.GRAY;
        };
    }

    public void actualizar() {
        repaint();
    }
}
