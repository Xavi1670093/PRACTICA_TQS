package es.uab.tqs.parchis.view;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import es.uab.tqs.parchis.model.Ficha;
import es.uab.tqs.parchis.model.Tablero;

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

        int[][] nums = tablero.getNumerosTablero();

        // --- 1. Pintar casillas ---
        for (int i = 0; i < 19; i++) {
            for (int j = 0; j < 19; j++) {
                int x = j * TILE;
                int y = i * TILE;

                // Fondo
                Color fondo = obtenerColorZona(i, j);
                g.setColor(fondo);
                g.fillRect(x, y, TILE, TILE);

                // Casillas especiales
                int numero = nums[i][j];

                // --- CASILLAS FINALES MÁS FUERTES ---
                if(numero == 104) { // rojo
                    g.setColor(new Color(200, 0, 0));
                    g.fillRect(x, y, TILE, TILE);
                } else if(numero == 96) { // azul
                    g.setColor(new Color(0, 0, 200));
                    g.fillRect(x, y, TILE, TILE);
                } else if(numero == 120) { // amarillo
                    g.setColor(new Color(255, 200, 0));
                    g.fillRect(x, y, TILE, TILE);
                } else if(numero == 112) { // verde
                    g.setColor(new Color(0, 200, 0));
                    g.fillRect(x, y, TILE, TILE);
                }

                // Casillas especiales normales
                if((numero >= 113 && numero <= 120) || (numero == 5 || numero == 68 || numero == 12)){
                    g.setColor(Color.YELLOW);
                    g.fillRect(x, y, TILE, TILE);
                }
                if((numero >= 89 && numero <= 96) ||(numero == 17 || numero == 22 || numero == 29) ){
                    g.setColor(new Color(120, 160, 255));
                    g.fillRect(x, y, TILE, TILE);
                }
                if((numero >= 97 && numero <= 104) ||(numero == 34 || numero == 39 || numero == 46) ){
                    g.setColor(new Color(255, 120, 120));
                    g.fillRect(x, y, TILE, TILE);
                }
                if((numero >= 105 && numero <= 112) ||(numero == 51 || numero == 56 || numero == 63) ){
                    g.setColor(new Color(120, 230, 120));
                    g.fillRect(x, y, TILE, TILE);
                }

                // Casillas finales más fuertes (sobrescribir)
                switch(numero) {
                    case 84+20 -> g.setColor(new Color(200, 0, 0));      // rojo
                    case 76+20 -> g.setColor(new Color(0, 0, 200));      // azul
                    case 100+20 -> g.setColor(new Color(255, 200, 0));   // amarillo
                    case 92+20 -> g.setColor(new Color(0, 200, 0));      // verde
                }
                if(numero == 84+20 || numero == 76+20 || numero == 100+20 || numero == 92+20){
                    g.fillRect(x, y, TILE, TILE);
                }

                // Número
                if (numero > 0 && numero <= 68) {
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Arial", Font.PLAIN, 11));
                    g.drawString(String.valueOf(numero), x + TILE/4, y + TILE/2);
                }

                // Borde
                g.setColor(Color.BLACK);
                g.drawRect(x, y, TILE, TILE);
            }
        }

        // --- 2. Pintar fichas según su posición ---
        for (Ficha.ColorFicha color : Ficha.ColorFicha.values()) {
            if (color == Ficha.ColorFicha.NULL) continue;

            for (Ficha f : tablero.getFichasPorColor(color)) {
                if (f.getTipo() != Ficha.TipoFicha.TIPO_OCUPADO) continue;

                int num = f.getPosicion().getNumero();
                int[] coord = tablero.obtenerIndice(num);
                if (coord == null) continue;

                int x = coord[1] * TILE;
                int y = coord[0] * TILE;

                g.setColor(colorFicha(f.getColor()));
                g.fillOval(x + 5, y + 5, TILE - 10, TILE - 10);

                if (f.isBarrera()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setColor(Color.BLACK);
                    g2.setStroke(new BasicStroke(3));
                    g2.drawOval(x + 5, y + 5, TILE - 10, TILE - 10);
                    g2.dispose();
                }
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
        if (fila >= 0 && fila <= 6 && col >= 0 && col <= 6) return new Color(255, 160, 160);

        // -----------------------------------
        // ZONA CASA AZUL (arriba derecha)
        // -----------------------------------
        if (fila >= 0 && fila <= 6 && col >= 12 && col <= 18) return new Color(160, 200, 255);

        // -----------------------------------
        // ZONA CASA VERDE (abajo izquierda)
        // -----------------------------------
        if (fila >= 12 && fila <= 18 && col >= 0 && col <= 6) return new Color(160, 255, 160);

        // -----------------------------------
        // ZONA CASA AMARILLA (abajo derecha)
        // -----------------------------------
        if (fila >= 12 && fila <= 18 && col >= 12 && col <= 18) return new Color(255, 240, 160);

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
