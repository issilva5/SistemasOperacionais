package dinningP;

public class SolutionMonitor {
	
	public static int PENSANDO = 0;
	public static int FOME = 1;
	public static int COMENDO = 2;
	public static int N = 5;
	public static PhilosopherMonitor[] filosofos = new PhilosopherMonitor[N];
	
	public static int esq(int i) {
		
		return (i + 1) % N;
		
	}
	
	public static int dir(int i) {
		
		return (i - 1 + N) % N;
		
	}
	
	public static void main(String[] args) {
		
		for (int i = 0; i < N; i++)
			filosofos[i] = new PhilosopherMonitor(i);
		
	}

	public static void pega_talher(int id) throws InterruptedException {
		
		filosofos[id].estado = FOME;
		if(filosofos[esq(id)].estado != COMENDO && filosofos[dir(id)].estado != COMENDO) {
			
			filosofos[id].estado = COMENDO;
			
		} else synchronized (filosofos[id]) {
			
			filosofos[id].wait();
			
		}
		
	}

	public static void larga_talher(int id) throws InterruptedException {
		
		filosofos[id].estado = PENSANDO;
		
		if (filosofos[dir(id)].estado == FOME && filosofos[dir(dir(id))].estado != COMENDO) {
			
			filosofos[dir(id)].estado = COMENDO;
			
			synchronized (filosofos[dir(id)]) {
				filosofos[dir(id)].notify();
			}
			
		}
		
		if (filosofos[esq(id)].estado == FOME && filosofos[esq(esq(id))].estado != COMENDO) {
			
			filosofos[esq(id)].estado = COMENDO;
			
			synchronized (filosofos[esq(id)]) {
				filosofos[esq(id)].notify();
			}
			
		}
		
	}

}
