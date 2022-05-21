import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList; // import the ArrayList class
import java.util.Scanner;


// Clase principal iniciadora del programa ejemplo aprenderaprogramar.com

public class Erasmus {

    static Scanner in;
    final static int MAX_ELEMS = 100000;
    static ArrayList<Integer> v = new ArrayList<Integer>(MAX_ELEMS);

    public static void main (String[] args) {
        try {
            String path = "C://Users//alvar//xXx//ML//repoJava//N//erasmus//casos.txt";
            in = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (resuelveCaso());
    } //Cierre del main

    public static long erasmus(ArrayList<Integer> v, int n){
        long suma=0, resul=0;
        int i = 0;
    /*
    (0 <= i <=n) 
    ^ ret = Sum a,b : 0 <= a < b < i : v[a] + v[b] 
    ^ suma = Sum a: 0 <= a < i : v[i]
    */
        while (i < n){
            resul += v.get(i) * suma;
            suma += v.get(i);   
            ++i;
        }
        return resul;
    }

    //{Pos: Sum a,b : 0 < a < b < n: v[a]+v[b]}

    public static Boolean resuelveCaso() {
        int n;
        v = new ArrayList<Integer>(MAX_ELEMS);
        n = in.nextInt();

        if (n == 0)
            return false;
        for (int i = 0; i < n; ++i){
            v.add(in.nextInt());
        }
        System.out.println(erasmus(v, n)) ;
        return true;
    }



} //Cierre de la clase
