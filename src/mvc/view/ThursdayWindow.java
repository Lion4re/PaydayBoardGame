package mvc.view;

import javax.swing.*;

public class ThursdayWindow {

    ImageIcon image;
    int bet;

    /**
     * Constructor:<br> Instantiates a new Thursday Window
     */
    public ThursdayWindow(){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/crypto.jpg", 170, 100);

        Object[] options = {"Πόνταρε στο κρυπτονόμισμα", "Παράβλεψε το ποντάρισμα"};
        bet = JOptionPane.showOptionDialog(frame, "Πόνταρε σε κρυπτονομίσματα", "Crypto Thursday", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
    }

    /**
     * Constructor:<br> Instantiates a new Thursday Window
     * Postcondition:<br> Instantiates a new Thursday Window and prints if player win or lose
     * @param won the player's win or loss, 2 == win, 1 == moneyback, 0 == loss
     */
    public ThursdayWindow(int won){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/crypto.jpg", 170, 100);

        if(won == 2){
            Object[] options = {"Το κρυπτονόμισμα εκτοξέυθηκε!"};
            bet = JOptionPane.showOptionDialog(frame, "Κέρδισες 600 ευρω!", "Crypto Thursday", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else if (won == 1){
            Object[] options = {"Το κρυπτονόμισμα έμεινε σταθερό"};
            bet = JOptionPane.showOptionDialog(frame, "Πάρε το ποντάρισμα σου πίσω!", "Crypto Thursday", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
        }else{
            Object[] options = {"Το κρυπτονόμισμα έπεσε"};
            bet = JOptionPane.showOptionDialog(frame, "Έχασες το ποντάρισμα σου", "Crypto Thursday", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, image, options, options[0]);
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
