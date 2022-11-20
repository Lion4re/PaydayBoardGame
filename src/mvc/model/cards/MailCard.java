package mvc.model.cards;

import mvc.model.Player;

public abstract class MailCard extends Card{

    /**
     * Constructor:<br>instantiates a new Deal Card and initializes all it's values
     * Preconditions:<br>imageURL must be valid<br>money greater or equal to 0
     * @param image the imageURL of the card
     * @param money the cost of the card
     * @param text the text that the card should display
     */
    public MailCard(String image, int money, String text) {
        super(image, money, text);
    }

    /**
     *the action that all MailCards inherit
     * @param p the player that will make the action
     */
    @Override
    public void action(Player p){

    }
}
