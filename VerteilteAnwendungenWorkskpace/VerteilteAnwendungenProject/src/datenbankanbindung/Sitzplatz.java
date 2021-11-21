package datenbankanbindung;

public class Sitzplatz {
	public int nr;
	public Zustand status;
	public String nameReservator;

	public Sitzplatz(int nr, Zustand status, String nameReservator) {
		this.nr = nr;
		this.status = status;
		this.nameReservator = nameReservator;
	}

	public Sitzplatz(int nr, Zustand status) {
		this.nr = nr;
		this.status = status;
		this.nameReservator = null;
	}

	public void print() {
		System.out.println("Nr. = " + this.nr + "\nStatus = " + this.status.toString() + (this.nameReservator!=null ?  "\nName Reservator = " + this.nameReservator : ""));
	} 
}
