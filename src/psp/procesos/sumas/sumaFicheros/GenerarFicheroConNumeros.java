package psp.procesos.sumas.sumaFicheros;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class GenerarFicheroConNumeros {

    final static  int NUM_LINEAS = 10000000; //Con 10M de lineas por ficheros -> 800MB
    final static int PESO = Integer.MAX_VALUE / 100;

    public static void main(String[] args) {

        if (args.length<1){
            System.out.println("Se necesita el nombre del fichero");
            System.exit(1);
        }

        try{
            PrintWriter salida = new PrintWriter(new FileWriter(args[0]));

            for (int num = 1; num <= NUM_LINEAS; num++){
                long aleatorio = (long)(Math.random() * PESO);
                salida.println(aleatorio);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Fichero creado");
    }

}
