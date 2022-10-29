package gui;

import engines.*;
import sql.DbConnection;

import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.text.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Objects;



public class Gui extends JFrame {

    Color buttonColor = new Color(36, 26, 42, 255);
    Color buttonTextColor = new Color(173, 123, 127, 255);


    public static ImageIcon icon = new ImageIcon(Objects.requireNonNull(
            Gui.class.getResource("img/csm_AdobeStock_55007941_511db4fa94h.jpeg")));

    public static Gui framzz;
    public static JTextArea textAreaZeit;
    public static JTextArea textAreaZeitReal;
    public static JLabel wp1;

    JButton createUser = new JButton("MA Anlegen");
    JButton buttonNLogin = new JButton("Anmelden");
    JButton buttonAdmin = new JButton("Admin");
    JButton buttonStats = new JButton("Statistiken");
    JButton buttonCurrentLogins = new JButton("Eingelogte MA");
    JButton buttonUserSummary = new JButton("Einlogzeiten");
    JButton benutzerVerwaltenButton = new JButton("Benutzer verwalten");
    JButton dropDownVerwalten = new JButton("Dropdown verwalten");
    JButton addDropTätigkeit = new JButton("Hinzufügen");
    JButton delDropTätigkeit = new JButton("Löschen");
    public static JComboBox<String> maDropList = new JComboBox();
    public static JComboBox<String> dropDownAkti = new JComboBox();
    JLabel nameRegText = new JLabel("Vorname");
    JTextField nameReg = new JTextField();
    JLabel lastNameRegText = new JLabel("Nachname");
    JTextField lastNameReg = new JTextField();
    JLabel usernameRegText = new JLabel("Benutzername");
    JTextField usernameReg = new JTextField();
    JLabel passwordRegText = new JLabel("Passwort");
    JPasswordField passwordReg = new JPasswordField();
    JLabel cbTätigkeitText = new JLabel("Tätigkeit");
    JTextField cbTätigkeitFeld = new JTextField();
    //#####################################################################
    JLabel passwordRegText2 = new JLabel("Passwort wiederholen");
    JLabel adminCheckboxText = new JLabel("Adminrechte");
    JCheckBox adminCheckBox = new JCheckBox();
    JPasswordField passwordReg2 = new JPasswordField();

    JButton deleteUserButton = new JButton("MA entfernen");

