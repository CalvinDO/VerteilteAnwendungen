package datenbankstrukturen;

public class SQLGenerator {

	public static void main(String[] args) {
		
		String output = "INSERT INTO `karten` (`index-sitzplatz`, `status`, `name-reservator`, `reservierungen-annehmen`) VALUES (DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
		for (int index = 1; index < 101; index++) {
			output += ", (DEFAULT, DEFAULT, DEFAULT, DEFAULT)";
		}
		output += ";";
		System.out.println(output);
	}

}
