package dinningP;

import java.util.concurrent.Semaphore;

public class SolutionSemaphore {
	
	public static int PENSANDO = 0;
	public static int FOME = 1;
	public static int COMENDO = 2;
	public static int N = 3;
	public static Semaphore mutex = new Semaphore(1);
	public static Philosopher[] filosofos = new Philosopher[N];
	
	public static int esq(int i) {
		
		return (i + 1) % N;
		
	}
	
	public static int dir(int i) {
		
		return (i - 1 + N) % N;
		
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < N; i++)
			filosofos[i] = new Philosopher(i);
		
	}

	public static void pega_talher(int id) throws InterruptedException {
		
		mutex.acquire();
		filosofos[id].estado = FOME;
		if(filosofos[esq(id)].estado != COMENDO && filosofos[dir(id)].estado != COMENDO) {
			filosofos[id].sem.release();
			filosofos[id].estado = COMENDO;
		}
		mutex.release();
		filosofos[id].sem.acquire();
		
	}

	public static void larga_talher(int id) throws InterruptedException {
		
		mutex.acquire();
		filosofos[id].estado = PENSANDO;
		
		if (filosofos[dir(id)].estado == FOME && filosofos[dir(dir(id))].estado != COMENDO) {
			filosofos[dir(id)].estado = COMENDO;
			filosofos[dir(id)].sem.release();
		}
		
		if (filosofos[esq(id)].estado == FOME && filosofos[esq(esq(id))].estado != COMENDO) {
			filosofos[esq(id)].estado = COMENDO;
			filosofos[esq(id)].sem.release();
		}
		
		mutex.release();
		
	}

}