    public void run() throws SQLException {



        DropDownArray.dropDownz();
        MADropList.dropDownzMA();

        JComboBox<String> maDropListDeleteUser = new JComboBox<>(MADropList.mitarbeiterArrayDropD.toArray(new String[0]));

        framzz = new Gui();

        framzz.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        framzz.setSize(640, 480);
        framzz.setLayout(null);
        framzz.setBackground(new Color(96, 23, 23));
        framzz.setVisible(true);
        framzz.toFront();
        framzz.setState(Frame.NORMAL);

        textAreaZeitReal = new JTextArea();
        textAreaZeitReal.setSize(550, 60);
        textAreaZeitReal.setBackground(new Color(158, 245, 191, 0));
        textAreaZeitReal.setLocation(65, 1);
        textAreaZeitReal.setForeground(new Color(44, 172, 190, 255));
        textAreaZeitReal.setFont(new Font("Arial", Font.ITALIC, 25));
        textAreaZeitReal.setFocusable(false);
        textAreaZeitReal.setVisible(true);
        textAreaZeitReal.requestFocus(false);
        framzz.add(textAreaZeitReal);


        textAreaZeit = new JTextArea();
        textAreaZeit.setSize(480, 140);
        textAreaZeit.setBackground(new Color(33, 62, 80, 255));
        textAreaZeit.setLocation(65, 70);
        textAreaZeit.setForeground(new Color(202, 255, 213));
        textAreaZeit.setFont(new Font("Arial", Font.PLAIN, 17));
        textAreaZeit.setFocusable(false);
        textAreaZeit.setVisible(true);
        textAreaZeit.requestFocus(false);
        framzz.add(textAreaZeit);



        JTextArea textPaneStats = new JTextArea();
        textPaneStats.setBounds(265,15,340,370);
        textPaneStats.setVisible(false);
        textPaneStats.setBackground(new Color(40, 32, 45, 255));
        textPaneStats.setForeground(buttonTextColor);
        textPaneStats.setFocusable(true);


        JScrollPane js = new JScrollPane(textPaneStats);
        js.setBounds(265,15,340,370);
        js.setVisible(false);
        js.setBackground(new Color(40, 32, 45, 255));
        js.setForeground(buttonTextColor);
        js.setFocusable(false);
        js.setOpaque(false);
        js.getViewport().setOpaque(false);
        framzz.add(js);
        js.setVisible(false);




        dropDownAkti = new JComboBox<>(DropDownArray.dropdownArrayList.toArray(new String[0]));
        dropDownAkti.setBounds(160, 240, 140, 20);
        dropDownAkti.setVisible(false);
        framzz.add(dropDownAkti);


        maDropList = new JComboBox<>(MADropList.mitarbeiterArrayDropD.toArray(new String[0]));
        maDropList.setBounds(125, 228, 122, 20);
        maDropList.setVisible(false);
        framzz.add(maDropList);



        JButton startButton = new JButton("Beginn");
        startButton.setSize(120,35);
        startButton.setLocation(160,320);
        startButton.setFont(new Font("Arial", Font.BOLD, 12));
        startButton.setFocusable(false);
        startButton.setBackground(buttonColor);
        startButton.setForeground(buttonTextColor);
        framzz.add(startButton);
        startButton.setVisible(false);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //textAreaZeit.append("\n You selected " + dropDownAkti.getItemAt(dropDownAkti.getSelectedIndex()));


                try {
                    if (!new GetStatus().getStatus(Engine1.momentanerUser)) {
                        textAreaZeit.setText("");
                        Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                        PreparedStatement ps = null;
                        try {
                            String setTime = "INSERT INTO TimeStampz(id, Benutzername, StartZeit, Tätigkeit, Status) VALUES(null, ?, current_timestamp, ?, 1) ";
                            ps = con.prepareStatement(setTime);
                            ps.setString(1, Engine1.momentanerUser);
                            ps.setString(2, dropDownAkti.getItemAt(dropDownAkti.getSelectedIndex()));
                            ps.execute();


                            String getTime = "SELECT * FROM TimeStampz WHERE StartZeit = (SELECT MAX(StartZeit)  FROM TimeStampz) AND Benutzername like ?";
                            PreparedStatement ps2;
                            ResultSet rs;
                            ps2 = con.prepareStatement(getTime);
                            ps2.setString(1, Engine1.momentanerUser);

                            rs = ps2.executeQuery();

                            while (rs.next()) {

                                textAreaZeit.setText("\nBeginn:    " + rs.getString("StartZeit"));
                                textAreaZeit.append("\nTätigkeit: " + rs.getString("Tätigkeit"));
                                textAreaZeit.setVisible(false);
                                textAreaZeit.setVisible(true);

                            }


                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                            System.out.println("lol");

                        }
                    } else {

                        Connection con = DbConnection.connect(DbConnection.conTimeStampDB);

                        String getTime = "SELECT * FROM TimeStampz WHERE Status like 1 AND Benutzername like ?";
                        PreparedStatement ps2;
                        ResultSet rs;
                        ps2 = con.prepareStatement(getTime);
                        ps2.setString(1, Engine1.momentanerUser);

                        rs = ps2.executeQuery();

                        while (rs.next()) {

                            textAreaZeit.setText("");
                            textAreaZeit.setText("\nBeginn:    " + rs.getString("StartZeit"));
                            textAreaZeit.append("\nTätigkeit: " + rs.getString("Tätigkeit"));
                            textAreaZeit.append("\n\n          Laufende Tätigkeit muss zuerst beendet werden");
                            Engine1.momentaneID = rs.getInt("id");
                        }
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    System.out.println("lol");
                }




            }

            });
