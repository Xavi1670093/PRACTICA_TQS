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
            System.out.println("\nIntroduce la fila de la ficha a mover (0 para salir): ");
            int fila = sc.nextInt();
            System.out.println("Introduce la columna de la ficha a mover (0 para salir): ");
            int columna = sc.nextInt();

            if (fila == 0 && columna == 0) {
                System.out.println("Saliendo del programa...");
                break;
            }

            // Obtener ficha en coordenadas
            Ficha ficha = tablero.getFicha(fila, columna);

            if (ficha == null) {
                System.out.println("No hay ninguna ficha en esa posición.");
                continue;
            }

            System.out.println("Introduce el número del dado: ");
            int dado = sc.nextInt();

            // Verificamos el movimiento
            if (!tablero.movimientPosible(ficha, dado)) {
                System.out.println("Movimiento no posible para esa ficha.");
                continue;
            }

            // Ejecutamos el movimiento
            tablero.mouFicha(ficha, dado);

            // Mostrar tablero
            System.out.println("\nTablero actualizado:");
            tablero.mostrar();
        }

        sc.close();
    }
}
