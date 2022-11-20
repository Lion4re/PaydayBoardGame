package mvc.view;

import javax.swing.*;

public class SundayWindow {

    ImageIcon image;
    int bet;

    /**
     * Constructor:<br> Instantiates a new Sunday Window
     */
    public SundayWindow(){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/Barcelona_Real.jpg", 170, 100);

        Object[] options = {"1", "X", "2", "Δεν θα παίξω"};
        bet = JOptionPane.showOptionDialog(frame, "Παίξε 500 ευρώ στο El Clasico", "El Clasico", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
    }

    /**
     * Constructor:<br> Instantiates a new Sunday Window
     * Postcondition:<br> Instantiates a new Sunday Window and prints if player win or lose
     * @param won the player's win or loss, 1 == win, 0 == loss
     */
    public SundayWindow(boolean won){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/Barcelona_Real.jpg", 170, 100);

        if(won){
            Object[] options = {"Κέρδισες 1000 ευρώ!"};
            bet = JOptionPane.showOptionDialog(frame, "Κέρδισες!", "El Clasico", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else{
            Object[] options = {"Πήγες κουβά"};
            bet = JOptionPane.showOptionDialog(frame, "Πήγες κουβά!", "El Clasico", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }
    }

    /**
     * Accessor:<br> The player's betting option
     * @return the betting option of the player
     */
    public int getBet(){
        return bet;
    }
}
