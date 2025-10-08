package psp.procesos.sumas.sumaFicheros;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class LanzaSumaFicheros {

    private static final String[] fileNames = {"cuenta1.txt", "cuenta2.txt", "cuenta3.txt", "cuenta4.txt",
            "cuenta5.txt", "cuenta6.txt", "cuenta7.txt",
            "cuenta8.txt", "cuenta9.txt", "cuenta10.txt"};

    private static final String packagePath = "psp.procesos.sumas.sumaFicheros";

    public static void main(String[] args) throws IOException, InterruptedException {

        ProcessBuilder pb = null;
        File file;

        List<Process> listaFicherosGenerados = new ArrayList<>();
        for (String fileName : fileNames) {
            file = new File(fileName);
            if(!file.exists()){
                pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), packagePath + ".GenerarFicheroConNumeros", fileName);
                listaFicherosGenerados.add(pb.start());
                //pb.start().waitFor();
            }
        }

        while(!listaFicherosGenerados.isEmpty()){
            Process procesoFichero;
            Iterator<Process> iterator = listaFicherosGenerados.iterator();
            while (iterator.hasNext()){
                procesoFichero = iterator.next();
                if (!procesoFichero.isAlive()){
                    iterator.remove();
                }
            }
        }


        LanzaSumaFicheros lsf = new LanzaSumaFicheros();
        lsf.sumaSerie();

        lsf.sumaParelelo();

    }

    public void sumaParelelo() throws IOException, InterruptedException {
        long total = 0;
        long tInit = System.currentTimeMillis();

        Process[] procesos = new Process[fileNames.length];
        InputStream[] entradas = new InputStream[fileNames.length];
        ProcessBuilder pb = null;

        for (int pos = 0; pos<fileNames.length; pos++) {
            pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), packagePath + ".SumaUnFichero", fileNames[pos]);

            procesos[pos] = pb.start();
            entradas[pos] = procesos[pos].getInputStream();
        }

        boolean algunVivo = true;
        while (algunVivo){
            algunVivo = false;
            
            for (int pos = 0; pos < fileNames.length; pos++){
                if (procesos[pos]==null) continue;
                if(entradas[pos].available()>0){
                    String linea = new String(entradas[pos].readAllBytes()).trim();
                    long valor = Long.parseLong(linea);

                    procesos[pos]=null;
                    entradas[pos].close();
                    total+=valor;
                } else if (procesos[pos].isAlive()) {
                    algunVivo = true;
                }
            }

            Thread.sleep(10);

        }
        long tFin = System.currentTimeMillis();
        System.out.printf("El total es de: %20d\n",total);
        System.out.printf("Tiempo empleado de: %d ms.\n", (tFin-tInit) );
    }



    public void sumaSerie() throws IOException, InterruptedException {
        long total = 0;

        Process proceso;
        InputStream entrada;

        ProcessBuilder pb = null;

        long tInit = System.currentTimeMillis();

        for (String fileName : fileNames) {
                pb = new ProcessBuilder("java", "-cp", System.getProperty("java.class.path"), packagePath + ".SumaUnFichero", fileName);
                proceso = pb.start();
                proceso.waitFor();
                entrada = proceso.getInputStream();

                String linea = new String(entrada.readAllBytes()).trim();
                long valor = Long.parseLong(linea);

                entrada.close();
                total += valor;
        }
        long tFin = System.currentTimeMillis();

        System.out.printf("El total es de: %20d\n",total);
        System.out.printf("Tiempo empleado de: %d ms.\n", (tFin-tInit) );
    }

}
