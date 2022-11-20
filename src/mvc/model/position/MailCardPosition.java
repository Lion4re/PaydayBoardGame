package mvc.model.position;

public class MailCardPosition extends Position{

    private final boolean draw;

    /**
     * Constructor:<br>instantiates a new MailCard position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the MailCard position
     * @param image the image URL of the MailCard position
     * @param draw if player is going to draw a card
     */
    public MailCardPosition(int index, String image, boolean draw) {
        super(index, image);
        this.draw = draw;
    }

    /**
     * Accessor:<br>
     * @return the number of cards that the player will draw
     */
    public boolean drawCard(){
        return draw;
    }
}
