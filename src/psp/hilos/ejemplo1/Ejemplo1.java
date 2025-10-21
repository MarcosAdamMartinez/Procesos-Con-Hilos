package psp.hilos.ejemplo1;

public class Ejemplo1 {

    public static void main(String[] args) {

//        System.out.println("CLASE PRINCIPAL corriendo. Lanzamiento de hilos...");
//        System.out.println("Hilo de la clase Ejemplo1: "+Thread.currentThread().getName());

        HiloThread hilo1 = new HiloThread();
        HiloThread hilo2 = new HiloThread();
        HiloThread hilo3 = new HiloThread();

        hilo1.setPriority(Thread.MIN_PRIORITY);
        hilo2.setPriority(Thread.MAX_PRIORITY);
        hilo3.setPriority(Thread.NORM_PRIORITY);

        hilo1.start();
        hilo2.start();
        hilo3.start();

        try {
            hilo1.join();
            hilo2.join();
            hilo3.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Se acabó la ejecución");

    }
}
