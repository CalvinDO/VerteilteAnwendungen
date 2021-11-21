package eieruhr;

public class EieruhrThread extends Thread{
	
	public long milliseconds;
	public long timerIncrement;
	public String ausgabeText;
	
	
	public EieruhrThread(long _milliseconds, long _timerIncrement, String _ausgabeText) {
		this.milliseconds = _milliseconds;
		this.timerIncrement = _timerIncrement;
		this.ausgabeText = _ausgabeText;
	}
	
	@Override
	public void run() {
		System.out.println("EieruhrThread is running");
		
		while (this.milliseconds > this.timerIncrement) {
			try {
				Thread.sleep(this.timerIncrement);
				this.milliseconds -= this.timerIncrement;
			}catch (InterruptedException e){
				e.printStackTrace();
			}
			
			System.out.println(this.milliseconds +  "milliseconds remaining");
		}
	}
}
