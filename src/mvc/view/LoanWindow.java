package mvc.view;

import javax.swing.*;

public class LoanWindow {

    ImageIcon image;
    String loan;

    /**
     * Constructor:<br> Instansiates a new Loan Window
     */
    public LoanWindow(){
        JFrame frame = new JFrame();
        image = View.getImageScaled("src/resources/images/Loan.jpg", 120, 100);

        Object[] options = {"1000", "2000", "3000", "4000", "5000", "10000", "15000", "20000"};
        loan = (String)JOptionPane.showInputDialog(frame, "Pick the amount of loan", "Bank", JOptionPane.PLAIN_MESSAGE, image, options, "1000");
    }

    /**
     * Accessor:<br> If a player is going to take a loan it will return the loan otherwise it will return nothing
     * @return the loan that the player takes
     */
    public String getLoanOption(){
        if(loan == null)
            return "0";
        else
            return loan;
    }
}
