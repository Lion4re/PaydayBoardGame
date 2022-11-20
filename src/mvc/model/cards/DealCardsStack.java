package mvc.model.cards;

import java.util.ArrayList;
import java.util.*;

public class DealCardsStack {

    private ArrayList<DealCard> dealCardStack;

    /**
     * Constructor<br>instantiates a new DealCardStack
     * Postcondition:<br>creates an empty ArrayList of DealCards
     */
    public DealCardsStack(){
        dealCardStack = new ArrayList<DealCard>();
    }

    /**
     * Mutative Transformer:<br>adds a new card to the stack
     * Postconditions:<br>a new card is added to the stack<br>the Arraylist size becomes bigger
     * @param c the card to be added to the stack
     */
    public void push(DealCard c){
        dealCardStack.add(c);
    }

    /**
     * Mutative Transformer:<br>a card is removed from the stack
     * Postconditions:<br>a card is removed from the stack<br>the Arraylist size becomes smaller
     * @return the removed card
     */
    public DealCard pop(){
        DealCard removed = dealCardStack.get(0);
        dealCardStack.remove(0);
        return removed;
    }

    /**
     * Mutative Transformer:<br>suffles the cards into the stack
     * Precondition:<br>all cards should be into the stack
     */
    public void suffleCardStack(){
        Collections.shuffle(dealCardStack);
    }

    /**
     * Observer:<br>returns if stack is empty or not
     * @return true if stack is empty, otherwise false
     */
    public boolean isStackEmpty(){
        return dealCardStack.isEmpty();
    }
}
