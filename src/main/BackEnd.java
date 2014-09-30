package main;

public class BackEnd implements Runnable {

	public BackEnd(int tick) {
		this.tick = tick;
	}

	private Thread runner;
	private int tick;
	
	public void start(){
		this.runner=new Thread(this, "Backend");
		this.runner.start();
		Interfata.database.vacuum();
	}
	public void stop(){
		this.runner=null;
	}
	
	@Override
	public void run() {
		Thread thisThread = Thread.currentThread();
		//int counterForVacuum = 0;
		System.out.println("start");
		while (thisThread == this.runner) {
			//counterForVacuum++;
			Interfata.gr.textPane.setText(Interfata.timer.getAvgTimenSeconds()+"");
			Interfata.gr.nrOfLinks.setText(Interfata.database.getNrOfLinksInDB());
			/*if(counterForVacuum>=(10*3600/(tick/1000))){
				Interfata.database.vacuum();
				counterForVacuum=0;
			}*/			
			try {
				Thread.sleep(this.tick);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

	}

}
