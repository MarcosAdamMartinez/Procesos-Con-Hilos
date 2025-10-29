package psp.hilos.filosofos;

import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Filosofo implements Runnable{

    private final Semaphore cubiertoIzdo, cubiertoDcho;

    public Filosofo(Semaphore cubiertoIzdo, Semaphore cubiertoDcho){
        this.cubiertoIzdo = cubiertoIzdo;
        this.cubiertoDcho = cubiertoDcho;
    }

    @Override
    public void run(){

        Random random = new Random();

        while (true){
            try{
                cubiertoIzdo.acquire();

                if (cubiertoDcho.tryAcquire()) {
                    System.out.println(Thread.currentThread().getName() + " comiendo...");

                    cubiertoIzdo.release();
                    cubiertoDcho.release();

                    System.out.println(Thread.currentThread().getName() + " pensando...");

                    Thread.sleep(random.nextInt(1, 100));
                } else {
                    System.out.println(Thread.currentThread().getName()+" no ha podido coger los cubiertos");
                }
                cubiertoIzdo.release();
            } catch (InterruptedException e){
                throw new RuntimeException();
            }

        }

    }
}
