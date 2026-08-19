package edu.eci.arsw.primefinder;

import java.util.LinkedList;
import java.util.List;

public class PrimeFinderThread extends Thread{


	int a,b;

	private List<Integer> primes;

    private boolean isSuspended = false;
	public PrimeFinderThread(int a, int b) {
		super();
                this.primes = new LinkedList<>();
		this.a = a;
		this.b = b;
	}

        @Override
	public void run(){
            int totalPrimes = 0;
            for (int i= a;i < b;i++){
                while (isSuspended){
                    synchronized (this){
                        try {
                            wait();
                        }
                        catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    }
                }
                if (isPrime(i)){
                    primes.add(i);
                    System.out.println(i);
                    totalPrimes++;
                    System.out.println("the total primes is: " + totalPrimes);
                }
            }
	}

	boolean isPrime(int n) {
	    boolean ans;
            if (n > 2) {
                ans = n%2 != 0;
                for(int i = 3;ans && i*i <= n; i+=2 ) {
                    ans = n % i != 0;
                }
            } else {
                ans = n == 2;
            }
	    return ans;
	}

	public List<Integer> getPrimes() {
		return primes;
	}

    public synchronized void setSuspended() {
        this.isSuspended = true;
    }

	public synchronized void stopSuspended() {
        this.isSuspended = false;
        notifyAll();

    }
}
