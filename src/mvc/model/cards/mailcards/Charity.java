package mvc.model.cards.mailcards;

import mvc.model.Jackpot;
import mvc.model.Player;
import mvc.model.cards.MailCard;

public class Charity extends MailCard{

    /**
     * Constructor:<br>instantiates a new Charity card
     * Precondition:<br>imageURL must be valid<br>money must be greater or equal to 0
     * @param image the image of the card
     * @param money the cost of the card
     * @param text the text that the card should display
     */
    public Charity(String image, int money, String text) {
        super(image, money, text);
    }

    /**
     * Moves balance from a player to the Jackpot balance
     * Postcondition:<br>balance is added to Jackpot
     * @param p the player that will make the action
     * @param jackpot the jackpot of the game
     */
    public void action(Player p, Jackpot jackpot){
        if(p.getBalance() < this.getMoney()){
            p.setLoans(this.getMoney() - p.getBalance());
            p.setBalance(this.getMoney() - p.getBalance());
        }

        p.setBalance(-this.getMoney());
        jackpot.setMoney(this.getMoney());
    }
}
