package mvc.model.cards.mailcards;

import mvc.model.Player;
import mvc.model.cards.MailCard;

public class TakeFromNeighbor extends MailCard{

    /**
     * Constructor:<br>implements a new Take from the neighbor card
     * Precondition:<br>imageURL must be valid<br>money should be greater or equal to 0
     * @param image the image of the card
     * @param money the money of the card
     * @param text the text that the card should display
     */
    public TakeFromNeighbor(String image, int money, String text) {
        super(image, money, text);
    }

    /**
     * Postcondition:<br>transfers money from a player to the other
     * @param p the player that will make the action
     */
    @Override
    public void action(Player p){
        if(p.getOpponent().getBalance() < this.getMoney()){
            p.getOpponent().setLoans(this.getMoney() - p.getOpponent().getBalance());
            p.getOpponent().setBalance(-this.getMoney() - p.getOpponent().getBalance());
        }
        p.getOpponent().setBalance(-this.getMoney());
        p.setBalance(this.getMoney());
    }
}
