package psp.hilos.prodcons;

public class Productor implements Runnable{

    private final int idProductor;
    private final int numProductos;
    private BufferProductos bufferProductos;

    public Productor(int idProductor, int numProductos, BufferProductos buffer){

        this.numProductos = numProductos;
        this.idProductor = idProductor;
        this.bufferProductos = buffer;

    }

    @Override
    public void run(){
        for (int i = 1; i <= numProductos; i++) {
            int producto = idProductor * 1000 + i;

            try {
                bufferProductos.producir(producto);
                System.out.println("El productor "+idProductor+" produjo: "+producto);
                Thread.sleep((long)(Math.random()*100));
            } catch (InterruptedException e){
                e.printStackTrace();
            }

        }

    }

}
