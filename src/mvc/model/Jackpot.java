package mvc.model;

public class Jackpot {

    private int money;

    /**
     * Constructor:<br>instantiates the jackpot spot
     * Postcondition:<br>initializes it's balance to 0
     */
    public Jackpot(){
        this.money = 0;
    }

    /**
     * Mutative Transformer:<br>adds money to the jackpot
     * Precondition:<br>the money should be greater than 0
     * Postcondition:<br>jackpot is greater than the previous one
     * @param newMoney the money to be added
     */
    public void setMoney(int newMoney){
        if(newMoney < 0)
            throw new IllegalArgumentException();

        this.money += newMoney;
    }

    /**
     * Accessor:<br>returns the money of the jackpot
     * @return the money of the jackpot
     */
    public int getMoney(){
        return this.money;
    }

    /**
     *Money that player takes.After that award is going to be 0
     * @return the jackpot's reward
     */
    public int jackpotAward(){
        int award = this.money;
        this.money = 0;
        return award;
    }

    public String toString(){
        return money + "";
    }


}
