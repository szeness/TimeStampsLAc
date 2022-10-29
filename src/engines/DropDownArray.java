package engines;

import main.Main;
import sql.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DropDownArray {

    public static ArrayList<String> dropdownArrayList = new ArrayList<>();


    public static void dropDownz() throws SQLException {

        dropdownArrayList.clear();

        Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
        PreparedStatement ps = null;

        String getComBoxTaetigkeiten = "SELECT * FROM comboBox";
        PreparedStatement ps2;
        ResultSet rs;
        ps2 = con.prepareStatement(getComBoxTaetigkeiten);

        rs = ps2.executeQuery();

        while (rs.next()) {
            dropdownArrayList.add(rs.getString("Auswahl"));

        }




    }

}
