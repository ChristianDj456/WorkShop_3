package workshop3;

import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
//import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Workshop3 {

    /*Algoritmos y complejidad
Name: Cristian Chavez Sarmiento ID: 200153656
Workshop 3: Best, Worst, and Average Case Analysis Experiments
Date: 10/08/2022
Description:En este algoritmo se realizan experimentos tomando los casos promedios en los tiempos
que tarda en ejecutarse el algoritmo de los numeros duplicados, duplicando su tamaño empezando en 16
hasta 1024
     */
    //https://github.com/misael-diaz/computer-programming/blob/main/src/io/java/BasicIOExample.java
    public static int[] c = new int[20];
    public static int numel = 262144;
    public static int[] numbs = new int[numel], counts = new int[numel];
    public static double[] ti = new double[500];
    public static int x = 0;

    public static void main(String[] args) {

        //DecimalFormat formatea = new DecimalFormat("###,###.##");
        double[] time = new double[20];
        // int[] fsize = new int[32];
        double promedio = 0, promedio2 = 0;
        double prom = 0;
        int k = 0;

        //  while (numel <= 32) {
        create();       // creates a file
        write(numel);	// writes data to the file
        read();		// reads data in the file
        store();
        double startTime = System.nanoTime();
        for (int i = 0; i < 20; i++) {
            int comcount = 0; // stores data in an array
            DuplicatesNumber(comcount);
            //}
            double endTime = System.nanoTime() - startTime;
            //System.out.println("El tiempo de ejecucion fue " + endTime + " NanoSeconds");
            //numel = numel * 2;
            time[k] = endTime;
            ++k;
        }
        for (int l = 0; l < k; l++) {
            //System.out.println("Tiempo " + l + " " + time[l]);
            promedio = promedio + time[l];
        }
        for (int l = 0; l < c.length; l++) {
            //System.out.println("Comparaciones " + l + " " + c[l]);
            //System.out.println("Tiempo " + l + " " + ti[l]);
            promedio2 = promedio2 + ti[l];

        }
        System.out.println("El promedio de comparaciones es " + (float) (promedio2 / c.length));
        System.out.println("El tiempo total de las ejecuciones fue: " + promedio / k + " en " + k + " Repeticiones ");
        /* System.out.println("N "+"TC "+"TT");
        for (int i = 0; i < 10; i++) {
            System.out.println(fsize[i]+" "+ c[i]+" "+ ti[i]);
        }*/

    }

    // implementations:
    private static void create() // creates a file
    {
        try {
            // defines the filename
            String fname = ("data.txt");
            // creates a new File object
            File f = new File(fname);

            String msg = "creating file `" + fname + "' ... ";
            System.out.println();
            System.out.printf("%s", msg);
            // creates the new file
            f.createNewFile();
            System.out.println("done");

        } catch (IOException err) {
            // complains if there is an Input/Output Error
            err.printStackTrace();
        }

        return;
    }

    private static void write(int numel) // writes data to a file
    {
        try {
            // defines the filename
            String filename = ("data.txt");
            // creates new PrintWriter object for writing file
            PrintWriter out = new PrintWriter(filename);

            // int numel = 1000;
            Random rand = new Random(numel);
            String msg = "writing %d numbers ... ";
            System.out.printf(msg, numel);
            //writes the integers in the range [0, 256)
            for (int i = 0; i != numel; ++i) {
                //out.printf("%d\n", i); //Se escriben numeros no aleatorios para el Worst Case
                out.printf("%d\n", rand.nextInt(256)); //Se escriben Numeros random en el rango de 256 para el Average Case
            }

            System.out.println("done");

            System.out.printf("closing file ... ");
            out.close();	// closes the output stream
            System.out.println("done");
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }
        return;
    }

    private static void read() // reads the file contents and prints them to the console
    {
        // defines the filename
        String filename = ("data.txt");
        // creates a File object
        File f = new File(filename);
        try {
            // attempts to create a Scanner object
            Scanner in = new Scanner(f);

            System.out.printf("\nnumbers in file:\n");

            int x;
            int count = 0;
            // reads integers in file until EOF
            while (in.hasNextInt()) {
                // reads  number and prints it
                x = in.nextInt();
                System.out.printf("%2d %4d\n", count, x);
                ++count;
            }

            String msg = ("%d numbers have been read\n");
            System.out.printf(msg, count);

            in.close();	// closes the input stream

        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return;
    }

    private static void store() // stores the file contents into an array and prints the array
    {
        String filename = "data.txt";
        File f = new File(filename);

        try {
            Scanner in = new Scanner(f);

            // allocates list for storing the numbers in file
            int size = lines(filename);
            int[] list = new int[size];
            //int [] counta = new int [size];

            int count = 0, countr = 0;
            int key = list[0];
            // reads numbers into array
            while (in.hasNextInt()) {
                list[count] = in.nextInt();
                ++count;
            }
            System.out.printf("\nnumbers in array:\n");
            for (int i = 0; i != size; ++i) {
                System.out.printf("%2d %4d\n", countr, list[i]);
                ++countr;
            }
            /*  for (int i = 0; i != size; i++) {
                            if (i == list[i]) {
                                counta[i]++;
                            }
                            System.out.printf("%2d %4d\n", i, list[i]);
                    }*/
            String msg = ("array stores %d numbers\n");
            System.out.printf(msg, count);

            in.close();	// closes the input stream

        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return;
    }

    public static void DuplicatesNumber(int comcount) { // Se crea una funcion que cuenta los numeros duplicados en el archivo

        double startCompa = System.nanoTime();
        String filename = "data.txt";
        File f = new File(filename);

        try {

            Scanner in = new Scanner(f);

            int size = lines(filename);
            int[] list = new int[size];
            int count = 0, counta = 0;
            double[] comp = new double[size];
            int key = 0; //Se crea una variable permanente que almacena el valor de la primera posicion en el vector

// reads numbers into array
            while (in.hasNextInt()) {
                list[count] = in.nextInt();
                ++count;
            }

           // System.out.printf("\nDuplicates numbers in array:\n");
          //  for (int i = 0; i != size; ++i) {
                //System.out.printf("%2d %4d\n", counts[i], list[i]);
            //}

           // String msg = ("array stores %d numbers\n");
            //System.out.printf(msg, count);

            for (int i = 0; i < numbs.length; ++i) {
                numbs[i] = 0;
            }

            for (int i = 0; i < numbs.length; ++i) {
                counts[i] = 0;
            }
            for (int i = 0; i < list.length; ++i) { //Se realizan las comparaciones
                key = list[i];                      //Para verificar los numeros repetidos en el array
                for (int j = 0; j < list.length; j++) {
                    if (key == list[j] && i != j) { //Si los numeros son iguales se aumenta en 1 el contador
                        ++comcount;
                        numbs[i] = key;
                        counts[i]++;
                    }
                }
            }
            c[x] = comcount; // Se almacena el numero comparaciones en un vector
            
            // System.out.println("Mostrar c " + comcount);
            in.close();	// closes the input stream
            //System.out.println("El tiempo de ejecucion fue " + formatea.format(endTime) + " NanoSeconds");
            //System.out.println("El tiempo de ejecucion fue " + (endTime) + " NanoSeconds");
            //System.out.println("El tiempo de cada comparacion: " + prom);
            double endCompa = System.nanoTime() - startCompa;
            ti[x] = endCompa; //Se almacenan los tiempos en un vector
            x++; //Aumentamos la posicion del vector
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Workshop3.class.getName()).log(Level.SEVERE, null, ex);
        }
        return;
    }

    private static int lines(String filename) // counts number of lines (or records) in a file
    {

        int count = 0;
        // creates a File object
        File f = new File(filename);
        try {
            // attempts to create a Scanner object
            Scanner in = new Scanner(f);

            // reads integers in file until EOF for counting
            while (in.hasNextInt()) {
                in.nextInt();
                ++count;
            }

            in.close();	// closes the input stream
        } catch (FileNotFoundException err) {
            // complains if file does not exist
            err.printStackTrace();
        }

        return count;
    }
    // public double []compa = new double[1000];
}

/*
 * COMMENTS:
 *
 * Reading Data:
 * Reads integers until the scanner object finds something that it is not
 * an integer, such as a String or an End-Of-File EOF for instance.
 *
 * Static methods:
 * Static methods are not bound to any object of the class and these can
 * be conveniently invoked directly from the main program (as done here).
 *
 * Variables:
 * Note that local variables are destroyed (freed from memory) after the
 * method executes. Do not use global variables unless you really have to.
 *
 */
