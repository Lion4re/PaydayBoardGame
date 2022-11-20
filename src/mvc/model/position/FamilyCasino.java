package mvc.model.position;

import mvc.model.Jackpot;
import mvc.model.Player;

public class FamilyCasino extends Position{

    /**
     * Constructor:<br>instantiates a new Family Casino position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the Family Casino position
     * @param image the image URL of the Family Casino position
     */
    public FamilyCasino(int index, String image) {
        super(index, image);
    }

    /**
     * Observer:<br>if player's dice is an odd number, then player give money into the jackpot
     *  if player's dice is an even number, the player takes money from the jackpot
     * @param p the player that will take the action
     * @param jackpot the jackpot in this part of the game
     * @return true if player takes money, false if player loses money
     */
    public boolean action(Player p, Jackpot jackpot){
        if(p.getDice().getNumber() % 2 == 0){
            p.setBalance(500);
            return true;
        }else {
            if (p.getBalance() < 500) {
                p.setLoans(500 - p.getBalance());
                p.setBalance(-p.getBalance());
            }else{
                p.setBalance(-500);
            }
            jackpot.setMoney(500);
        }

        return false;
    }
}
