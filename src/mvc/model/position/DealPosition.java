package mvc.model.position;

public class DealPosition extends Position{

    /**
     * Constructor:<br>instantiates a new Deal position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the Deal position
     * @param image the image URL of the Deal position
     */
    public DealPosition(int index, String image) {
        super(index, image);
    }
}
