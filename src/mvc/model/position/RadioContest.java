package mvc.model.position;

public class RadioContest extends Position{

    /**
     * Constructor:<br>instantiates a new Radio Contest position
     * Preconditions:<br>index should be great or equal to 1 and lower or equal to 30 and not be taken by another position
     * <br>image URL must be valid
     * @param index the index of the Radio Contest position
     * @param image the image URL of the Radio Contest position
     */
    public RadioContest(int index, String image) {
        super(index, image);
    }
}
