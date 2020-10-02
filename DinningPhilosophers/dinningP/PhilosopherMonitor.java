package dinningP;

public class PhilosopherMonitor implements Runnable {

		public int id;
		public int estado;
		
		public PhilosopherMonitor(int id) {
			
			this.id = id;
			this.estado = SolutionMonitor.PENSANDO;
			new Thread((Runnable) this, "Philosopher " + id).start();
			
		}
		
		@Override
		public void run() {
			
			while(true) {
			
				try {
					System.out.println("Filósofo " + id + " está pensando!");
					Thread.sleep(1000);
					System.out.println("Filósofo " + id + " está com fome!");
					SolutionMonitor.pega_talher(this.id);
					System.out.println("Filósofo " + id + " está comendo!");
					Thread.sleep(200);
					System.out.println("Filósofo " + id + " terminou de comer!");
					SolutionMonitor.larga_talher(this.id);
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
			
		}
		
	}