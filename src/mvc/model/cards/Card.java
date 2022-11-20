package mvc.model.cards;

import mvc.model.Player;


public abstract class Card {

    private final String image;
    private final int money;
    private final String text;

    /**
     * Constructor:<br>instantiates a new card and setting all it's values
     * Precondition:<br>money can't be negative
     * @param image the image url of the card
     * @param money the money that the card costs
     * @param text the text that card will have
     */
    public Card(String image, int money, String text){

        //if statement gia ta lefta na mhn eine negative

        this.image = image;
        this.money = money;
        this.text = text;
    }

    /**
     * Accessor:<br>returns the image of the card
     * @return the image of the card
     */
    public String getImage(){
        return image;
    }

    /**
     * Accessor:<br>returns the money of the card
     * @return the money of the card
     */
    public int getMoney(){
        return money;
    }

    /**
     * Accessor:<br>returns the text of the card
     * @return the card's text
     */
    public String getText(){
        return text;
    }

    /**
     *A method that all subclasses inherit and implements the action of every card
     * @param p the player that will make the action
     */
    public abstract void action(Player p);
}
