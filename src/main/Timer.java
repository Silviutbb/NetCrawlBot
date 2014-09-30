package main;

public class Timer {
	private static double dTime;
	private static double lastTime;
	private static double avgTime;
	public Timer() {
		dTime = 0;
		lastTime = 0;
		avgTime = 0;
	}
	
	public void start(){
		lastTime=System.currentTimeMillis();
		
	}
	public void stop(){
		double currentTime=System.currentTimeMillis();
		dTime=currentTime-lastTime;
		System.out.println(dTime);
		if(avgTime==0)
			avgTime=currentTime;
		avgTime = (avgTime+dTime)/2;
	}

	
	public double getAvgTimenSeconds() {
		return avgTime/1000;
	}
	
	@SuppressWarnings("static-access")
	public void addFromExternalTimer(Timer tEx){
		this.avgTime = (this.avgTime+tEx.avgTime)/2;
	}
	
	

}
