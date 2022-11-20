package mvc.model.cards.mailcards;

import mvc.model.Player;
import mvc.model.cards.MailCard;
import mvc.model.position.Buyer;
import mvc.model.position.DealPosition;
import mvc.model.position.Position;

public class MoveToDealBuyer extends MailCard{

    /**
     * Constructor:<br>instantiates a new Move to Deal/Buyer card
     * Precondition:<br>imageURL must be valid<br>money greater or equal to 0
     * @param image the image of the card
     * @param text the text that the card should display
     */
    public MoveToDealBuyer(String image, String text) {
        super(image, 0, text);
    }

    /**
     * Moves to the closest Deal or Buyer position
     * Postcondition:<br> if there is a deal or buyer position then player moves there
     * @param p the player that will make the action
     * @param positions positions of the board
     * @return the position that the player is going to move to
     */
    public int action(Player p, Position positions[]){
        for(int i = p.getPosition(); i < positions.length; i++){
            if(positions[i] instanceof DealPosition || positions[i] instanceof Buyer)
                return i;
        }
        return p.getPosition();
    }
}
