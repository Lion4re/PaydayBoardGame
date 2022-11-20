package mvc.model.position;

import mvc.model.Player;
import mvc.model.cards.DealCard;

public class YardSale extends Position{

    /**
     * Constructor:<br>instantiates a new Yard Sale position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the Yard Sale position
     * @param image the image URL of the Yard Sale position
     */
    public YardSale(int index, String image) {
        super(index, image);
    }

    /**
     * Mutative Transformer:<br>The player pays 100*the dice number and takes a DealCard from the DealCard Stack
     * @param p the player that will take the action
     * @param index the index of the Dice
     * @param c the DealCard that the player is going to take
     */
    public void action(Player p, int index, DealCard c){
        if(p.getBalance() < index * 100){
            p.setLoans(index * 100 - p.getBalance());
            p.setBalance(-p.getBalance());
        }else{
            p.setBalance(-(index * 100));
        }
        p.takeCard(c);
    }
}
