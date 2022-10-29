package engines;

import sql.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MADropList {

    public static ArrayList<String> mitarbeiterArrayDropD = new ArrayList<>();


    public static void dropDownzMA() throws SQLException {

        Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
        PreparedStatement ps = null;

        String getComBoxTaetigkeiten = "SELECT Benutzername FROM BenutzerLogin";
        PreparedStatement ps2;
        ResultSet rs;
        ps2 = con.prepareStatement(getComBoxTaetigkeiten);

        rs = ps2.executeQuery();

        while (rs.next()) {
            mitarbeiterArrayDropD.add(rs.getString("Benutzername"));

        }


    }

}
