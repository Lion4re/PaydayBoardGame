package mvc.view;

import javax.swing.*;

public class MonthsToPlayWindow {

    ImageIcon image;
    String months;

    /**
     * Constructor:<br> Instantiates a new Months to Play Window
     */
    public MonthsToPlayWindow(){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/logo.png", 120, 100);

        Object[] options = {"1", "2", "3"};
        months = (String)JOptionPane.showInputDialog(frame, "Number of months you want the game to last", "Months Playing", JOptionPane.PLAIN_MESSAGE, image, options, "1");
    }

    /**
     * Accessor:<br> The months that the game will last
     * Postcondition:<br> if months == null, then the returned value is 1 (month)
     * @return the months that player choose
     */
    public String getMonths(){
        if(months == null)
            return "1";
        else
            return months;
    }
}
