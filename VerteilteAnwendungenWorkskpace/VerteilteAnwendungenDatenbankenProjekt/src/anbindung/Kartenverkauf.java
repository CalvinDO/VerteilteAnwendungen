package anbindung;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.mariadb.jdbc.MariaDbDataSource;

public class Kartenverkauf {

	public DataSource datasource;

	Kartenverkauf() {
		this.datasource = new MariaDbDataSource(
				"jdbc:mariadb://rechentitan.dm.hs-furtwangen.de:3306/user06?user=user06&password=d83r235888a§§06");

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

	public void getSitzplatz(int nr) {
		try (Connection connection = this.datasource.getConnection();) {
			Statement statement = connection.createStatement();

			ResultSet resultset = statement.executeQuery("SELECT * FROM `karten` WHERE `index-sitzplatz` = " + nr);
			while (resultset.next()) {
				System.out.println(resultset.toString());
				// System.out.println(resultset.getString(""));

				// System.out.println(resultset.getString("nachname"));
				System.out.println(resultset.getInt("index-sitzplatz"));
				System.out.println(resultset.getString("status"));
				System.out.println(resultset.getString("name-reservator"));
				System.out.println(resultset.getBoolean("reservierungen-annehmen"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
