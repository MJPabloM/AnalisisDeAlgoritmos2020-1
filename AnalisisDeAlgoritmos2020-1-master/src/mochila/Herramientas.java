package mochila;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Herramientas {
    public static ArrayList<articulos> items;
    public static File abre;

    public static void leerDatos(){
        String texto,aux;
        LinkedList<String> lista = new LinkedList<>();
        try {
            //Llamamos al metodo que permite cargar la ventana
            JFileChooser file= new JFileChooser();
            file.setCurrentDirectory(new File("./"));
            file.showOpenDialog(file);
            // abrimos el archivo seleccionado
            File abre = file.getSelectedFile();
            //recorremos el archivo y lo leemos
            items = new ArrayList<>();
            if (abre != null){
                FileReader archivos = new FileReader(abre);
                BufferedReader lee = new BufferedReader(archivos);
                while ((aux=lee.readLine())!= null){
                    texto=aux;
                    lista.add(texto);
                }
                lee.close();
                ArrayList<String> lista2 = new ArrayList<>();
                for (int i = 0; i < lista.size() ; i++) {
                    StringTokenizer st = new StringTokenizer(lista.get(i),",");
                    while (st.hasMoreElements()){
                        lista2.add(st.nextToken());
                    }
                    articulos itemaux = new articulos(Integer.parseInt(lista2.get(0)),Integer.parseInt(lista2.get(1)));
                    items.add(itemaux);
                    lista2.clear();


                }
            }

        }
         catch (IOException e) {
            JOptionPane.showMessageDialog(null, e + ""
                            + "\nNo se ha encontrado el archivo",
                    "ADVERTENCIA!!!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void guardar(mochila mochila) {

        try {
            FileWriter fileWriter= null;
            fileWriter = new FileWriter(new File("./mochilaSaved.txt"));
            for (int i = 0; i < mochila.getItems().size() ; i++) {
                fileWriter.write(mochila.getItems().get(i).toString()+"\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
