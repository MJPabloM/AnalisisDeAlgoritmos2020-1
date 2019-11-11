package ordenamiento;

public class QuickSort implements AlgoritmoOrdenamiento,Runnable {
    private double[] arreglo;
    private double tt;
    private boolean thread;


    public QuickSort() {
        this.arreglo= null;
        this.thread= false;
    }

    public QuickSort(boolean thread) {
        this.thread= thread;
        this.arreglo = null;
    }


    @Override
    public void run() {
        QuickSort(this.arreglo,0,this.arreglo.length-1);

    }

    @Override
    public void definirDatos(double[] arreglo) {
        this.arreglo=arreglo;
    }

    @Override
    public void ordenarDatos() {
        if (this.thread){
            Thread hilo = new Thread(this);
            hilo.start();

        }else run();

    }

    @Override
    public double getTt() {
        return tt;
    }
    
    void QuickSort(double arr[], int inicio, int fin){
        double ti=System.currentTimeMillis();
                if(inicio>=fin) return;
                double pivote=arr[inicio];
                int elemIzq=inicio+1;
                int elemDer=fin;
                while(elemIzq<=elemDer){
                        while(elemIzq<=fin && arr[elemIzq]<pivote){
                                elemIzq++;
                        }
                        while(elemDer>inicio && arr[elemDer]>=pivote){
                                elemDer--;
                        }
                        if(elemIzq<elemDer){
                                double temp=arr[elemIzq];
                                arr[elemIzq]=arr[elemDer];
                                arr[elemDer]=temp;
                        }
                }
                
                if(elemDer>inicio){
                        double temp=arr[inicio];
                        arr[inicio]=arr[elemDer];
                        arr[elemDer]=temp;
                }
                QuickSort(arr, inicio, elemDer-1);
                QuickSort(arr, elemDer+1, fin);
        }
        double tf=System.currentTimeMillis();
        this.tt = tf - ti;

}