///xende xstart
        JButton endeButton = new JButton("Ende");
        endeButton.setSize(120,35);
        endeButton.setLocation(360,320);
        endeButton.setFont(new Font("Arial", Font.BOLD, 12));
        endeButton.setFocusable(false);
        endeButton.setBackground(buttonColor);
        endeButton.setForeground(buttonTextColor);
        framzz.add(endeButton);
        endeButton.setVisible(false);
        endeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaZeit.setVisible(false);
                textAreaZeit.setVisible(true);

                try {
                    if (new GetStatus().getStatus(Engine1.momentanerUser)) {
                        Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                        PreparedStatement ps = null;

                        String setTime = "UPDATE TimeStampz SET EndZeit = current_timestamp, Status = 0 WHERE Status = 1 AND Benutzername = ?";
                        ps = con.prepareStatement(setTime);
                        ps.setString(1, Engine1.momentanerUser);
                        ps.execute();

                        String getTime = "SELECT * FROM TimeStampz WHERE Benutzername like ? AND Status like 0 order by StartZeit desc limit 1";
                        PreparedStatement ps2;
                        ResultSet rs;
                        ps2 = con.prepareStatement(getTime);
                        ps2.setString(1, Engine1.momentanerUser);
                        rs = ps2.executeQuery();

                        while (rs.next()) {
                            textAreaZeit.setText("\nBeginn: " + rs.getString("StartZeit"));
                            textAreaZeit.append("\nEnde:    " + rs.getString("EndZeit"));
                            textAreaZeit.append("\nTätigkeit: " + rs.getString("Tätigkeit"));

                        }


                    } else {
                        textAreaZeit.setText("\nZeiterfassung muss zuerst gestartet sein");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }});

        JLabel usernameText = new JLabel("Benutzername");
        usernameText.setLocation(115,215);
        usernameText.setSize(150,25);
        usernameText.setBackground(new Color(35, 80, 96, 255));
        usernameText.setForeground(Color.WHITE);
        usernameText.setVisible(true);
        framzz.add(usernameText);


        JLabel passwordText = new JLabel("Passwort");
        passwordText.setLocation(143,250);
        passwordText.setSize(150,25);
        passwordText.setBackground(Color.BLACK);
        passwordText.setForeground(Color.WHITE);
        passwordText.setVisible(true);
        framzz.add(passwordText);

        JPasswordField passwordL = new JPasswordField();
        passwordL.setLocation(203, 250);
        passwordL.setSize(228, 25);
        passwordL.setVisible(true);
        passwordL.setEchoChar('*');
        framzz.add(passwordL);


        JTextField usernameL = new JTextField();
        usernameL.setLocation(203, 215);
        usernameL.setSize(228, 25);
        usernameL.setVisible(true);
        framzz.add(usernameL);


        JButton logoutButton = new JButton("Ausloggen");
        logoutButton.setSize(117,35);
        logoutButton.setLocation(10,400);
        logoutButton.setFont(new Font("Arial", Font.BOLD, 12));
        logoutButton.setBackground(buttonColor);
        logoutButton.setForeground(buttonTextColor);
        logoutButton.setFocusable(false);
        framzz.add(logoutButton);
        logoutButton.setVisible(false);
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                usernameText.setVisible(true);
                usernameL.setVisible(true);
                passwordText.setVisible(true);
                passwordL.setVisible(true);
                buttonNLogin.setVisible(true);

                startButton.setVisible(false);
                endeButton.setVisible(false);
                dropDownAkti.setVisible(false);
                buttonAdmin.setVisible(false);
                benutzerVerwaltenButton.setVisible(false);
                logoutButton.setVisible(false);
                cbTätigkeitFeld.setVisible(false);
                cbTätigkeitText.setVisible(false);
                addDropTätigkeit.setVisible(false);
                delDropTätigkeit.setVisible(false);
                nameReg.setVisible(false);
                nameRegText.setVisible(false);
                lastNameReg.setVisible(false);
                lastNameRegText.setVisible(false);
                usernameReg.setVisible(false);
                usernameRegText.setVisible(false);
                passwordReg.setVisible(false);
                passwordRegText.setVisible(false);
                passwordRegText2.setVisible(false);
                passwordReg2.setVisible(false);
                createUser.setVisible(false);
                dropDownVerwalten.setVisible(false);
                buttonStats.setVisible(false);
                textPaneStats.setVisible(false);
                buttonCurrentLogins.setVisible(false);
                buttonUserSummary.setVisible(false);
                maDropList.setVisible(false);
                js.setVisible(false);
                maDropListDeleteUser.setVisible(false);
                deleteUserButton.setVisible(false);
                adminCheckboxText.setVisible(false);
                adminCheckBox.setVisible(false);


                usernameL.setText("");
                passwordL.setText("");



                wp1.setVisible(false);
                wp1.setVisible(true);


                textAreaZeit.setText("");
                textAreaZeit.setVisible(false);
                textAreaZeit.setVisible(true);

                textAreaZeitReal.setText("");
                textAreaZeitReal.setVisible(false);
                textAreaZeitReal.setVisible(true);
                Clockz.adminBoo = false;
                new Clockz().start();


            }
        });

        /*buttonStats.setSize(120,35);
        buttonStats.setLocation(500,361);
        buttonStats.setFont(new Font("Arial", Font.BOLD, 12));
        buttonStats.setBackground(buttonColor);
        buttonStats.setForeground(buttonTextColor);
        framzz.add(buttonStats);
        buttonStats.setVisible(false);*/
