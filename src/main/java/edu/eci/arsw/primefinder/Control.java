/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.primefinder;

/**
 *
 */
public class Control extends Thread {
    
    private final static int NTHREADS = 3;
    private final static int MAXVALUE = 30000000;
    private final static int TMILISECONDS = 5000;

    private final int NDATA = MAXVALUE / NTHREADS;

    private PrimeFinderThread pft[];
    
    private Control() {
        super();
        this.pft = new  PrimeFinderThread[NTHREADS];

        int i;
        for(i = 0;i < NTHREADS - 1; i++) {
            PrimeFinderThread elem = new PrimeFinderThread(i*NDATA, (i+1)*NDATA);
            pft[i] = elem;
        }
        pft[i] = new PrimeFinderThread(i*NDATA, MAXVALUE + 1);
    }
    
    public static Control newControl() {
        return new Control();
    }

    @Override
    public void run() {
        long actualTime = System.currentTimeMillis();
        for(int i = 0;i < NTHREADS;i++ ) {
            pft[i].start();
            synchronizedPrimes();
        }
    }

    private void synchronizedPrimes() {
        long limite = System.currentTimeMillis() + TMILISECONDS;

        for (int i = 0; i < NTHREADS; i++) {
            long restante = limite - System.currentTimeMillis();
            if (restante > 0) {
                synchronized (pft[i]) {
                    try {
                        pft[i].wait(restante);
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }
                }
            }
            System.out.println(pft[i].getPrimes());
        }
    }
}
