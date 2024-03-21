package recolecciondatos001;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RecoleccionDatos001 {

    private static final String RUTA_ARCHIVO = "C:\\Users\\shyon\\Desktop\\trabajos 3er semestre\\numeros\\numeros2.txt";

    public static void main(String[] args) throws FileNotFoundException {

        
        Scanner scanner = new Scanner(new File(RUTA_ARCHIVO));

        
        int[] datos = new int[2000]; // Ajustar el tamaño del arreglo según la cantidad de datos
        int n = 0; // Cantidad de datos leídos
        int maximo = Integer.MIN_VALUE;
        int minimo = Integer.MAX_VALUE;

        
        while (scanner.hasNextLine()) {
            String linea = scanner.nextLine();
            int valor = Integer.parseInt(linea);
            datos[n++] = valor;

            // Actualizar máximo y mínimo
            if (valor > maximo) {
                maximo = valor;
            }
            if (valor < minimo) {
                minimo = valor;
            }
        }

        // Calcular el rango, tamaño de intervalo y número de clases
        int rango = maximo - minimo;
        double tamanoIntervaloSinRedondeo = rango / 10.0; // Ajustar el divisor según el número deseado de clases
        int tamanoIntervaloRedondeado = (int) Math.ceil(tamanoIntervaloSinRedondeo);
        int numeroClases = (rango / tamanoIntervaloRedondeado) + 1;

        // Mostrar resultados
        System.out.println("1. EL NUMERO DE CLASES ES: " + numeroClases);
        System.out.println("2. MAXIMO: " + maximo);
        System.out.println("3. MINIMO: " + minimo);
        System.out.println("4. EL RANGO ES: " + rango);
        System.out.println("5. EL TAMAÑO DE INTERVALO ES (SIN REDONDEAR): " + tamanoIntervaloSinRedondeo);
        System.out.println("6. EL TAMAÑO DEL INTERVALO ES: " + tamanoIntervaloRedondeado);

        // Imprimir tabla de frecuencias
        System.out.println("| Li-1 - Li | Xi | ni | Ni | hi | Hi |");
        System.out.println("|---|---|---|---|---|---|");
        for (int i = 0; i < numeroClases; i++) {
            int liInferior = minimo + (i * tamanoIntervaloRedondeado);
            int liSuperior = liInferior + tamanoIntervaloRedondeado - 1;
            int xi = (liInferior + liSuperior) / 2;
            int ni = contarFrecuencia(datos, n, liInferior, liSuperior);
            int niAcumulado = 0;
            for (int j = 0; j <= i; j++) {
                niAcumulado += contarFrecuencia(datos, n, minimo + (j * tamanoIntervaloRedondeado), minimo + ((j + 1) * tamanoIntervaloRedondeado) - 1);
            }
            double hi = (double) ni/n*100;
            double hiAcumulado = (double) niAcumulado/n*100;

            System.out.printf("| %2d - %2d | %2d | %2d | %2d | %5.2f | %5.2f |\n", liInferior, liSuperior, xi, ni, niAcumulado, hi, hiAcumulado);
        }

        System.out.println("| TOTAL |   | " + n + " |   |   |   |");
    }

    private static int contarFrecuencia(int[] datos, int n, int liInferior, int liSuperior) {
        int frecuencia = 0;
        for (int i = 0; i < n; i++) {
            if (datos[i] >= liInferior && datos[i] <= liSuperior) {
                frecuencia++;
            }
        }
        return frecuencia;
    }
}