///xstatistik
        buttonCurrentLogins.setSize(120,35);
        buttonCurrentLogins.setLocation(125,290);
        buttonCurrentLogins.setFont(new Font("Arial", Font.BOLD, 12));
        buttonCurrentLogins.setBackground(buttonColor);
        buttonCurrentLogins.setForeground(buttonTextColor);
        framzz.add(buttonCurrentLogins);
        buttonCurrentLogins.setVisible(false);
        buttonCurrentLogins.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                textPaneStats.setText("");

                String summaryQUery = "SELECT Benutzername, Tätigkeit FROM TimeStampz WHERE Status like 1";
                Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                PreparedStatement ps = null;
                ResultSet rs;
                try {
                    ps = con.prepareStatement(summaryQUery);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        textPaneStats.append("\n" + rs.getString("Benutzername"));
                        textPaneStats.append(" -  "+rs.getString("Tätigkeit"));
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });




        buttonUserSummary.setSize(120,35);
        buttonUserSummary.setLocation(125,250);
        buttonUserSummary.setFont(new Font("Arial", Font.BOLD, 12));
        buttonUserSummary.setBackground(buttonColor);
        buttonUserSummary.setForeground(buttonTextColor);
        framzz.add(buttonUserSummary);
        buttonUserSummary.setVisible(false);
        buttonUserSummary.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textAreaZeitReal.setVisible(false);
                textPaneStats.setText("");

                String summaryQUery = "SELECT time(strftime('%s',EndZeit) - strftime('%s',StartZeit), 'unixepoch') as Gesamtzeit, Tätigkeit, StartZeit FROM TimeStampz WHERE Benutzername like ? ORDER by StartZeit desc";
                Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                PreparedStatement ps = null;
                ResultSet rs;
                try {
                    ps = con.prepareStatement(summaryQUery);
                    ps.setString(1, maDropList.getItemAt(maDropList.getSelectedIndex()));
                    rs = ps.executeQuery();
                    textPaneStats.append("Beginn                         Dauer        Tätigkeit ");
                    while (rs.next()) {
                        textPaneStats.append("\n"+ rs.getString("StartZeit"));
                        textPaneStats.append(" - " + rs.getString("Gesamtzeit"));
                        textPaneStats.append(" - "+ rs.getString("Tätigkeit"));

                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
            });

        buttonStats.setSize(120,35);
        buttonStats.setLocation(500,361);
        buttonStats.setFont(new Font("Arial", Font.BOLD, 12));
        buttonStats.setBackground(buttonColor);
        buttonStats.setForeground(buttonTextColor);
        framzz.add(buttonStats);
        buttonStats.setVisible(false);
        buttonStats.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clockz.adminBoo = true;
                textPaneStats.setVisible(true);
                js.setVisible(true);
                buttonStats.setVisible(false);
                textAreaZeitReal.setVisible(false);
                textAreaZeit.setVisible(false);

                maDropList.setVisible(true);
                buttonUserSummary.setVisible(true);
                buttonCurrentLogins.setVisible(true);
                textAreaZeitReal.setVisible(true);
                textAreaZeitReal.setVisible(false);
                maDropListDeleteUser.setVisible(false);
                deleteUserButton.setVisible(false);

            }
        });



        buttonAdmin.setSize(120,35);
        buttonAdmin.setLocation(500,400);
        buttonAdmin.setFont(new Font("Arial", Font.BOLD, 12));
        buttonAdmin.setBackground(buttonColor);
        buttonAdmin.setForeground(new Color(168, 46, 56, 255));
        framzz.add(buttonAdmin);
        buttonAdmin.setVisible(false);
        buttonAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Clockz.adminBoo = true;
                buttonAdmin.setVisible(true);
                endeButton.setVisible(false);
                startButton.setVisible(false);
                dropDownAkti.setVisible(false);
                textAreaZeitReal.setVisible(false);
                nameReg.setVisible(false);
                nameRegText.setVisible(false);
                lastNameReg.setVisible(false);
                lastNameRegText.setVisible(false);
                usernameReg.setVisible(false);
                usernameRegText.setVisible(false);
                passwordReg.setVisible(false);
                passwordRegText.setVisible(false);
                passwordRegText2.setVisible(false);
                passwordReg2.setVisible(false);
                createUser.setVisible(false);
                addDropTätigkeit.setVisible(false);
                cbTätigkeitFeld.setVisible(false);
                cbTätigkeitText.setVisible(false);
                dropDownAkti.setVisible(false);
                delDropTätigkeit.setVisible(false);
                textPaneStats.setVisible(false);
                js.setVisible(false);
                addDropTätigkeit.setVisible(false);
                delDropTätigkeit.setVisible(false);
                maDropList.setVisible(false);
                buttonUserSummary.setVisible(false);
                buttonCurrentLogins.setVisible(false);
                maDropList.setVisible(false);
                maDropListDeleteUser.setVisible(false);
                deleteUserButton.setVisible(false);

                benutzerVerwaltenButton.setVisible(true);
                dropDownVerwalten.setVisible(true);
                buttonStats.setVisible(true);
                textAreaZeitReal.setVisible(false);
                adminCheckboxText.setVisible(false);
                adminCheckBox.setVisible(false);

            }
        });
