import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;

// http://www.aceptaelreto.com/problem/statement.php?id=354
public class NinosPrimero {

    static Scanner in;
    final static int MAX_ELEMS = 200000;
    static ArrayList<Integer> v;

    
    public static void main (String[] args) {
        try {
            String path = "C://Users//alvar//xXx//ML//repoJava//N//ninosPrimero//casos.txt";
            in = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (resuelveCaso());
    } //Cierre del main

    public static int ninos(int n) {
        int i = 1;
        int numNinos = 1;
        int max = v.get(0);
        int altura = v.get(0);
        while (i < n) {
            if (v.get(i) <= altura) { //Si la persona que pasa tiene una altura <= que la primera que pasó
                numNinos = i+1;			//se añade un niño y se actualiza la altura maxima para que se le considere un niño
                altura = max;
            }
            else if (v.get(i) > max) max = v.get(i); //Actualiza la maxima altura de las personas que van pasando
    
            i++;
        }
        return numNinos;
    }

    public static Boolean resuelveCaso() {
        int n = in.nextInt();
        if ( n == 0)
            return false;
        v = new ArrayList<Integer>(MAX_ELEMS);
        for (int i=0; i<n; i++)
            v.add(in.nextInt());
        System.out.println(ninos(n));
        return true;
    }
}