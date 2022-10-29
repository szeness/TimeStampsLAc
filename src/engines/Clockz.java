package engines;

import gui.Gui;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;


public class Clockz extends Thread {

        public static boolean adminBoo = false;



        SimpleDateFormat formatDate = new SimpleDateFormat("HH:mm:ss            " +
                "                       dd.MM.yyyy");


            public void run() {
                Gui.textAreaZeitReal.setVisible(false);
                while (!adminBoo) {
                    try {
                        Date currDate = new Date();
                        Gui.textAreaZeitReal.setVisible(true);
                            Gui.textAreaZeitReal.setText("\n"+String.valueOf(formatDate.format(currDate)));
                            sleep(1000);
                        Gui.textAreaZeitReal.setVisible(false);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
                Gui.textAreaZeitReal.setText("");
                Gui.textAreaZeitReal.setVisible(false);
            }

        }



