package mvc.controller;

import mvc.model.*;
import mvc.model.cards.AllCardsStack;
import mvc.model.cards.DealCard;
import mvc.model.cards.DealCardsStack;
import mvc.model.cards.MailCardsStack;
import mvc.model.cards.mailcards.*;
import mvc.model.position.*;
import mvc.view.MonthsToPlayWindow;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Controller {

    public Player p1, p2;
    public Position[] table;
    public AllCardsStack RejectedCards;
    public MailCardsStack MailCardsStack;
    public DealCardsStack DealCardsStack;
    public boolean start;
    public int pointsP1;
    public int pointsP2;
    public Jackpot jackpot;
    public ClassLoader cldr;

    /**
     * Constructor:<br>Implements a new Controller for the game
     */
    public Controller(){

        p1 = new Player("Player 1");
        p2 = new Player("Player 2");
        p1.setOpponent(p2);
        p2.setOpponent(p1);

        RejectedCards = new AllCardsStack();
        MailCardsStack = new MailCardsStack();
        DealCardsStack = new DealCardsStack();
        jackpot = new Jackpot();

        cldr = this.getClass().getClassLoader();

    }

    /**
     * Transformer:<br>Initializes the game and the game is starting
     */
    public void initGame(){
        initCards();
        MonthsToPlayWindow monthsWindow = new MonthsToPlayWindow();
        gameMonths(Integer.parseInt(monthsWindow.getMonths()));
        initBalance();

    }

    /**
     * Mutative Transformer:<br>Setting the table of the game
     * Postcondition:<br>Implements the table and setting positions of the game random into the Array
     */
    public void initTable(){
        table = new Position[32];

        table[0] = new Start("images/start.png");
        table[31] = new PayDayPosition("images/pay.png");

        int i = 1;

        while(i < 5){
            table[i] = new MailCardPosition(0, "images/mc1.png", true);
            i++;
        }

        while(i < 9){
            table[i] = new MailCardPosition(0, "images/mc2.png", false);
            i++;
        }

        while(i < 14){
            table[i] = new DealPosition(0, "images/deal.png");
            i++;
        }

        while(i < 16){
            table[i] = new Sweepstakes(0, "images/sweep.png");
            i++;
        }

        while(i < 19){
            table[i] = new Lottery(0, "images/lottery.png");
            i++;
        }

        while(i < 21){
            table[i] = new RadioContest(0, "images/radio.png");
            i++;
        }

        while(i < 27){
            table[i] = new Buyer(0, "images/buyer.png");
            i++;
        }

        while(i < 29){
            table[i] = new FamilyCasino(0, "images/casino.png");
            i++;
        }

        while(i < 31){
            table[i] = new YardSale(0, "images/yard.png");
            i++;
        }

        positionSuffle(table);
    }

    /**
     * Transformer:<br>Suffles the positions into the table
     * Precondition:<br>The Array argument must contain all it's elements(positions)
     * Postcondition:<br>Suffles the positions into the Array, except the Starting Position(0) and the PayDay Position(31)
     * @param pos the positions into the Array
     */
    public static void positionSuffle(Position[] pos){
        int days = pos.length - 1;

        for(int i = 1; i < days; i++){
            int randomPos = i + (int) (Math.random() * (days - i ));
            Position tmp = pos[i];
            pos[i] = pos[randomPos];
            pos[randomPos] = tmp;
        }

        pos[0].setName("Start");
        pos[0].setIndex(0);

        pos[31].setName("Wednesday");
        pos[31].setIndex(31);

        for(int i = 1; i < days; i++){
            pos[i].setIndex(i);
            if(i % 7 == 0)
                pos[i].setName("Sunday");
            else if(i % 7 == 1)
                pos[i].setName("Monday");
            else if(i % 7 == 2)
                pos[i].setName("Tuesday");
            else if(i % 7 == 3)
                pos[i].setName("Wednesday");
            else if(i % 7 == 4)
                pos[i].setName("Thursday");
            else if(i % 7 == 5)
                pos[i].setName("Friday");
            else if(i % 7 == 6)
                pos[i].setName("Saturday");
        }
    }

    /**
     * Transformer:<br> Sets the months that the game will last
     * Precondition:<br> months must be greater or equal to 1 and lower or equal to 3
     * Postcondition:<br> the months should be the same for both players
     * @param months months that the game will last
     */
    public void gameMonths(int months){
        p1.setMonthsCounter(months);
        p2.setMonthsCounter(months);
    }

    /**
     * Transformer:<br>Setting the cards
     */
    public void initCards(){
        String[][] mailCards = readFile("resources/mailCards.csv", "Mail");
        String[][] dealCards = readFile("resources/dealCards.csv", "Deal");

        for(int i = 0; i < 48; i++){
            if(mailCards[i][1].equals("Advertisement"))
                MailCardsStack.push(new Advertisement(mailCards[i][5], mailCards[i][2]));
            else if(mailCards[i][1].equals("Bill"))
                MailCardsStack.push(new Bill(mailCards[i][5], Integer.parseInt(mailCards[i][4]), mailCards[i][2]));
            else if(mailCards[i][1].equals("Charity"))
                MailCardsStack.push(new Charity(mailCards[i][5], Integer.parseInt(mailCards[i][4]), mailCards[i][2]));
            else if(mailCards[i][1].equals("MoveToDealBuyer"))
                MailCardsStack.push(new MoveToDealBuyer(mailCards[i][5], mailCards[i][2]));
            else if(mailCards[i][1].equals("PayTheNeighbor"))
                MailCardsStack.push(new PayTheNeighbor(mailCards[i][5], Integer.parseInt(mailCards[i][4]), mailCards[i][2]));
            else if(mailCards[i][1].equals("MadMoney"))
                MailCardsStack.push(new TakeFromNeighbor(mailCards[i][5], Integer.parseInt(mailCards[i][4]), mailCards[i][2]));
        }
        MailCardsStack.suffleCardStack();

        for(int i = 0; i < 20; i++){
            String image = dealCards[i][5];
            String text = dealCards[i][2];
            int cost = Integer.parseInt(dealCards[i][3]);
            int sell = Integer.parseInt(dealCards[i][4]);

            DealCardsStack.push(new DealCard(image, cost, text, sell));
        }
        DealCardsStack.suffleCardStack();
    }

    /**
     * Transformer:<br>Each player roll the dice. If p1 dice is greater than p2 dice then p1 is playing first
     * otherwise player 2 plays first
     * Precondition:<br>p1 != p2
     * Postcondition:<br> gives the turn to player 1 or player 2
     * @param p1_dice p1 dice
     * @param p2_dice p2 dice
     */
    public void playingFirst(int p1_dice, int p2_dice){
        if(p1_dice > p2_dice)
            p1.setTurn(true);
        else
            p2.setTurn(true);
    }

    /**
     * Transformer:<br>Initializes the balance of both players
     */
    public void initBalance(){
        p1.setBalance(3500);
        p2.setBalance(3500);
    }

    /**
     * Transformer:<br>The player is going to take the jackpot money
     * @param p the player that is going to take the jackpot
     */
    public void jackpot(Player p){
        p.setBalance(this.jackpot.jackpotAward());
    }

    /**
     * Observer:<br>
     * @param p the player that is on a Thursday position
     * @param bet the bet from the player
     * @return returns 2 if the player wins money, 1 if he gets money back and 0 if he lose his money
     */
    public int isThursday(Player p, int bet){

        if(bet != 1 && p.getBalance() < 300){
            p.setLoans(300);
            p.setBalance(300);
        }

        int win = -1;

        if(bet == 0){
            if(p.getDice().getNumber() == 1 || p.getDice().getNumber() == 2){
                p.setBalance(-300);
                win = 0;
            }else if(p.getDice().getNumber() == 3 || p.getDice().getNumber() == 4){
                win = 1;
            }else{
                p.setBalance(300);
                win = 2;
            }
        }

        return win;
    }

    /**
     * Observer:<br>
     * @param p the player that is on a Thursday position
     * @param bet the bet from the player
     * @return returns true if the player wins money, otherwise false
     */
    public boolean isSunday(Player p, int bet){

        if(bet != 3 && p.getBalance() < 500){
            p.setLoans(500);
            p.setBalance(500);
        }

        boolean moneyback = false;

        if(bet == 0){
            if(p.getDice().getNumber() == 1 || p.getDice().getNumber() == 2){
                p.setBalance(500);
                moneyback = true;
            }else{
                p.setBalance(-500);
                moneyback = false;
            }
        }else if(bet == 1){
            if(p.getDice().getNumber() == 3 || p.getDice().getNumber() == 4){
                p.setBalance(500);
                moneyback = true;
            }else{
                p.setBalance(-500);
                moneyback = false;
            }
        }else if(bet == 2){
            if(p.getDice().getNumber() == 5 || p.getDice().getNumber() == 6){
                p.setBalance(500);
                moneyback = true;
            }else{
                p.setBalance(-500);
                moneyback = false;
            }
        }

        return moneyback;
    }

    /**
     * Observer:<br>
     * @return if game is finished returns true, otherwise false
     */
    public boolean gameFinish(){
        return p1.getFinish() && p2.getFinish();
    }

    /**
     * A method that calculates the scores and sets the winner of the game
     * @return the player that won the game and some other things
     */
    public String gameWinner(){
        pointsP1 = p1.getBalance() - p1.getBills() - p1.getLoans();
        pointsP2 = p2.getBalance() - p2.getBills() - p2.getLoans();

        String winOrDraw;

        if(pointsP1 > pointsP2)
            winOrDraw = "Player 1 wins!\nNew game?";
        else if(pointsP1 < pointsP2)
            winOrDraw = "Player 2 wins!\nNew game?";
        else
            winOrDraw = "Draw!\nNew game?";

        return winOrDraw;
    }

    /**
     * Mutative Transformer:<br>The Player will throw all the cards
     * @param p the player that will throw the cards
     */
    public void throwAllCards(Player p){
        if(p.getFinish()){
            while(!p.noDealCards())
                this.RejectedCards.push(p.sellCard());
        }
    }

    /**
     * A method that provides information to the players of the game and describing the game
     * @return useful messages about the game
     */
    public String[] gameLog(){
        String[] log = new String[2];
        if(p1.getMonthsCounter() > p2.getMonthsCounter())
            log[0] = p1.getMonthsCounter() + " months remaining.";
        else
            log[0] = p2.getMonthsCounter() + " months remaining.";

        if(p1.getTurn())
            log[1] = p1.getName() + " has turn.";
        else if(p2.getTurn())
            log[1] = p2.getName() + " has turn.";

        return log;
    }


    public String[][] readFile(String path, String type){
        String[][] mailCards = new String[48][4];
        String[][] dealCards = new String[20][8];

        BufferedReader br = null;
        String sCurrentLine;
        try {
            String fullPath = cldr.getResource(path).getPath();
            br = new BufferedReader(new FileReader(fullPath));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        int count = 0;
        int splitCount = 0;
        HashMap<Integer, String> domainsMap = new HashMap<>();
        try {
            br.readLine();
            while ((sCurrentLine = br.readLine()) != null) {
                if (type.equals("Mail")) {
                    mailCards[count++] = sCurrentLine.split(",");
                } else {

                    dealCards[count++] = sCurrentLine.split(",");
                }
            }
            br.close();
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (type.equals("Mail")) {
            return mailCards;
        } else {
            return dealCards;
        }
    }
}

