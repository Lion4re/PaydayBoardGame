package mvc.model;

import mvc.model.cards.*;
import java.util.ArrayList;

public class Player {

    private int balance;
    private int bills;
    private int loans;
    private int monthsCounter;
    private final String name;
    private ArrayList<DealCard> playerCards;
    private Dice playerDice;
    private Player opponent;
    private boolean playerTurn;
    private boolean finish;
    private boolean pending;
    private int position;

    /**
     * Constructor:<br>instantiates a new Player and initializes all it's values
     * @param name the name of the Player
     */
    public Player(String name){
        this.balance = 0;
        this.bills = 0;
        this.loans = 0;
        this.monthsCounter = 0;
        this.name = name;
        playerCards = new ArrayList<>();
        this.playerDice = new Dice();

        this.playerTurn = false;
        this.finish = false;
        this.pending = false;
        this.position = 0;

    }


    /**
     * Mutative Transformer:<br>adds money to the player's balance
     * Postcondition:<br> changes player's balance
     * @param balance the amount of balance to add
     */
    public void setBalance(int balance){
        this.balance += balance;

    }

    /**
     * Accessor:<br>returns the player's balance
     * @return the player's balance
     */
    public int getBalance(){
        return this.balance;
    }

    /**
     * Mutative Transformer:<br>adds a loan to player's loans
     * Postcondition:<br>changes player's loans
     * @param loans the amount of loan to add
     */
    public void setLoans(int loans){
        this.loans += loans;
    }

    /**
     * Accessor:<br>returns the player's loans
     * @return the player's loans
     */
    public int getLoans(){
        return this.loans;
    }

    /**
     * Mutative Transformer:<br>adds a bill to player's bills
     * Postcondition:<br>changes player's bills
     * @param bills the amount of bill to add
     */
    public void setBills(int bills){
        this.bills += bills;
    }

    /**
     * Accessor:<br>returns the player's bills
     * @return the player's bills
     */
    public int getBills(){
        return this.bills;
    }


    /**
     * Accessor:<br>returns the player's name
     * @return the player's name
     */
    public String getName(){
        return name;
    }


    /**
     * Mutative Transformer:<br>adds the player's game months
     * Postcondition:<br>changes the number of months
     * @param newMonths the number of months to be added
     */
    public void setMonthsCounter(int newMonths){
        this.monthsCounter = newMonths;
    }

    /**
     * Accessor:<br>returns the number of game months
     * @return the number of game months
     */
    public int getMonthsCounter(){
        return monthsCounter;
    }

    /**
     * Mutative Transformer:<br>sets the player's opponent
     * Postcondition:<br>changes the player's opponent
     * @param opponent the opponent of the player
     */
    public void setOpponent(Player opponent){
        this.opponent = opponent;
    }

    /**
     * Accessor:<br>returns the player's opponent
     * @return the player's opponent
     */
    public Player getOpponent(){
        return this.opponent;
    }

    /**
     * Mutative Transformer:<br>sets the turn of the player
     * @param newTurn true if it's his turn, false if it's not his turn
     */
    public void setTurn(boolean newTurn){
        if(this.getFinish())
            this.playerTurn = false;
        else
            this.playerTurn = newTurn;

    }

    /**
     * Observer:<br>returns if it's player's turn
     * @return true if it's player's turn, false if it's not
     */
    public boolean getTurn(){
        return playerTurn;
    }

    /**
     * Accessor:<br>returns the player's dice
     * @return the player's dice
     */
    public Dice getDice(){
        return playerDice;
    }

    /**
     * Mutative Transformer:<br>sets if player has finished his turn or not
     * @param finish true if player finished his turn, otherwise false
     */
    public void setFinish(boolean finish){
        this.finish = finish;
    }

    /**
     * Observer:<br>returns if player finished his turn
     * @return true if player finished his turn, otherwise false
     */
    public boolean getFinish(){
        return finish;
    }

    /**
     * Mutative Transformer:<br>sets if pending player's action or not
     * @param pending true if pending an action from player, otherwise false
     */
    public void setPending(boolean pending){
        this.pending = pending;
    }

    /**
     * Observer:<br>returns if pending player's action or not
     * @return true if pending an action from player, otherwise false
     */
    public boolean getPending(){
        return pending;
    }


    /**
     * Observer:<br>returns if player has DealCards or not
     * @return true if player has DealCards, otherwise false
     */
    public boolean noDealCards(){
        return playerCards.isEmpty();
    }

    /**
     * Adds a new card to player's cardlist
     * Postcondition:<br>adds a new card to the card's ArrayList
     * @param newCard the card is going to be added
     */
    public void takeCard(DealCard newCard){
        this.playerCards.add(newCard);
    }

    /**
     * Sells a card
     * Postcondition:<br>removes a card from the card's ArrayList and adds the money from the sellprice to the player's balance
     * @return the card that was sold
     */
    public DealCard sellCard(){
        DealCard removed = playerCards.get(0);
        playerCards.remove(0);
        return removed;
    }

    /**
     * Mutative Transformer:<br>sets the player's position
     * Postcondition:<br>changes the player's position
     * @param newPosition the new player's position
     */
    public void setPosition(int newPosition){
        if(position + newPosition > 31)
            position = 31;
        else
            position += newPosition;
    }

    /**
     * Accessor<br>returns the player's position
     * @return the player's position
     */
    public int getPosition(){
        return this.position;
    }

    //============================

//    public boolean paydayPositionCheck(){
//        return true;
//    }

}
