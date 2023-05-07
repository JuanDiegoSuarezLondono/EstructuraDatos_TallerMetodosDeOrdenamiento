/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tallermetodosdeordenamiento;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import javafx.scene.layout.BorderStroke;

/**
 *
 * @author suare
 */
public class TallerMetodosDeOrdenamiento {

    static int vectorOrdenado[];
    public static void main(String[] args) throws IOException {
        BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
        int vector[]; 
        String str=new String();
        boolean salir=true;
        int numero=0;
        System.out.println("Ingrese un vector de numeros:");
        str=br.readLine();
        vector=new int[str.length()]; 
        for(int i=0;i<str.length();i++) {
            vector[i]=Character.getNumericValue(str.charAt(i));        
        }
        System.out.println();
        System.out.println();
        vectorOrdenado=vector;
        do {
            System.out.println("\n______________________________________________");
            System.out.print("El vector ingresado es: ");
            System.out.print("|");
            for(int i=0;i<vector.length;i++) {
                System.out.print(vector[i]+"|");           
            }
            System.out.println("\n1) Burbuja");
            System.out.println("2) Quick Sort");
            System.out.println("3) Merge Sort");
            System.out.println("4) Heap Sort");
            System.out.println("5) Salir");
            System.out.println("Ingrese el numero por el que quiere ordenarlos:");        
            System.out.println("______________________________________________");
            numero=Integer.parseInt(br.readLine());
            switch(numero) {
                case 1:
                    Burbuja();
                    System.out.print("El vector ordenado es: ");
                    System.out.print("|");
                    for(int i=0;i<vectorOrdenado.length;i++) {
                        System.out.print(vectorOrdenado[i]+"|");           
                    }
                break;

                case 2:
                    quickSort(vectorOrdenado,0, vectorOrdenado.length-1);
                    System.out.print("El vector ordenado es: ");
                    System.out.print("|");
                    for(int i=0;i<vectorOrdenado.length;i++) {
                        System.out.print(vectorOrdenado[i]+"|");           
                    }
                break;

                case 3:
                   sort(vectorOrdenado,0,vectorOrdenado.length-1);
                   System.out.print("El vector ordenado es: ");
                   System.out.print("|");
                   for(int i=0;i<vectorOrdenado.length;i++) {
                       System.out.print(vectorOrdenado[i]+"|");           
                   }
                break;

                case 4:
                    HeapSort(vectorOrdenado);
                    System.out.print("El vector ordenado es: ");
                    System.out.print("|");
                    for(int i=0;i<vectorOrdenado.length;i++) {
                        System.out.print(vectorOrdenado[i]+"|");           
                    }
                break;
                
                case 5:
                    salir=false;
                break;
                
                default:
                    System.out.println("No sea pendejo, eso ni esta entre las opciones");
                break;
            }
        }while(salir);
    }
    
    static public void Burbuja() {
        boolean noEsta=true;
        int temporal;
        if(vectorOrdenado.length==1) {
            System.out.println("¿Y que pretendes que organice campeon?");
        }
        else {
            do{
                noEsta=false;
                for(int i=0;i<vectorOrdenado.length-1;i++) {
                    if(vectorOrdenado[i]>vectorOrdenado[i+1]) {
                        temporal=vectorOrdenado[i+1];
                        vectorOrdenado[i+1]=vectorOrdenado[i];
                        vectorOrdenado[i]=temporal;
                        noEsta=true;
                    }
                }
            }
            while(noEsta);
        }
    } 
    
    static void quickSort(int[] vector, int izquierda, int derecha) {
        int pivote = vector[izquierda];
        int i = izquierda;
        int j = derecha;
        int auxIntercambio;
        while (i < j) {                                 //mientras izqueirda sea menor a derecha
            while (vector[i] <= pivote && i < j) {      
                i++;
            }
            while (vector[j] > pivote) {
                j--;
            }
            if (i < j) {
                auxIntercambio = vector[i];
                vector[i] = vector[j];
                vector[j] = auxIntercambio;
            }
        }
        vector[izquierda] = vector[j];
        vector[j] = pivote;
        if (izquierda < j - 1) {
            quickSort(vector, izquierda, j - 1);
        }
        if (j + 1 < derecha) {
            quickSort(vector, j + 1, derecha);
        }        
    }
    
    static public void merge(int arr[], int l, int m, int r) {
        int n1 = m - l + 1;
        int n2 = r - m;
        int L[] = new int [n1];
        int R[] = new int [n2];
        for (int i=0; i<n1; ++i)
            L[i] = arr[l + i];
        for (int j=0; j<n2; ++j)
            R[j] = arr[m + 1+ j];
        int i = 0, j = 0;
        int k = l;
        while (i < n1 && j < n2)
        {
            if (L[i] <= R[j])
            {
                arr[k] = L[i];
                i++;
            }
            else
            {
                arr[k] = R[j];
                j++;
            }
            k++;
        }
        while (i < n1)
        {
            arr[k] = L[i];
            i++;
            k++;
        }
        while (j < n2)
        {
            arr[k] = R[j];
            j++;
            k++;
        }        
    } 
    static public void sort(int arr[], int l, int r) {
            if (l < r)
        {
            int m = (l+r)/2;
            sort(arr, l, m);
            sort(arr , m+1, r);
            merge(arr, l, m, r);
        }
    }
    
    static public void HeapSort(int arr[]) {
        int n = arr.length;
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);
        for (int i=n-1; i>=0; i--)
        {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heapify(arr, i, 0);
        }        
    } 
    
    static public void  heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2*i + 1;
        int r = 2*i + 2;
        if (l < n && arr[l] > arr[largest])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            heapify(arr, n, largest);
        }
    }
}
