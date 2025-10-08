package psp.procesos.sumas;

import com.sun.security.jgss.GSSUtil;

import java.io.*;

public class LanzadorSumas {

    private static String FILENAME = "logsumaglobal.txt";

    private void lanzarSuma(Integer n1, Integer n2, Integer proceso){

        ProcessBuilder pb;
        Process process;

        String classname ="psp.procesos.sumas.Sumador";
        // String classpath = "/home/alumno/IdeaProjects/LanzadorSumas/out/production/LanzadorSumas/";
        String currentPath = System.getProperty("user.dir");
        String classpath = currentPath + "/out/production/LanzadorSumas";


        try {

            pb = new ProcessBuilder("java", "-cp", classpath, classname, n1.toString(), n2.toString(), proceso.toString(), FILENAME);

            pb.inheritIO();
            process = pb.start();

            //process.waitFor();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void main(String[] args) {

        try{
            File file = new File(FILENAME);
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        LanzadorSumas ls = new LanzadorSumas();

        ls.lanzarSuma(2,3,1);
        ls.lanzarSuma(1646,1366,2);
        ls.lanzarSuma(3,6,3);
        ls.lanzarSuma(5,8,4);
        ls.lanzarSuma(1,7,5);

        try {
            BufferedReader br = new BufferedReader(new FileReader(FILENAME));
            String output, line;
            output="";

            while ((line=br.readLine()) != null){
                output += line;
            }

            System.out.print(output);
            br.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

}
