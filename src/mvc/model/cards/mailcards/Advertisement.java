package mvc.model.cards.mailcards;

import mvc.model.Player;
import mvc.model.cards.MailCard;

public class Advertisement extends MailCard{

    /**
     * Constructor:<br>instantiates a new Advertisement card
     * Postcondition:<br>instantiates a new Advertisement card with cost value = 0
     * @param image the image of the card
     * @param text the text that the card should display
     */
    public Advertisement(String image, String text) {
        super(image, 0, text);
    }

    /**
     * Sells the card and add the sell value to player's balance
     * @param p the player that will make the action
     */
//    @Override
//    public void action(Player p){
//
//    }
}
