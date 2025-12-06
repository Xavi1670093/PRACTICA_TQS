package es.uab.tqs.parchis.view;

import es.uab.tqs.parchis.model.*;

import java.util.Scanner;

public class JuegoView {

    private final Scanner scanner = new Scanner(System.in);

    public void mostrarMensaje(String msg) {
        System.out.println(msg);
    }

    public void clear() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public void esperarEnter() {
        System.out.println("Pulsa ENTER para lanzar dado...");
        scanner.nextLine();
    }
    
    public void mostrarEstado(Juego juego) {
        System.out.println("\n================= ESTADO DEL TABLERO =================");
        juego.getTablero().mostrar();
        System.out.println("=======================================================\n");
    }

        public void mostrarDado(int valor) {
            String dado = switch (valor) {
                case 1 ->  
                    """
                    ┌───────┐
                    │       │
                    │   O   │
                    │       │
                    └───────┘
                    """;
                case 2 ->
                    """
                    ┌───────┐
                    │ O     │
                    │       │
                    │     O │
                    └───────┘
                    """;
                case 3 ->
                    """
                    ┌───────┐
                    │ O     │
                    │   O   │
                    │     O │
                    └───────┘
                    """;
                case 4 ->
                    """
                    ┌───────┐
                    │ O   O │
                    │       │
                    │ O   O │
                    └───────┘
                    """;
                case 5 ->
                    """
                    ┌───────┐
                    │ O   O │
                    │   O   │
                    │ O   O │
                    └───────┘
                    """;
                case 6 ->
                    """
                    ┌───────┐
                    │ O   O │
                    │ O   O │
                    │ O   O │
                    └───────┘
                    """;
                default -> "ERROR: dado inválido";
            };

            System.out.println("Dado: " + valor);
            System.out.println(dado);
        }
    
    public void mostrarGanador(Jugador ganador) {
        System.out.println("\n ¡El ganador es " + ganador.getNombre() + " (" + ganador.getColor() + ")! ");
    }
}
