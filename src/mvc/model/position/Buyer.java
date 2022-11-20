package mvc.model.position;

public class Buyer extends Position{


    /**
     * Constructor:<br>instantiates a new Buyer position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the Buyer position
     * @param image the image URL of the Buyer position
     */
    public Buyer(int index, String image) {
        super(index, image);
    }
}
