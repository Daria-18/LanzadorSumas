package psp.procesos.sumas;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Sumador {

    /**
     * Metodo sucesion de fibonnaci entre dos numeros; n1,n2 y identificador; proceso
     * @param n1 rango inferior
     * @param n2 rango superior
     * @param proceso id proceso
     * @return resultado sucesión
     */

    public int sumar(int n1, int n2, int proceso){

        int resultado = 0;
        for (int i=n1; i<=n2; i++){
            resultado += i;
        }

        System.out.println("Ejecutado por el proceso: " + proceso);
        System.out.println("El resultado de la suma de " + n1 + " y " + n2 + " es igual a: " + resultado );

        return resultado;
    }

    public int sumar2(int n1, int n2, int proceso, String filename){

        int resultado = 0;
        for (int i=n1; i<=n2; i++){
            resultado += i;
        }

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write("\nEjecutado por el proceso: " + proceso);
            bw.write("\nEl resultado de la suma de " + n1 + " y " + n2 + " es igual a: " + resultado);
            bw.flush();
            bw.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return resultado;
    }

    /**
     * Esta clase usa el metodo main para realizar una llamada al metodo sumar, que realiza una sucesion de fibonnaci en el rango
     * determinado entre n1(primer param) y n2(segundo param), añade un id al proceso
     * @param args args main method
     */

    public static void main(String[] args) {

        int n1 = Integer.parseInt(args[0]);
        int n2 = Integer.parseInt(args[1]);
        int proceso = Integer.parseInt(args[2]);
        String filename = args[3];


        Sumador sumador = new Sumador();

        // sumador.sumar(n1,n2,proceso);
        sumador.sumar2(n1,n2,proceso,filename);

    }

}
