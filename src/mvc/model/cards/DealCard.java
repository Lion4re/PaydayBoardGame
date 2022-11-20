package mvc.model.cards;

import mvc.model.Player;

public class DealCard extends Card{

    private final int sell;

    /**
     * Constructor:<br>instantiates a new Deal Card and initializes all it's values
     * Preconditions:<br>imageURL must be valid<br>money greater or equal to 0<br>sell greater or equal to 0
     * @param image the imageURL of the card
     * @param money the cost of the card
     * @param text the text that the card should display
     * @param sell the sell value of the card
     */
    public DealCard(String image, int money, String text, int sell){
        super(image, money, text);
        this.sell = sell;
    }


    /**
     * Accessor:<br>returns the sell value of the card
     * @return the sell value of the card
     */
    public int getSell(){
        return this.sell;
    }


    /**
     * Postcondition:<br>if player buys the card, adds the card into his cardlist, otherwise to the AllCardsStack
     * @param p the player that will make the action
     */
    @Override
    public void action(Player p){
        p.setBalance(-this.getMoney());
        p.takeCard(this);
    }
}
