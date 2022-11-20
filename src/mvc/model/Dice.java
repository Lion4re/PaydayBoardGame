package mvc.model;

public class Dice{

    private int number;
    private boolean roll;

    /**
     * Constructor:<br>instantiates a new dice
     * Postconditions:<br>dice number is 0<br>roll is equal to false
     */
    public Dice(){
        this.number = 0;
        this.roll = false;
    }

    /**
     * Mutative Transformer:<br>sets the number of the dice
     * Precondition:<br> newNumber must be greater or equal to 1 and lower or equal to 6
     * Postcondition:<br>changes the dice's number
     * @param newNumber new number of the dice
     */
    public void setNumber(int newNumber){
        if(newNumber < 1 || newNumber > 6)
            throw new IllegalArgumentException();

        this.number = newNumber;
    }

    /**
     * Accessor:<br>returns the number of the dice
     * @return the dice's number
     */
    public int getNumber(){
        return number;
    }

    /**
     * Mutative Transformer:<br>when the dice is rolled, the value is true, otherwise false
     * @param newRoll the new roll value
     */
    public void setRoll(boolean newRoll){
        this.roll = newRoll;
    }


    /**
     * Observer:<br>the value if dice is rolled or not
     * @return true if dice rolled, otherwise false
     */
    public boolean getRoll(){
        return roll;
    }

    /**
     * Applicative Transformer:<br>sets a new random value to the dice from 1 to 6
     * Postcondition:<br>the new value of the dice should be newNumber must be greater or equal to 1 and lower or equal to 6
     * @return the new value of dice
     */
    public int rollTheDice(){
        int randomRoll =1 + (int) (Math.random() * 6);
        roll = true;
        number = randomRoll;
        return randomRoll;
    }

}