///xdropdown
        dropDownVerwalten.setSize(165,35);
        dropDownVerwalten.setLocation(330,400);
        dropDownVerwalten.setFont(new Font("Arial", Font.BOLD, 12));
        dropDownVerwalten.setBackground(buttonColor);
        dropDownVerwalten.setForeground(buttonTextColor);
        dropDownVerwalten.setFocusable(false);
        framzz.add(dropDownVerwalten);
        dropDownVerwalten.setVisible(false);
        dropDownVerwalten.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Clockz.adminBoo = true;
                textAreaZeitReal.setText("");
                benutzerVerwaltenButton.setVisible(true);
                usernameText.setVisible(false);
                usernameL.setVisible(false);
                passwordText.setVisible(false);
                passwordL.setVisible(false);
                buttonNLogin.setVisible(false);
                textAreaZeitReal.setVisible(false);
                textAreaZeit.setVisible(false);
                textPaneStats.setVisible(false);
                js.setVisible(false);
                addDropTätigkeit.setVisible(false);
                delDropTätigkeit.setVisible(false);
                maDropList.setVisible(false);

                addDropTätigkeit.setVisible(true);
                cbTätigkeitFeld.setVisible(true);
                cbTätigkeitText.setVisible(true);
                dropDownAkti.setVisible(true);
                delDropTätigkeit.setVisible(true);
                buttonCurrentLogins.setVisible(false);
                buttonUserSummary.setVisible(false);
                maDropListDeleteUser.setVisible(false);
                deleteUserButton.setVisible(false);
                buttonStats.setVisible(false);
                textPaneStats.setVisible(false);
                nameReg.setVisible(false);
                nameRegText.setVisible(false);
                lastNameReg.setVisible(false);
                lastNameRegText.setVisible(false);
                usernameReg.setVisible(false);
                usernameRegText.setVisible(false);
                passwordReg.setVisible(false);
                passwordRegText.setVisible(false);
                passwordRegText2.setVisible(false);
                passwordReg2.setVisible(false);
                createUser.setVisible(false);
                adminCheckboxText.setVisible(false);
                adminCheckBox.setVisible(false);




                Clockz.adminBoo = true;
                textAreaZeitReal.setVisible(false);


            }
        });


        addDropTätigkeit.setSize(120,35);
        addDropTätigkeit.setLocation(340,200);
        addDropTätigkeit.setFont(new Font("Arial", Font.BOLD, 12));
        addDropTätigkeit.setFocusable(false);
        addDropTätigkeit.setBackground(buttonColor);
        addDropTätigkeit.setForeground(buttonTextColor);
        framzz.add(addDropTätigkeit);
        addDropTätigkeit.setVisible(false);
        addDropTätigkeit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            String addTatigkeitQuery = "INSERT INTO comboBox(Auswahl) VALUES(?)";
                Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                PreparedStatement ps = null;

                try {
                    ps = con.prepareStatement(addTatigkeitQuery);

                    if(cbTätigkeitFeld.getText().length() == 0) {
                        textAreaZeitReal.setVisible(false);
                        textAreaZeitReal.setVisible(true);
                        textAreaZeitReal.setText("Tätigkeitsfeld muss gefüllt sein");
                    } else {
                        ps.setString(1, cbTätigkeitFeld.getText());
                        textAreaZeitReal.setText('"' + cbTätigkeitFeld.getText() + '"' + " wurde hinzugefügt");
                        textAreaZeitReal.setVisible(false);
                        textAreaZeitReal.setVisible(true);

                        ps.execute();

                    }
                dropDownAkti.addItem(cbTätigkeitFeld.getText());

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }



            }
        });

        delDropTätigkeit.setSize(100,35);
        delDropTätigkeit.setLocation(176,270);
        delDropTätigkeit.setFont(new Font("Arial", Font.BOLD, 12));
        delDropTätigkeit.setFocusable(false);
        delDropTätigkeit.setBackground(buttonColor);
        delDropTätigkeit.setForeground(new Color(168, 46, 56, 255));
        framzz.add(delDropTätigkeit);
        delDropTätigkeit.setVisible(false);
        delDropTätigkeit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String deleteTätigkeitQuery = "DELETE FROM comboBox WHERE Auswahl like ?";
                Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                PreparedStatement ps = null;

                try {
                    ps = con.prepareStatement(deleteTätigkeitQuery);
                    ps.setString(1, dropDownAkti.getItemAt(dropDownAkti.getSelectedIndex()));
                    ps.execute();
                    DropDownArray.dropDownz();
                    textAreaZeitReal.setText('"' + dropDownAkti.getItemAt(dropDownAkti.getSelectedIndex()) + '"' + " wurde entfernt");
                    textAreaZeitReal.setVisible(false);
                    textAreaZeitReal.setVisible(true);

                    dropDownAkti.removeItem(dropDownAkti.getItemAt(dropDownAkti.getSelectedIndex()));


                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

            }
        });

        cbTätigkeitText.setLocation(260, 170); //150, 215
        cbTätigkeitText.setSize(150, 25);
        cbTätigkeitText.setBackground(Color.BLACK);
        cbTätigkeitText.setForeground(Color.WHITE);
        cbTätigkeitText.setVisible(false);
        framzz.add(cbTätigkeitText);

        cbTätigkeitFeld.setLocation(315, 170); // 237, 215
        cbTätigkeitFeld.setSize(180, 25);
        cbTätigkeitFeld.setVisible(false);
        framzz.add(cbTätigkeitFeld);
