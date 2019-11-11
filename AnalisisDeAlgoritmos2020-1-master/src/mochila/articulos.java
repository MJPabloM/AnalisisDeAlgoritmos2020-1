package mochila;

import java.util.ArrayList;
import java.util.Random;

public class articulos {
    private int weigth;
    private int benefit;

    public articulos(int weigth, int benefit) {
        this.weigth = weigth;
        this.benefit = benefit;
    }

    public int getWeigth() {
        return weigth;
    }

    public void setWeigth(int weigth) {
        this.weigth = weigth;
    }

    public int getBenefit() {
        return benefit;
    }

    public void setBenefit(int benefit) {
        this.benefit = benefit;
    }

    public static ArrayList<articulos> generarItems(int numItems, int peso, int beneficio){
        ArrayList<articulos> Items = new ArrayList<>();
        for (int i = 0; i <numItems ; i++) {
            Random ranPeso = new Random();
            Random ranBeneficio= new Random();
            articulos aux = new articulos(ranPeso.nextInt(peso)+1,ranBeneficio.nextInt(beneficio)+1);
            Items.add(aux);
        }
        return Items;
    }
    @Override
    public String toString(){
        return weigth+","+benefit;
    }
}