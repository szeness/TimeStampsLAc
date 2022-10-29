package main;

import engines.Clockz;
import gui.Gui;

import java.sql.SQLException;

public class Main {

    public static Gui guitza = new Gui();

    public static void main(String[] args) throws SQLException {


        guitza.run();
        new Clockz().start();

    }

}