//xbenuterverw
        benutzerVerwaltenButton.setSize(155,35);
        benutzerVerwaltenButton.setLocation(174,400);
        benutzerVerwaltenButton.setFont(new Font("Arial", Font.BOLD, 12));
        benutzerVerwaltenButton.setBackground(buttonColor);
        benutzerVerwaltenButton.setForeground(buttonTextColor);
        benutzerVerwaltenButton.setFocusable(false);
        framzz.add(benutzerVerwaltenButton);
        benutzerVerwaltenButton.setVisible(false);
        benutzerVerwaltenButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Clockz.adminBoo = true;
                textAreaZeitReal.setText("");
                benutzerVerwaltenButton.setVisible(true);
                usernameText.setVisible(false);
                usernameL.setVisible(false);
                passwordText.setVisible(false);
                passwordL.setVisible(false);
                buttonNLogin.setVisible(false);
                textAreaZeitReal.setVisible(false);
                textAreaZeit.setVisible(false);
                textPaneStats.setVisible(false);
                js.setVisible(false);
                addDropTätigkeit.setVisible(false);
                delDropTätigkeit.setVisible(false);
                maDropList.setVisible(false);
                buttonCurrentLogins.setVisible(false);
                buttonUserSummary.setVisible(false);
                maDropList.setVisible(false);

                dropDownAkti.setVisible(false);
                cbTätigkeitFeld.setVisible(false);
                cbTätigkeitText.setVisible(false);
                addDropTätigkeit.setVisible(false);
                delDropTätigkeit.setVisible(false);
                buttonStats.setVisible(false);
                textPaneStats.setVisible(false);

                nameReg.setVisible(true);
                nameRegText.setVisible(true);
                lastNameReg.setVisible(true);
                lastNameRegText.setVisible(true);
                usernameReg.setVisible(true);
                usernameRegText.setVisible(true);
                passwordReg.setVisible(true);
                passwordRegText.setVisible(true);
                passwordRegText2.setVisible(true);
                passwordReg2.setVisible(true);
                createUser.setVisible(true);
                deleteUserButton.setVisible(true);
                maDropListDeleteUser.setVisible(true);
                adminCheckboxText.setVisible(true);
                adminCheckBox.setVisible(true);



                Clockz.adminBoo = true;
                textAreaZeitReal.setVisible(false);




            }
        });

//xregbutton
        createUser.setSize(120,35);
        createUser.setLocation(280-90,330);
        createUser.setFont(new Font("Arial", Font.BOLD, 12));
        createUser.setFocusable(false);
        createUser.setBackground(buttonColor);
        createUser.setForeground(buttonTextColor);
        framzz.add(createUser);
        createUser.setVisible(false);
        createUser.addActionListener(new ActionListener() {
         @Override
         public void actionPerformed(ActionEvent e) {

             textAreaZeitReal.setVisible(true);
             textAreaZeitReal.setText("");

             if (usernameReg.getText().length() != 0 && nameReg.getText().length() != 0 &&
                     lastNameReg.getText().length() != 0 && passwordReg.getText().length() != 0) {
                 if (passwordReg.getText().length() >= 6) {
                     if (passwordReg.getText().equals(passwordReg2.getText())) {


                         Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                         PreparedStatement ps = null;
                         try {
                             String sql = "INSERT INTO BenutzerLogin(Name, Vorname, Benutzername, Passwort, Admin) VALUES(?,?,?,?,?) ";
                             ps = con.prepareStatement(sql);
                             ps.setString(1, lastNameReg.getText());
                             ps.setString(2, nameReg.getText());
                             ps.setString(3, usernameReg.getText());
                             ps.setString(4, passwordReg.getText());
                             ps.setString(5, String.valueOf((adminCheckBox.isSelected() ? 1 : 0)));
                             ps.execute();
                             textAreaZeitReal.setText("Erfolgreich registriert!");
                             textAreaZeitReal.setVisible(false);
                             textAreaZeitReal.setVisible(true);

                             nameReg.setText("");

                             lastNameReg.setText("");;

                             usernameReg.setText("");

                             passwordReg.setText("");

                             passwordReg2.setText("");


                         } catch (Exception a) {
                             //konsoleArea.setText(a.toString());
                             textAreaZeitReal.setVisible(false);
                             textAreaZeitReal.setVisible(true);
                             textAreaZeitReal.append("\nDer Benutzername ist bereits vergeben");
                         }
                     } else {
                         textAreaZeitReal.setVisible(false);
                         textAreaZeitReal.setVisible(true);
                         textAreaZeitReal.setText("Passwörter stimmen nicht überein");
                     }
                 } else {
                     textAreaZeitReal.setVisible(false);
                     textAreaZeitReal.setVisible(true);
                     textAreaZeitReal.setText("Das Passwort muss mindestens 6 Zeichen lang sein");
                 }
             } else {
                 textAreaZeitReal.setVisible(false);
                 textAreaZeitReal.setVisible(true);
                 textAreaZeitReal.setText("Alle Felder muessen gefuellt sein");
             }
         }
     });

        maDropListDeleteUser.setBounds(420, 252, 140, 20);
        maDropListDeleteUser.setVisible(false);
        framzz.add(maDropListDeleteUser);


        deleteUserButton.setSize(120,35);
        deleteUserButton.setLocation(427,275);
        deleteUserButton.setFont(new Font("Arial", Font.BOLD, 12));
        deleteUserButton.setFocusable(false);
        deleteUserButton.setBackground(buttonColor);
        deleteUserButton.setForeground(new Color(168, 46, 56, 255));
        framzz.add(deleteUserButton);
        deleteUserButton.setVisible(false);
        deleteUserButton.addActionListener(new ActionListener() {
                   @Override
                   public void actionPerformed(ActionEvent e) {

                       String deleteUserQuery = "DELETE FROM BenutzerLogin WHERE Benutzername like ?; DELETE FROM TimeStampz WHERE Benutzername like ?";
                       Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                       PreparedStatement ps = null;

                       try {
                           ps = con.prepareStatement(deleteUserQuery);
                           ps.setString(1, maDropListDeleteUser.getItemAt(maDropListDeleteUser.getSelectedIndex()));
                           ps.execute();

                           MADropList.dropDownzMA();
                           String deleteUserQuery2 = "DELETE FROM TimeStampz WHERE Benutzername like ?";


                           try {
                               ps = con.prepareStatement(deleteUserQuery2);
                               ps.setString(1, maDropListDeleteUser.getItemAt(maDropListDeleteUser.getSelectedIndex()));
                               ps.execute();
                               MADropList.dropDownzMA();


                               textAreaZeitReal.setText('"' + maDropListDeleteUser.getItemAt(maDropListDeleteUser.getSelectedIndex()) + '"' + " wurde entfernt");
                               textAreaZeitReal.setVisible(false);
                               textAreaZeitReal.setVisible(true);
                               textAreaZeitReal.append("\nÄnderungen nach Neustart wirksam");

                           } catch (SQLException ex) {
                               ex.printStackTrace();
                           }
                           repaint();
                       } catch (SQLException throwables) {
                           throwables.printStackTrace();
                       }

                   }
                   });

