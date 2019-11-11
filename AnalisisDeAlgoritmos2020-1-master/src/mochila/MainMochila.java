package mochila;

public class MainMochila {
   
    public static void mochila(){
        mochila backpack = new mochila(articulos.generarItems(30,25,100),250);
        backpack.buscarSolucion();
    }
    public static  void main(String[] agrs){
        MainMochila.mochila();
    }
}