package mvc.view;

import javax.swing.*;

public class Window {

    ImageIcon icon;
    int option, optionHelper;

    /**
     * Constructor:<br> Instantiates a new Window
     * @param op1 Option 1
     * @param op2 Option 2
     * @param msg Message of the window
     * @param windowName Window's name
     * @param image The image of the window
     */
    public Window(Object op1, Object op2, String msg, String windowName, String image){
        JFrame frame = new JFrame();
        icon = View.getImageScaled(image, 170, 100);

        Object[] options = {op1, op2};
        optionHelper = JOptionPane.showOptionDialog(frame, msg, windowName, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, icon, options, options[0]);

        if(optionHelper == JOptionPane.YES_OPTION)
            option = 1;
        else if(optionHelper == JOptionPane.NO_OPTION)
            option = 2;
        else
            option = 0;
    }

    /**
     * Accessor:<br> Returns the option that was choosen
     * @return the player's option
     */
    public int getOption(){
        return option;
    }
}
