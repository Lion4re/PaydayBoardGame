package mvc.view;

import mvc.model.Player;

import javax.swing.*;
import java.util.ArrayList;

public class LotteryWindow {

    ImageIcon image;
    String bet;

    /**
     * Constructor:<br> Instantiates a new Lottery Window
     * @param p the Player who is in the Lottery Position
     * @param betOptions the options that the player have
     */
    public LotteryWindow(Player p, ArrayList betOptions){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/lottery.jpg", 120, 100);

        Object[] options = betOptions.toArray();
        bet = (String)JOptionPane.showInputDialog(frame, "Place the bet ", p.getName() + " chooces", JOptionPane.PLAIN_MESSAGE, image, options, "1000");
    }

    /**
     * Accessor:<br> This method is used to get the player's bet option
     * Postcondition:<br> if player doesn't bet, returns a random number
     * @return the bet option
     */
    public String getBet(){
        int i = 1 + (int)(Math.random() * 6);

        if(bet == null)
            return i + "";
        else
            return bet;
    }
}
