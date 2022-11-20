package mvc.model.position;

import mvc.model.Player;

public abstract class Position {

    private int index;
    private String name;
    private final String image;

    /**
     * Constructor:<br>instantiates a new position
     * @param index the index of the position
     * @param image the position's image
     */
    public Position(int index, String image) {
        this.index = index;
        this.image = image;
    }


    /**
     * Mutative Transformer:<br>sets the index of the position
     * @param newIndex the index of the position
     */
    public void setIndex(int newIndex){
        this.index = newIndex;
    }

    /**
     * Accessor:<br>returns the index of the position
     * @return the index of the position
     */
    public int getIndex(){
        return index;
    }

    /**
     * Mutative Transformer:<br>sets the name of the position
     * @param name the name of the position
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Accessor:<br>returns the name of the position
     * @return the name of the position
     */
    public String getName(){
        return name;
    }

    /**
     * Accessor:<br>returns the image of the position
     * @return the image of the position
     */
    public String getImage(){
        return image;
    }

    /**
     * Observer:<br>checks if the day is Thursday
     * @return true if is Thursday, otherwise false
     */
    public boolean isThursday(){
        return name.equals("Thursday");
    }

    /**
     * Observer:<br>checks if the day is Sunday
     * @return true if is Sunday, otherwise false
     */
    public boolean isSunday(){
        return name.equals("Sunday");
    }
}
