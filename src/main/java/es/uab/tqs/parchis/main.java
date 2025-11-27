package es.uab.tqs.parchis;

import es.uab.tqs.parchis.model.Ficha;
import es.uab.tqs.parchis.model.Tablero;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        // Crear e inicializar tablero
        Tablero tablero = new Tablero();
        tablero.inicializa();

        System.out.println("Tablero inicial:");
        tablero.mostrar();

        while (true) {
            System.out.println("\nIntroduce el número de la ficha a mover (0 para salir): ");
            int numFicha = sc.nextInt();

            if (numFicha == 0) {
                System.out.println("Saliendo del programa...");
                break;
            }

            int[] coords = tablero.obtenerIndice(numFicha);
            if (coords == null) {
                System.out.println("Número de ficha inválido.");
                continue;
            }

            Ficha ficha = tablero.getFicha(coords[0], coords[1]);
            if (ficha == null || ficha.getTipo() == Ficha.TipoFicha.TIPO_EMPTY) {
                System.out.println("No hay ninguna ficha en esa casilla.");
                continue;
            }

            System.out.println("Introduce el número del dado: ");
            int dado = sc.nextInt();

            if (!tablero.movimientPosible(ficha, dado)) {
                System.out.println("Movimiento no posible para esa ficha.");
                continue;
            }

            tablero.mouFicha(ficha, dado);

            System.out.println("\nTablero actualizado:");
            tablero.mostrar();
        }

        sc.close();
    }
}
