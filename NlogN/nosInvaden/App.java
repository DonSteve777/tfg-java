import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * ¡Nos invaden!
 * Problema número 305
 * http://www.aceptaelreto.com/problem/statement.php?id=305
 * N log N
 * tiempos buenos
 */

public class App {
    
    static Scanner in;
    
    public static void main (String[] args) {
        try {
            String path = "C://Users//alvar//xXx//ML//repoJava//NlogN//nosInvaden//casos.txt";
            in = new Scanner(new File(path));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        while (resuelveCaso());
    } //Cierre del main


   //O(nlogn). Mejor coste para ordenación
// Merges two subarrays of arr[].
// First subarray is arr[l..m]
// Second subarray is arr[m+1..r]
static void merge(ArrayList<Integer> arr, int l, int m, int r){
	int n1 = m - l + 1;
	int n2 = r - m;
	// Create temp arrays
	int L[] = new int[n1];
	int R[] = new int[n2];
	// Copy data to temp arrays L[] and R[]
	for (int i = 0; i < n1; i++)
		L[i] = arr.get(l + i);
	for (int j = 0; j < n2; j++)
		R[j] = arr.get(m + 1 + j);
	// Merge the temp arrays back into arr[l..r]
	// Initial index of first subarray
	int i = 0;
	// Initial index of second subarray
	int j = 0;
	// Initial index of merged subarray
	int k = l;
	while (i < n1 && j < n2) {
		if (L[i] <= R[j]) {
            arr.set(k, L[i]);
			i++;
		}
		else {
            arr.set(k, R[j]);
			j++;
		}
		k++;
	}
	// Copy the remaining elements of
	// L[], if there are any
	while (i < n1) {
        arr.set(k, L[i]);
		i++;
		k++;
	}
	// Copy the remaining elements of
	// R[], if there are any
	while (j < n2) {
        arr.set(k, R[j]);
		j++;
		k++;
	}
}

static void mergeSort(ArrayList<Integer> v, int l, int r) {
	if (l >= r) {
		return;//returns recursively
	}
	int m = (l + r - 1) / 2;
	mergeSort(v, l, m);
	mergeSort(v, m + 1, r);
	merge(v, l, m, r);
}

static int voraz(ArrayList<Integer> invasores, ArrayList<Integer> defensores, int N) {
	int ret = 0;
	int i = 0, d = 0;
	while (d < N) {
		int def = defensores.get(d);    //[d];
		int inv = invasores.get(i);//[i];
		if (def >= inv) {
			ret++;
			i++;
		}
		d++;
	}
	return ret;
}
public static Boolean resuelveCaso() {
		int n;
		if (in.hasNext())
			n = in.nextInt();
		else
			return false;

        ArrayList<Integer> invasores = new ArrayList<Integer>(n);
        ArrayList<Integer> defensores = new ArrayList<Integer>(n);

        for (int i = 0; i < n; i++)
            invasores.add(in.nextInt()); 
        for (int i = 0; i < n; i++)
            defensores.add(in.nextInt()); 
        mergeSort(invasores, 0, n - 1);
	    mergeSort(defensores, 0, n - 1);        

        System.out.println(voraz(invasores, defensores, n));
        return true;
    }
}