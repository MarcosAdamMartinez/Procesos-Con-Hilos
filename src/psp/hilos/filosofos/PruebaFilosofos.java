package psp.hilos.filosofos;

import java.util.concurrent.Semaphore;

public class PruebaFilosofos {

    public static void main(String[] args) {

        Semaphore semaforo12 = new Semaphore(1);
        Semaphore semaforo23 = new Semaphore(1);
        Semaphore semaforo31 = new Semaphore(1);

        Filosofo filo1 = new Filosofo(semaforo12,semaforo31);
        Filosofo filo2 = new Filosofo(semaforo23,semaforo12);
        Filosofo filo3 = new Filosofo(semaforo31,semaforo23);

        Thread h1, h2, h3;
        h1 = new Thread(filo1, "Filosofo 1");
        h2 = new Thread(filo1, "Filosofo 2");
        h3 = new Thread(filo1, "Filosofo 3");

        h1.start();
        h2.start();
        h3.start();

        try{
            h1.join();
            h2.join();
            h3.join();
        } catch (InterruptedException e){
            e.printStackTrace();
        }

    }

}
