package mvc.model.cards.mailcards;

import mvc.model.Player;
import mvc.model.cards.MailCard;

public class Bill extends MailCard{

    /**
     * Constructor:<br>instantiates a new Bill card
     * @param image the image of the card
     * @param money the money of the card
     * @param text the text that the card should display
     */
    public Bill(String image, int money, String text) {
        super(image, money, text);
    }

    /**
     * Adds the bill's value to the bills balance of the player
     * @param p the player that will make the action
     */
    @Override
    public void action(Player p){
        p.setBills(this.getMoney());
    }
}