////xReg//////////////////////////////////////
                nameRegText.setLocation(150 - 133, 170); //150, 215
                nameRegText.setSize(150, 25);
                nameRegText.setBackground(Color.BLACK);
                nameRegText.setForeground(Color.WHITE);
                nameRegText.setVisible(false);
                framzz.add(nameRegText);

                nameReg.setLocation(280-133, 170); // 237, 215
                nameReg.setSize(228, 25);
                nameReg.setVisible(false);
                framzz.add(nameReg);

                lastNameRegText.setLocation(150-133, 200);    //150, 245
                lastNameRegText.setSize(150, 25);
                lastNameRegText.setBackground(Color.BLACK);
                lastNameRegText.setForeground(Color.WHITE);
                lastNameRegText.setVisible(false);
                framzz.add(lastNameRegText);

                lastNameReg.setLocation(280-133, 200);    //237, 160
                lastNameReg.setSize(228, 25);
                lastNameReg.setVisible(false);
                framzz.add(lastNameReg);

                usernameRegText.setLocation(150-133, 230);    //150, 225
                usernameRegText.setSize(150, 25);
                usernameRegText.setBackground(Color.BLACK);
                usernameRegText.setForeground(Color.WHITE);
                usernameRegText.setVisible(false);
                framzz.add(usernameRegText);

                usernameReg.setLocation(280-133, 230);
                usernameReg.setSize(228, 25);
                usernameReg.setVisible(false);
                framzz.add(usernameReg);

                passwordRegText.setLocation(150-133, 260);    //150, 220
                passwordRegText.setSize(150, 25);
                passwordRegText.setBackground(Color.BLACK);
                passwordRegText.setForeground(Color.WHITE);
                passwordRegText.setVisible(false);
                framzz.add(passwordRegText);

                passwordReg.setLocation(280-133, 260); //237, 220     237, 250
                passwordReg.setSize(228, 25);
                passwordReg.setVisible(false);
                passwordReg.setEchoChar('*');
                framzz.add(passwordReg);

                //###########################################
                passwordRegText2.setLocation(150-133, 290);
                passwordRegText2.setSize(150, 25);
                passwordRegText2.setBackground(Color.BLACK);
                passwordRegText2.setForeground(Color.WHITE);
                passwordRegText2.setVisible(false);
                framzz.add(passwordRegText2);

                passwordReg2.setLocation(280-133, 290);    //237, 250
                passwordReg2.setSize(228, 25);
                passwordReg2.setVisible(false);
                passwordReg2.setEchoChar('*');
                framzz.add(passwordReg2);


                adminCheckboxText.setLocation(150-133, 320);
                adminCheckboxText.setSize(150, 25);
                adminCheckboxText.setBackground(new Color(12,12,12, 0));
                adminCheckboxText.setForeground(Color.WHITE);
                adminCheckboxText.setVisible(false);
                framzz.add(adminCheckboxText);

                adminCheckBox.setLocation(280-133,320);
                adminCheckBox.setSize(18,18);
                adminCheckBox.setBackground(Color.BLACK);
                adminCheckBox.setVisible(false);
                framzz.add(adminCheckBox);
