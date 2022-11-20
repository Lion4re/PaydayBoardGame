package mvc.view;

import javax.swing.*;

public class PayDayWindow {

    ImageIcon image;
    int pChoice;

    /**
     * Constructor:<br> Instantiates a new PayDay Window
     */
    public PayDayWindow(){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/logo.png", 170, 100);

        Object options[] = {"Ναι", "Μέρος Δανείου", "Όχι"};
        pChoice = JOptionPane.showOptionDialog(frame, "Θα θέλατε να ξοφλήσετε τα δάνεια σας;", "Τράπεζα", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
    }

    /**
     * Accessor:<br> The option that the player chooses in the PayDay
     * @return the player's choise
     */
    public int getPChoice(){
        return pChoice;
    }

    /**
     * Accessor:<br> If a player is going to pay a part of loan then there is one of those options
     * @return the part of loan that the player will pay
     */
    public String partOfLoan(){
        String loan;
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/logo.jpg", 120, 100);

        Object[] options = {"1000", "2000", "3000", "4000", "5000", "6000", "7000", "8000", "9000", "10000",
                "11000", "12000", "13000", "14000", "15000", "16000", "17000", "18000", "19000", "20000"};
        loan = (String) JOptionPane.showInputDialog(frame, "Επιλέξτε ποσό πληρωμής δανείου", "Τράπεζα", JOptionPane.PLAIN_MESSAGE, image, options, "1000");
        return loan;
    }
}
