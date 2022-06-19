import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class Chrono {

    // parametros del cronometro y del problema
    static final int MAX_DATO = 1000000;
    static final int MAX_CASOS = 100000;
    static final String COMMAND = "java App";
    static final String casosFile = "C://Users//alvar//xXx//ML//repoJava//NlogN//nosInvaden//casos.txt";
    static final String csvFile = "C://Users//alvar//xXx//ML//repoJava//NlogN//nosInvaden//ctiempos.csv";

    static final int REPETITIONS = 10;
    static final int OBSERVATIONS = 3;
    static final int MIN_CASOS = 1;
    static Scanner in;
    static int observations[] = { 1, 50, 99 };

    public static void main (String[] args) {

        try (BufferedWriter csvWriter = new BufferedWriter(new FileWriter(csvFile, false))) {
        // This writes te csv header
            for(int i = 0; i < OBSERVATIONS-1; i++ )
                csvWriter.write("N,seconds,");
            csvWriter.write("N,seconds\n");
            
            for (int i = 0; i < OBSERVATIONS; i++) {

                int currentN = (observations[i] * MAX_CASOS) / 100;
                generarEntrada(currentN);

                long start = System.nanoTime();
                long end = start;
                Process process = Runtime.getRuntime().exec(COMMAND);
                int exitValue = process.waitFor();
                if (exitValue == 0)
                    end = System.nanoTime();
                else
                    System.out.println("error al lanzar el subproceso");
                
                double elapsedTimeInSecond = (double) (end - start) / 1_000_000_000;
                double ponderada = elapsedTimeInSecond / REPETITIONS;
                NumberFormat formatter = new DecimalFormat("#0.000000000");     
                String result = formatter.format(ponderada); 

                System.out.println("i = " + i + " | N = " + currentN + " | seconds = " + result );
                System.out.println("--------------------------------------------");
                System.out.println("--------------------------------------------");
                csvWriter.write(currentN + "," + result );
                if (i < OBSERVATIONS-1)
                    csvWriter.write(",");
            }
            csvWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void generarEntrada(int currentN){
        try (BufferedWriter casosWriter = new BufferedWriter(new FileWriter(casosFile, false))) {
            Random rand = new Random(); //instance of random class
            for (int k = 0; k < REPETITIONS; k++) {
                casosWriter.write(currentN + "\n");
                int num;
                for (int j = 0; j < currentN; j++) {
                    num = 1 + rand.nextInt(MAX_DATO); 
                    casosWriter.write(num + " ");
                }
                casosWriter.write("\n");
                for (int j = 0; j < currentN; j++) {
                    num = 1 + rand.nextInt(MAX_DATO); 
                    casosWriter.write(num + " ");
                }
                casosWriter.write("\n");
            }
            casosWriter.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}
