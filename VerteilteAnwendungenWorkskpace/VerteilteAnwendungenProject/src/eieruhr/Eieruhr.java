package eieruhr;

public class Eieruhr {

	public static void main(String[] args) {
		eieruhr(9000, 1000, "kaskdfkaskdjflkjslkdjf");
	}

	public static void eieruhr(long _milliseconds, long _timerIncrement, String _ausgabeText) {
		EieruhrThread eieruhrThread = new EieruhrThread(_milliseconds, _timerIncrement, _ausgabeText);
		eieruhrThread.run();
	}

	public static void eieruhrShortCoded(long _milliseconds, String _ausgabeText) {

		new Thread() {
			public void run() {
				System.out.println("EieruhrThread starts now");

				try {
					Thread.sleep(_milliseconds);

				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				System.out.println("EieruhrThread finished waiting");
			}

		}.start();
	}
}
