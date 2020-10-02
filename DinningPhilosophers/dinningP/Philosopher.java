package dinningP;
import java.util.concurrent.Semaphore;

public class Philosopher implements Runnable {

		public int id;
		public int estado;
		public Semaphore sem;
		
		public Philosopher(int id) {
			
			this.id = id;
			this.estado = SolutionSemaphore.PENSANDO;
			this.sem = new Semaphore(0);
			new Thread((Runnable) this, "Philosopher " + id).start();
			
		}
		
		@Override
		public void run() {
			
			while(true) {
			
				try {
					System.out.println("Filósofo " + id + " está pensando!");
					Thread.sleep(1000);
					System.out.println("Filósofo " + id + " está com fome!");
					SolutionSemaphore.pega_talher(this.id);
					System.out.println("Filósofo " + id + " está comendo!");
					Thread.sleep(200);
					System.out.println("Filósofo " + id + " terminou de comer!");
					SolutionSemaphore.larga_talher(this.id);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}