package dispatcher;

public class Dispatcher {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	public static int[] execute(F _f, int _n) {
		
		Result result = new Result();
		
		
		for (int threadIndex = 0; threadIndex < _n; threadIndex++) {
			DispatcherThread newThread = new DispatcherThread(_n, _f, result);
			newThread.start();			
		}
		return new int[0];
	}
}
