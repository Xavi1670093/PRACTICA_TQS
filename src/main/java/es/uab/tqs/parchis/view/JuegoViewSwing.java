package es.uab.tqs.parchis.view;

import es.uab.tqs.parchis.model.*;

import javax.swing.*;
import java.awt.*;

public class JuegoViewSwing {

    private final JFrame ventana;
    private final JTextArea areaTexto;

    private final Tablero tablero;
    private final TableroView tableroView;

    public JuegoViewSwing(Tablero tablero) {

        // --- Inicializar el modelo ---
        this.tablero = tablero;
        // --- Ventana ---
        ventana = new JFrame("Parchís");
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventana.setSize(1100, 750);
        ventana.setLayout(new BorderLayout());

        // --- TABLERO GRÁFICO ---
        tableroView = new TableroView(tablero);
        ventana.add(tableroView, BorderLayout.CENTER);

        // --- PANEL DE TEXTO ---
        areaTexto = new JTextArea();
        areaTexto.setEditable(false);
        areaTexto.setFont(new Font("Monospaced", Font.PLAIN, 14));

        JScrollPane scroll = new JScrollPane(areaTexto);
        scroll.setPreferredSize(new Dimension(300, 0));
        ventana.add(scroll, BorderLayout.EAST);

        ventana.setVisible(true);
    }

    // ====== MÉTODOS DE LA VISTA ======

    public void mostrarMensaje(String msg) {
        areaTexto.append(msg + "\n");
    }

    public void clear() {
        areaTexto.setText("");
    }

    public void mostrarEstado(Juego juego) {
        tableroView.actualizar();  // repaint() del tablero gráfico
    }

    public void mostrarFichasPorColor(Ficha.ColorFicha color) {
        mostrarMensaje("Fichas del color " + color);
        for (Ficha f : tablero.getFichasPorColor(color)) {
            mostrarMensaje("  - Posición: " + f.getPosicion().getNumero());
        }
    }

    


    public void mostrarGanador(Jugador ganador) {
        mostrarMensaje("\n=====================================");
        mostrarMensaje("          ¡FIN DE LA PARTIDA!        ");
        mostrarMensaje("=====================================");
        mostrarMensaje("Ganador: " + ganador.getNombre() + " (" + ganador.getColor() + ")");
        mostrarMensaje("¡Felicidades!\n");
        }

    public Tablero getTablero() {
        return tablero;
    }

}
