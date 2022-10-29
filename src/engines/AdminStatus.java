package engines;

import sql.DbConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminStatus {


        public boolean getAdminStatus(String user) throws SQLException {

            boolean stats = false;

            Connection con = sql.DbConnection.connect(DbConnection.conTimeStampDB);

            String getTime = "SELECT Admin FROM BenutzerLogin WHERE Benutzername like ?";
            PreparedStatement ps2;
            ResultSet rs;
            ps2 = con.prepareStatement(getTime);
            ps2.setString(1, Engine1.momentanerUser);
            rs = ps2.executeQuery();

            while (rs.next()) {
                if (rs.getInt("Admin") == 0) {
                    stats = false;
                }else stats = true;
            }
            return stats;
        }
    }


