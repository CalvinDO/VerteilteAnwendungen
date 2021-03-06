package datenbankanbindung;

import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

public class Kartenverkauf {

	public DataSource datasource;

	Kartenverkauf() {
		this.datasource = new MariaDbDataSource(
				"jdbc:mariadb://rechentitan.dm.hs-furtwangen.de:3306/user06?user=userx06&password=d83r235888a06");

		try (Connection connection = datasource.getConnection()) {

			System.out.println("\nVerbindung erfolgreich hergestellt");
			ResultSet rs = connection.createStatement().executeQuery("show databases");
			System.out.println("Verfügbare Datenbanken:");

			while (rs.next()) {
				System.out.println("- " + rs.getString("database"));
			}

		} catch (SQLException e) {
			System.out.println("sql error: ");
			e.printStackTrace();
		}
	}

	public Sitzplatz getSitzplatz(int nr) {
		try (Connection connection = this.datasource.getConnection();) {
			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("SELECT * FROM `karten` WHERE `index-sitzplatz` = " + nr);

			while (resultset.next()) {

				int indexSitzplatz = resultset.getInt("index-sitzplatz");
				Zustand status = Zustand.valueOf(resultset.getString("status"));
				String nameReservator = resultset.getString("name-reservator");

				if (status == Zustand.reserviert) {
					return new Sitzplatz(indexSitzplatz, status, nameReservator);
				} else {
					return new Sitzplatz(indexSitzplatz, status);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return null;
	}

	public Sitzplatz[] getSitzplätze() {
		Sitzplatz[] output = null;

		try (Connection connection = this.datasource.getConnection();) {

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("SELECT * FROM `karten`");

			ResultSet countResultset = statement.executeQuery("SELECT COUNT(*) AS `total` FROM `karten`");
			countResultset.next();
			
			int length = countResultset.getInt("total");

			output = new Sitzplatz[length];

			int index = 0;
			while (resultset.next()) {

				int indexSitzplatz = resultset.getInt("index-sitzplatz");
				Zustand status = Zustand.valueOf(resultset.getString("status"));
				String nameReservator = resultset.getString("name-reservator");


				if (status == Zustand.reserviert) {

					output[index] = new Sitzplatz(indexSitzplatz, status, nameReservator);
				} else {
					output[index] = new Sitzplatz(indexSitzplatz, status);
				}

				index++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return output;
	}

	public boolean getReservierungenAnnehmen() {
		
		boolean output = false;
		
		try (Connection connection = this.datasource.getConnection();) {

			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("SELECT * FROM `reservierungen-annehmen-status`");
			
			
			while (resultset.next()) {
				output = resultset.getBoolean("reservierungen-annehmen");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return output;
	}

	
	  public String toString() {
		  String output = "";
		  
		  output += "Reservierungen werden momentan " + (this.getReservierungenAnnehmen() ? "angenommen" : "nicht angenommen") ;
		  output += "\n Sitzplätze: \n";
		  
		  for (Sitzplatz sitzplatz : this.getSitzplätze()) {
			  output += sitzplatz.toString();
		  }
		  
		  return output;
	  }
	 
	  
	
	   public void kaufe(int nr) {
	
	  }
	 /* 
	 * public void reserviere(int nr, String name) {
	 * 
	 * }
	 * 
	 * public void kaufeReserviert(int nr, String name) {
	 * 
	 * }
	 * 
	 * public void storniere(int nr) {
	 * 
	 * }
	 * 
	 * public void hebeReservierungenAuf() {
	 * 
	 * }
	 * 
	 * public void initialisiere() {
	 * 
	 * }
	 */
}
