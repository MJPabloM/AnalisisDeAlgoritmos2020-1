///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package analisisdealgoritmos2020.pkg1;
//
//import java.util.ArrayList;
//import ordenamiento.AlgoritmoOrdenamiento;
//import ordenamiento.Burbuja;
//import ordenamiento.BurbujaOp;
//import ordenamiento.Insercion;
//import ordenamiento.Grafica;
//import ordenamiento.Herramientas;
//import ordenamiento.Manager;
//import ordenamiento.MergeSort;
//import ordenamiento.QuickSort;
//
//
//public class AnalisisDeAlgoritmos20201 {
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String[] args) {
//        
//        
//        //Burbuja  b = new Burbuja(true);
//        //BurbujaOp  b1 = new BurbujaOp(true);
//        Insercion  b2 = new Insercion(false);
//        MergeSort b3 = new MergeSort(false);
//        QuickSort b4 = new QuickSort(false);
//        
//        ArrayList<AlgoritmoOrdenamiento> lista = new ArrayList<>();
//        //lista.add(b);
//        //lista.add(b1);
//        lista.add(b2);
//        lista.add(b3);
//        lista.add(b4);
//       
//        Manager m = new Manager(lista);
//        m.ejecutarPrueba(3000, 5, 250, true);
//        
//       
//        
//    }
//    
//}
package analisisdealgoritmos2020.pkg1;

import ordenamiento.*;

public class AnalisisDeAlgoritmos20201 {

    public static void main(String[] args) {
        double ti= System.currentTimeMillis();
        long FR= Fibonacci.fiboRecursivo(60);
        double tf= System.currentTimeMillis();
        System.out.println("Fibonacci: "+(tf-ti));
        System.out.println(FR);


    }
    
}
