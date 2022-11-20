package mvc.model.position;

import mvc.model.Player;

public class Sweepstakes extends Position{

    /**
     * Constructor:<br>instantiates a new Sweepstakes position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the Sweeptakes position
     * @param image the image URL of the Sweeptakes position
     */
    public Sweepstakes(int index, String image) {
        super(index, image);
    }

    /**
     * Mutative Transformer:<br>if player is on this position wins dice number multiplied by 1000
     * @param p the player that will take the action
     * @param index the index of the dice
     */
    public void action(Player p, int index){
        p.setBalance(index * 1000);
    }
}
