package mvc.model.position;

public class Start extends Position{

    /**
     * Constructor:<br>instantiates a new Start position
     * Precondition:<br>image URL must be valid
     * @param image image URL of the Start position
     */
    public Start(String image) {
        super(0, image);
    }
}
