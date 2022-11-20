package mvc.model.cards;

import java.util.ArrayList;

public class AllCardsStack {

    private ArrayList<Card> rejectedCardStack;

    /**
     * Constructor:<br>instantiates a new stack for all the cards
     * Postcondition:<br>creates an empty Arraylist
     */
    public AllCardsStack(){
        rejectedCardStack = new ArrayList<Card>();
    }

    /**
     * Mutative Transformer:<br>adds a new card to the stack
     * Postconditions:<br>a new card is added to the stack<br>the Arraylist size becomes bigger
     * @param c the card to be added to the stack
     */
    public void push(Card c){
        rejectedCardStack.add(c);
    }

    /**
     * Applicative Transformer:<br>removes the cards from the current stack and place them
     * into MailCardsStack or DealCardsStack depending on their card type
     * Precondition:<br>AllCardsStack must contain elements(cards)
     * Postcondition:<br>cards are seperated into 2 different stacks and AllCardsStack should be empty
     * @param mailCards the stack of mail cards
     * @param dealCards the stack of deal cards
     */
    public void popAndSeperate(MailCardsStack mailCards, DealCardsStack dealCards){
        while(!rejectedCardStack.isEmpty()) {
            Card c = rejectedCardStack.get(0);
            rejectedCardStack.remove(0);
            if(c instanceof MailCard)
                mailCards.push((MailCard)c);
            else
                dealCards.push((DealCard)c);

        }
        mailCards.suffleCardStack();
        dealCards.suffleCardStack();
    }


    /**
     * Observer:<br>returns if stack is empty or not
     * @return true if stack is empty, otherwise false
     */
    public boolean isStackEmpty(){
        return rejectedCardStack.isEmpty();
    }
}
