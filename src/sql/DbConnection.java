package sql;
 


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	public static String conTimeStampDB = "jdbc:sqlite:C:\\Users\\Student\\Desktop\\TimeStampz.db";
	public static String conBenutzerLoginDB = "jdbc:sqlite:C:\\Users\\Student\\Desktop\\BenutzerLogin.db";

	public static Connection connect(String db) {
		Connection con = null;
		try {
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection(db);
			System.out.println("Verbindung zur Datenbank erfolgreich");
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			System.out.println(e + "");
		}
		return con;
	}
}
