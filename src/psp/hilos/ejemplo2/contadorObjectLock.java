package psp.hilos.ejemplo2;

public class contadorObjectLock {

    public static int contador1 = 0;
    public static int contador2 = 0;

    private static final int THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 100000;

    private final Object lock1 = new Object();
    private final Object lock2 = new Object();

    public void incrementar1(){
        synchronized (lock1) {
            contador1++;
        }
    }

    public void incrementar2(){
        synchronized (lock2){
            contador2++;
        }
    }


    public static void main(String[] args) {

        contadorObjectLock contador = new contadorObjectLock();

        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++){

            threads[i] = new Thread(() -> {
                for(int j=0;j<INCREMENTS_PER_THREAD;j++){
                    contador.incrementar1();
                    contador.incrementar2();
                }
            });
            threads[i].start();
        }

        for(Thread t: threads){

            try{
                t.join();
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }

        }

        System.out.println("Esperado: "+(THREADS*INCREMENTS_PER_THREAD));
        System.out.println("Obtenido cont1: "+contador1);
        System.out.println("Obtenido cont2: "+contador2);

    }

}
