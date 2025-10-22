package psp.hilos.ejemplo2;

public class ejemploContadorColaborativo {

    private static int value = 0;

    private static final int THREADS = 10;
    private static final int INCREMENTS_PER_THREAD = 100000;

    synchronized public void incrementa(){

        int valorPrevio = value;

        value = value + 1;

        if(valorPrevio != (value-1)){
            System.out.println("Valor previo: "+valorPrevio+" - Valor actual: "+value);
        }

    }

    public static void main(String[] args) {

        ejemploContadorColaborativo ecc = new ejemploContadorColaborativo();

        Thread[] threads = new Thread[THREADS];
        for (int i = 0; i < THREADS; i++){

            threads[i] = new Thread(() -> {
                    for(int j=0;j<INCREMENTS_PER_THREAD;j++){
                    ecc.incrementa();
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
        System.out.println("Obtenido: "+value);
    }

}