//xlogin
                buttonNLogin.setSize(120, 35);
                buttonNLogin.setLocation(245, 285);
                buttonNLogin.setFont(new Font("Arial", Font.BOLD, 12));
                buttonNLogin.setBackground(buttonColor);
                buttonNLogin.setForeground(buttonTextColor);
                framzz.add(buttonNLogin);
                buttonNLogin.setVisible(true);
                buttonNLogin.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {


                        String loginRequest = "Select * from BenutzerLogin where Benutzername LIKE ? AND Passwort like ?;";

                        Connection connectToDataBase = sql.DbConnection.connect(DbConnection.conTimeStampDB);
                        PreparedStatement ps = null;
                        ResultSet rs;

                        try {
                            ps = connectToDataBase.prepareStatement(loginRequest);
                            ps.setString(1, usernameL.getText());
                            ps.setString(2, passwordL.getText());
                            rs = ps.executeQuery();

                            if (rs.next()) {

                                Engine1.momentanerUser = usernameL.getText();
                                textAreaZeit.setText("                                 Welcome " + usernameL.getText());


                                usernameText.setVisible(false);
                                usernameL.setVisible(false);
                                passwordText.setVisible(false);
                                passwordL.setVisible(false);
                                //buttonNReg.setVisible(false);
                                buttonNLogin.setVisible(false);
                                //buttonBacktoLogin.setVisible(false);
                                // konsoleArea.setVisible(false);
                                //  konsoleArea.setText("");

                                startButton.setVisible(true);
                                endeButton.setVisible(true);
                                dropDownAkti.setVisible(true);
                                logoutButton.setVisible(true);
                                textAreaZeit.setVisible(false);
                                textAreaZeit.setVisible(true);

                                framzz.toFront();
                                framzz.requestFocus();

                                if (new AdminStatus().getAdminStatus(Engine1.momentanerUser)) {

                                    buttonAdmin.setVisible(true);

                                }

                                if (new GetStatus().getStatus(Engine1.momentanerUser)) {

                                    String loginZeit = "SELECT time(strftime('%s', 'now') - strftime('%s',t.StartZeit), 'unixepoch') as Gesamtzeit, t.Tätigkeit, t.StartZeit, b.Name, b.Vorname FROM TimeStampz AS t JOIN BenutzerLogin AS b ON b.Benutzername = t.Benutzername WHERE t.Benutzername like ? AND Status like 1 limit 1;";
                                    Connection con = DbConnection.connect(DbConnection.conTimeStampDB);
                                    PreparedStatement ps5 = null;
                                    ResultSet rs5;
                                    try {
                                        ps5 = con.prepareStatement(loginZeit);
                                        ps5.setString(1, Engine1.momentanerUser);
                                        rs5 = ps5.executeQuery();

                                        while (rs5.next()) {
                                            textAreaZeit.append("\n\n" + "Name: "+ rs5.getString("Vorname"));
                                            textAreaZeit.append(" " + rs5.getString("Name"));
                                            textAreaZeit.append("\n" + "Aktuelle Tätigkeit: "+ rs5.getString("Tätigkeit"));
                                            textAreaZeit.append("\nBeginn: " + rs5.getString("StartZeit"));
                                            textAreaZeit.append("\nDauer: " + rs5.getString("Gesamtzeit"));

                                        }

                                    } catch (SQLException throwables) {
                                        throwables.printStackTrace();
                                    }
                                }




                        //JOptionPane.showMessageDialog(null, "Welcome "+ nameField);

                        } else {
                                //System.out.println("Falsche anmelde daten");
                                textAreaZeit.append("Falsche Anmeldedaten\n");
                            }

                            //while(rs.next()) {
                            //	String Name = rs.getString("Name");
                            //JOptionPane.showMessageDialog(null, Name);
                            //	System.out.println("Name :" + Name);
                            //}
                        } catch (Exception a) {
                            System.out.println(a.toString());
                        } finally {
                            try {
                                //rs.close();
                                ps.close();
                                connectToDataBase.close();
                            } catch (Exception a) {
                                System.out.println(a.toString());
                            }
                        }

                        //System.out.println("Login Button Testen");
                    }
                });


                wp1 = new JLabel();
                wp1.setBounds(0, -4, 640, 480);
                wp1.setBackground(new Color(96, 23, 23));
                wp1.setIcon(icon);
                framzz.add(wp1);
                wp1.setVisible(true);
                wp1.setVisible(false);
                wp1.setVisible(true);


            }

        }
