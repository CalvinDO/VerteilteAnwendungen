package datenbankanbindung;

public class KartenverkaufTester {

	public static void main(String[] args) {
		Kartenverkauf verkauf  = new Kartenverkauf();
		
		/*
		Sitzplatz[] sitzpl�tze = verkauf.getSitzpl�tze();
		for (Sitzplatz sitzplatz : sitzpl�tze) {
			sitzplatz.print();
			
		}
		*/
		System.out.println(verkauf.toString());
		
	}
}
