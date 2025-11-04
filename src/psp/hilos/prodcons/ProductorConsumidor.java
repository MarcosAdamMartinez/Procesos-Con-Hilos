package psp.hilos.prodcons;

public class ProductorConsumidor {

    public static void main(String[] args) throws InterruptedException{

        int CAPACIDAD_BUFFER = 5;
        int PRODUCTORES = 2;
        int CONSUMIDORES = 3;
        int ITEMS_POR_PRODUCTOR = 20;

        BufferProductos bufferProductos = new BufferProductos(CAPACIDAD_BUFFER);

        Thread[] productores = new Thread[PRODUCTORES];
        Thread[] consumidores = new Thread[CONSUMIDORES];

        for (int i = 0; i < productores.length; i++){
            productores[i] = new Thread(new Productor(i+1, ITEMS_POR_PRODUCTOR, bufferProductos));
            productores[i].start();
        }

        for (int j = 0; j < consumidores.length; j++){
            consumidores[j] = new Thread(new Consumidor(j+1, bufferProductos));
            consumidores[j].start();
        }

        for (Thread p: productores){
            p.join();
        }

        while (!bufferProductos.estaVacio()){
            Thread.sleep(100);
        }

        for (Thread c: consumidores){
            c.interrupt();
            c.join();
        }

        System.out.println("Programa finalizado");

    }

}
