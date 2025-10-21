package psp.hilos.ejemplo1;

public class HiloThread extends Thread{

    @Override
    public void run(){
        System.out.println("Nombre del hilo actual: "+getName());
        for (int i=1; i<=5; i++){
            System.out.println("Contador hilo: "+getName()+" - "+i);
            try {
                sleep(5);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

}
