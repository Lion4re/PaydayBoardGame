package mvc.view;

import mvc.controller.Controller;
import mvc.model.Dice;
import mvc.model.Player;
import mvc.model.cards.Card;
import mvc.model.cards.DealCard;
import mvc.model.cards.MailCard;
import mvc.model.cards.mailcards.*;
import mvc.model.position.*;

import javax.swing.*;
import java.net.URL;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;


public class View extends JFrame {

    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private final int width = (int) Math.round(screenSize.getWidth()) - 200;
    private final int height = (int) Math.round(screenSize.getHeight()) - 200;

    private JLayeredPaneExtension panel;
    private URL imageURL;
    private Image image;
    ImageIcon ins;
    private JDesktopPane InfoBox, table, p1, p2, jackpotPanel;
    private JLabel payDayImage, p1Name, p2Name, jackpotLabel;
    private JButton dealCards, mailCards, DealCards1, DealCards2, getLoanP1, getLoanP2, endTurnP1, endTurnP2, diceP1, diceP2;
    private JTextField balance1Text, balance2Text, loan1Text, loan2Text, bills1Text, bills2Text, jackpotText, info, turn, monthsLeft, command , temp;
    private JMenu menu;
    private JDesktopPane[] position;
    JLayeredPane[] pawn_position;
    int choiseP1, choiseP2;

    private int counter = 0;
    private boolean radioPos = false;
    private boolean lotteryPos = false;

    private ClassLoader cldr;
    Controller controller;


    /**
     * Constructor:<br>Constructs the basic graphic elements of the game
     */
    public View() {

        cldr = this.getClass().getClassLoader();

        p1 = new JDesktopPane();
        p2 = new JDesktopPane();

        payDayImage = new JLabel();
        table = new JDesktopPane();
        position = new JDesktopPane[35];
        pawn_position = new JLayeredPane[32];

        jackpotPanel = new JDesktopPane();
        jackpotLabel = new JLabel();
        jackpotText = new JTextField();

        balance1Text = new JTextField();
        balance2Text = new JTextField();
        loan1Text = new JTextField();
        loan2Text = new JTextField();
        bills1Text = new JTextField();
        bills2Text = new JTextField();
        p1Name = new JLabel("Player 1");
        p1Name.setFont(new Font(null, Font.BOLD, 20));
        p2Name = new JLabel("Player 2");
        p2Name.setFont(new Font(null, Font.BOLD, 20));

        InfoBox = new JDesktopPane();
        info = new JTextField("Info Box");
        info.setFont(new Font(null, Font.BOLD, 15));

        monthsLeft = new JTextField();
        command = new JTextField();
        turn = new JTextField();
        endTurnP1 = new JButton();
        endTurnP2 = new JButton();

        dealCards = new JButton();
        mailCards = new JButton();
        DealCards1 = new JButton();
        DealCards2 = new JButton();

        diceP1 = new JButton();
        diceP2 = new JButton();

        getLoanP1 = new JButton();
        getLoanP2 = new JButton();

        controller = new Controller();

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.getContentPane().setBackground(new Color(0, 102, 0));
        this.setResizable(false);
        this.setTitle("PayDay");
        imageURL = cldr.getResource("resources/images/logo.png");
        this.setIconImage(new ImageIcon(imageURL).getImage());
        this.setVisible(true);
        initComponents();


    }

    /**
     * Transformer:<br>Initializes the components of the game
     */
    public void initComponents(){

        dealCards.setSize(width * (2 / 16) - 40, height * 1 / 7);
        imageURL = cldr.getResource("resources/images/dealCard.png");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width * (2 / 16) - 20, height * 1 / 7 - 15, Image.SCALE_SMOOTH);
        dealCards.setIcon(new ImageIcon(image));
        dealCards.addActionListener(new cardListener());

        mailCards.setSize(width * (2 / 16) - 50, height * 1 / 7);
        imageURL = cldr.getResource("resources/images/mailCard.png");
        image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(width * (2 / 16) - 20, height * 1 / 7 - 15, Image.SCALE_SMOOTH);
        mailCards.setIcon(new ImageIcon(image));
        mailCards.addActionListener(new cardListener());

        dealCards.setEnabled(false);
        mailCards.setEnabled(false);

        imageURL = cldr.getResource("resources/images/bg_green.png");
        image = new ImageIcon(imageURL).getImage();
        panel = new JLayeredPaneExtension(image.getScaledInstance(width, height, Image.SCALE_DEFAULT));

        this.setPreferredSize(new Dimension(width, height));
        this.add(panel);

        payDayImage.setIcon(getImageScaled("src/resources/images/logo.png", width * 6 / 8, height * 1 / 5));
        payDayImage.setBounds(0, 0, width * 6 / 8, height * 1 / 5);
        panel.add(payDayImage);

        p1.setSize(width * 2 / 8 - 50, height * 2 / 7);
        p1.setBounds((width * 6 / 8) + 30, 10, width * 2 / 8 - 50, height * 2 / 7);
        p1.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.YELLOW));

        p2.setSize(width * 2 / 8 - 50, height * 2 / 7);
        p2.setBounds((width * 6 / 8) + 30, height * 5 / 7 - 40, width * 2 / 8 - 50, height * 2 / 7 - 20);
        p2.setBorder(BorderFactory.createMatteBorder(1, 5, 1, 1, Color.cyan));

        mailCards.setBounds((width * 6 / 8) + 30, height * 1 / 2 + 20, width * 2 / 16 - 40, height * 1 / 10);
        dealCards.setBounds(width * 7 / 8 + 20, height * 1 / 2 + 20, width * 2 / 16 - 40, height * 1 / 10);

        panel.add(dealCards);
        panel.add(mailCards);
        playerPanel();
        panel.add(p1);
        panel.add(p2);

        setInfobox();
        panel.add(InfoBox);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        menuInitialize();
        menuBar.add(menu);
        pack();
        start();
    }

    /**
     * Initializes the starting units of the game
     */
    private void start(){

        controller.initGame();
        controller.initTable();

        boardPositions();
        panel.add(table);

        paintPawn("Player 2", controller.p2.getPosition());
        paintPawn("Player 1", controller.p1.getPosition());
        paintPlayer();

        paintDice("Player 1", 6);
        paintDice("Player 2", 6);
        paintInfoBox("");
    }

    /**
     * Initializes a new Game
     */
    public void newGame(){
        this.setVisible(false);
        new View();
    }

    /**
     * Sets the InfoBox
     */
    public void setInfobox(){
        InfoBox.setBounds((width * 6 / 8) + 30, height * 1 / 3, width * 2 / 8 - 50, p1.getHeight() / 2 + 20);
        InfoBox.setSize(width * 2 / 8 - 50, p1.getHeight() / 2 + 20);
        InfoBox.setLayout(new BoxLayout(InfoBox, BoxLayout.Y_AXIS));
        InfoBox.setBackground(Color.WHITE);
        InfoBox.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.black), BorderFactory.createCompoundBorder(
                        BorderFactory.createRaisedBevelBorder(), BorderFactory.createLoweredBevelBorder())));

        info.setEditable(false);
        info.setBorder(null);
        info.setOpaque(false);

        turn.setEditable(false);
        turn.setOpaque(false);
        turn.setBorder(null);
        command.setEditable(false);
        command.setOpaque(false);
        command.setBorder(null);
        monthsLeft.setEditable(false);
        monthsLeft.setOpaque(false);
        monthsLeft.setBorder(null);

        paintInfoBox("");

        InfoBox.add(info);
        InfoBox.add(monthsLeft);
        InfoBox.add(turn);
        InfoBox.add(command);
    }

    /**
     *Initializes the board with the positions
     */
    public void boardPositions(){

        table.setBounds(10, height * 1 / 5 + 10, payDayImage.getWidth() - 10, height - payDayImage.getHeight() - 80);
        table.setSize(payDayImage.getWidth() - 10, height - payDayImage.getHeight() - 70);
        GridLayout grid = new GridLayout(0, 7);
        table.setOpaque(false);
        table.setLayout(grid);

        for(int i = 0; i < 32; i++){
            position[i] = new JDesktopPane();
            temp = new JTextField();

            temp.setText(controller.table[i].getName() + " " + controller.table[i].getIndex());
            imageURL = cldr.getResource(controller.table[i].getImage());
            Image pos = new ImageIcon(imageURL).getImage();
            pawn_position[i] = new JLayeredPaneExtension(pos.getScaledInstance(width * 1 / 9, height * 1 / 9, Image.SCALE_SMOOTH));

            pawn_position[i].setLayout(new FlowLayout());

            temp.setEditable(false);
            temp.setOpaque(true);
            temp.setBackground(Color.YELLOW);
            temp.setFont(new Font(null, Font.BOLD, 12));
            temp.setMaximumSize(new Dimension(width * 1 / 9, 20));

            position[i].setLayout(new BoxLayout(position[i], BoxLayout.Y_AXIS));
            position[i].add(temp);
            position[i].add(pawn_position[i]);
            table.add(position[i]);
        }

        jackpotPanel.setLayout(new BoxLayout(jackpotPanel, BoxLayout.Y_AXIS));
        jackpotText.setOpaque(false);

        paintJackpot();
        jackpotText.setEditable(false);
        jackpotText.setOpaque(false);
        jackpotText.setHorizontalAlignment(JTextField.CENTER);
        jackpotText.setFont(new Font(null, Font.BOLD, 12));
        jackpotText.setForeground(Color.white);
        jackpotText.setBorder(null);

        ImageIcon ins = getImageScaled("src/resources/images/jackpot.png", width * 1 / 9, height * 1 / 9);
        jackpotLabel.setIcon(ins);
        jackpotPanel.add(jackpotLabel);
        jackpotPanel.add(jackpotText);
        jackpotPanel.setOpaque(false);

        JLabel dummy = new JLabel();
        table.add(dummy);
        table.add(jackpotPanel);
    }

    /**
     * Initializes the menu buttons
     */
    public void menuInitialize(){
        JMenuItem item;
        menu = new JMenu("Game Options");
        item = new JMenuItem("New Game");
        item.addActionListener(new menuListener());
        menu.add(item);
        item = new JMenuItem("Exit Game");
        item.addActionListener(new menuListener());
        menu.add(item);
    }

    /**
     * Initializes the Player1 and Player2 panels
     */
    public void playerPanel(){

        //PLAYER 1
        paintPlayer();
        p1.setLayout(new BorderLayout());
        p1.add(p1Name, BorderLayout.NORTH);

        JDesktopPane temp0 = new JDesktopPane();
        temp0.setLayout(new BoxLayout(temp0, BoxLayout.Y_AXIS));

        balance1Text.setEditable(false);
        balance1Text.setOpaque(false);
        balance1Text.setBorder(null);
        temp0.add(balance1Text);

        loan1Text.setEditable(false);
        loan1Text.setOpaque(false);
        loan1Text.setBorder(null);
        temp0.add(loan1Text);

        bills1Text.setOpaque(false);
        bills1Text.setBorder(null);
        bills1Text.setEditable(false);
        temp0.add(bills1Text);
        DealCards1.setText("My Deal Cards");
        DealCards1.addActionListener(new buttonListener1());
        temp0.add(DealCards1);
        p1.add(temp0, BorderLayout.CENTER);

        JDesktopPane temp1 = new JDesktopPane();
        getLoanP1.setText("Get Loan");
        getLoanP1.addActionListener(new buttonListener1());
        endTurnP1.setText("End Turn");
        endTurnP1.addActionListener(new buttonListener1());
        temp1.setLayout(new BoxLayout(temp1, BoxLayout.X_AXIS));
        temp1.add(getLoanP1);
        temp1.add(endTurnP1);
        p1.add(temp1, BorderLayout.SOUTH);
        diceP1.setBackground(Color.WHITE);
        diceP1.setBorder(null);
        diceP1.addActionListener(new diceListener());
        paintDice("Player 1", 1);
        p1.add(diceP1, BorderLayout.EAST);

        //PLAYER 2
        p2.setLayout(new BorderLayout());
        p2.add(p2Name, BorderLayout.NORTH);

        JDesktopPane temp2 = new JDesktopPane();
        temp2.setLayout(new BoxLayout(temp2, BoxLayout.Y_AXIS));

        balance2Text.setEditable(false);
        balance2Text.setOpaque(false);
        balance2Text.setBorder(null);
        temp2.add(balance2Text);

        loan2Text.setEditable(false);
        loan2Text.setOpaque(false);
        loan2Text.setBorder(null);
        temp2.add(loan2Text);

        bills2Text.setOpaque(false);
        bills2Text.setBorder(null);
        bills2Text.setEditable(false);
        temp2.add(bills2Text);
        DealCards2.setText("My Deal Cards");
        DealCards2.addActionListener(new buttonListener2());
        temp2.add(DealCards2);
        p2.add(temp2, BorderLayout.CENTER);

        JDesktopPane temp3 = new JDesktopPane();
        getLoanP2.setText("Get Loan");
        getLoanP2.addActionListener(new buttonListener2());
        endTurnP2.setText("End Turn");
        endTurnP2.addActionListener(new buttonListener2());
        temp3.setLayout(new BoxLayout(temp3, BoxLayout.X_AXIS));
        temp3.add(getLoanP2);
        temp3.add(endTurnP2);

        diceP2.setBackground(Color.WHITE);
        diceP2.setBorder(null);
        diceP2.addActionListener(new diceListener());
        paintDice("Player 2", 1);
        p2.add(diceP2, BorderLayout.EAST);
        p2.add(temp3, BorderLayout.SOUTH);
    }


    /**
     * This method impelements the action that the card makes with graphics
     * @param p the player that draws the card
     * @param c the card that was drawn and makes the action
     */
    public void mailCardGfxAction(Player p, MailCard c){
        if(c instanceof Advertisement){

        }else if(c instanceof Bill){
            Bill myCard = (Bill) c;
            myCard.action(p);
            paintInfoBox("Bills added");
            paintPlayer();
        }else if(c instanceof Charity){
            Charity myCard = (Charity) c;
            myCard.action(p, controller.jackpot);
            paintInfoBox("Charity paid");
            paintPlayer();
            paintJackpot();
        }else if(c instanceof TakeFromNeighbor){
            TakeFromNeighbor myCard = (TakeFromNeighbor) c;
            myCard.action(p);
            paintInfoBox(p.getName() + " got the money");
            paintPlayer();

        }else if(c instanceof MoveToDealBuyer){
            MoveToDealBuyer myCard = (MoveToDealBuyer) c;
            pawn_position[p.getPosition()].removeAll();
            if (p.getPosition() == p.getOpponent().getPosition())
                paintPawn(p.getOpponent().getName(), p.getOpponent().getPosition());

            pawn_position[p.getPosition()].repaint();
            p.setPosition(((MoveToDealBuyer) c).action(p, controller.table) - p.getPosition());
            paintPawn(p.getName(), p.getPosition());

            if(((MoveToDealBuyer) c).action(p, controller.table) % 7 == 0 && p.getPosition() % 7 == 0)
                Sunday(p);

            if(((MoveToDealBuyer) c).action(p, controller.table) % 7 == 4 && p.getPosition() % 7 == 4)
                Thursday(p);
            positionGraphics(p, p.getPosition());

        }else if(c instanceof PayTheNeighbor){
            PayTheNeighbor myCard = (PayTheNeighbor) c;
            myCard.action(p);
            paintInfoBox(p.getOpponent().getName() + " got the money");
            paintPlayer();
        }
    }

    /**
     * Pops up the Sunday window
     * @param p the player that is on Sunday Position
     */
    public void Sunday(Player p){
        if(controller.table[p.getPosition()].isSunday()){
            SundayWindow win = new SundayWindow();
            if(controller.isSunday(p, win.getBet())){
                SundayWindow win1 = new SundayWindow(true);
            }else if(win.getBet() != 3){
                SundayWindow win1 = new SundayWindow(false);
            }
            paintPlayer();
        }
    }

    /**
     * Pops up the Thursday window
     * @param p the player that is on Thursday Position
     */
    public void Thursday(Player p){
        if(controller.table[p.getPosition()].isThursday()){
            ThursdayWindow win = new ThursdayWindow();
            if(controller.isThursday(p, win.getBet()) == 2){
                ThursdayWindow win1 = new ThursdayWindow(2);
            }else if(controller.isThursday(p, win.getBet()) == 1){
                ThursdayWindow win1 = new ThursdayWindow(1);
            }else if(controller.isThursday(p, win.getBet()) == 0){
                ThursdayWindow win1 = new ThursdayWindow(0);
            }
            paintPlayer();
        }
    }

    /**
     * This method moves the player into the board
     * @param e the action event taken from the dicelistener
     */
    public void playerMove(ActionEvent e){
        Dice dice1 = controller.p1.getDice();
        Dice dice2 = controller.p2.getDice();

        if(e.getSource() == diceP1){
            paintDice("Player 1", dice1.rollTheDice());
            if(dice1.getRoll()){
                diceP1.setEnabled(false);
                if(controller.start){
                    if(dice1.getNumber() == 6){
                        controller.jackpot(controller.p1);
                        paintJackpot();
                        paintPlayer();
                    }
                    if(controller.p1.getPosition() == 0){
                        pawn_position[31].removeAll();
                        pawn_position[31].repaint();
                    }
                    pawn_position[controller.p1.getPosition()].removeAll();

                    if(controller.p1.getPosition() == controller.p2.getPosition())
                        paintPawn("Player 2", controller.p2.getPosition());

                    pawn_position[controller.p1.getPosition()].repaint();
                    controller.p1.setPosition(dice1.getNumber());
                    paintPawn("Player 1", controller.p1.getPosition());
                    Thursday(controller.p1);
                    Sunday(controller.p1);
                    positionGraphics(controller.p1, controller.p1.getPosition());
                }
            }
        }else{
            paintDice("Player 2", dice2.rollTheDice());
            if(dice2.getRoll()){
                diceP2.setEnabled(false);

                if(controller.start){
                    if(dice2.getNumber() == 6){
                        controller.jackpot(controller.p2);
                        paintJackpot();
                        paintPlayer();
                    }
                    if(controller.p2.getPosition() == 0){
                        pawn_position[31].removeAll();
                        pawn_position[31].repaint();
                    }
                    pawn_position[controller.p2.getPosition()].removeAll();

                    if(controller.p1.getPosition() == controller.p2.getPosition())
                        paintPawn("Player 1", controller.p1.getPosition());

                    pawn_position[controller.p2.getPosition()].repaint();
                    controller.p2.setPosition(dice2.getNumber());
                    paintPawn("Player 2", controller.p2.getPosition());
                    Thursday(controller.p2);
                    Sunday(controller.p2);
                    positionGraphics(controller.p2, controller.p2.getPosition());
                }
            }
        }
        startingPlayer();
    }

    /**
     * This method is used to implement the graphics action of a certain position
     * @param p the player on the position
     * @param position the number index of the position
     */
    private void positionGraphics(Player p, int position){
        if(controller.table[position] instanceof DealPosition){
            paintInfoBox(p.getName() + " Draw a Deal Card");
            p.setPending(true);
        }else if(controller.table[controller.p1.getPosition()] instanceof RadioContest && controller.p1.getTurn() || controller.table[controller.p2.getPosition()] instanceof RadioContest && controller.p2.getTurn()){
            radioPos = true;
            diceP1.setEnabled(true);
            diceP2.setEnabled(true);
            paintInfoBox("Throw the dices");
            controller.p1.getDice().setRoll(false);
            controller.p2.getDice().setRoll(false);
        }else if(controller.table[controller.p1.getPosition()] instanceof Lottery && controller.p1.getTurn() || controller.table[controller.p2.getPosition()] instanceof Lottery && controller.p2.getTurn()){
            lotteryPos = true;
            ArrayList<Object> bet = new ArrayList();
            bet.add("1");
            bet.add("2");
            bet.add("3");
            bet.add("4");
            bet.add("5");
            bet.add("6");
            LotteryWindow win1 = new LotteryWindow(controller.p1, bet);
            choiseP1 = Integer.parseInt(win1.getBet());
            bet.remove(choiseP1 -1);

            LotteryWindow win2 = new LotteryWindow(controller.p2, bet);
            choiseP2 = Integer.parseInt(win2.getBet());
            paintInfoBox("Player 1 bet: " + choiseP1 + " Player 2 bet " + choiseP2);
            if(controller.p1.getTurn()){
                diceP1.setEnabled(true);
                controller.p1.getDice().setRoll(false);
            }else if(controller.p2.getTurn()){
                diceP2.setEnabled(true);
                controller.p2.getDice().setRoll(false);
            }
        }else if(controller.table[position] instanceof PayDayPosition){

            PayDayPosition pos = (PayDayPosition) controller.table[position];
            if(p.getLoans() != 0){
                PayDayWindow window = new PayDayWindow();
                if(window.getPChoice() == 0){
                    pos.action(p, window.getPChoice(), 0);
                }else if(window.getPChoice() == 1){
                    int part = Integer.parseInt(window.partOfLoan());
                    while(part >= p.getBalance()){
                        part = Integer.parseInt(window.partOfLoan());
                    }
                    pos.action(p, 1, part);
                }

            }else{
                pos.action(p, -1, 0);
            }
            controller.throwAllCards(p);
            if(controller.gameFinish()){
                String s = controller.gameWinner();
                paintInfoBox("Player 1 Points: " + controller.pointsP1 + " Player 2 Points: " + controller.pointsP2);
                paintPlayer();
                Window window = new Window("Start new game", "Exit", s, "Τελος Παιχνιδιού", "src/resources/images/logo.png");
                if(window.getOption() == 1)
                    newGame();
                else if(window.getOption() == 2)
                    System.exit(0);
            }
            paintPlayer();
        }else if(controller.table[position] instanceof Buyer){
            if (p.noDealCards()){
                p.setPending(false);
                paintInfoBox(p.getName() + ": Has no cards to sell");
            }else{
                paintInfoBox(p.getName() + ": Sell a card");
                p.setPending(true);
            }
        } else if (controller.table[position] instanceof MailCardPosition) {
            MailCardPosition pos = (MailCardPosition) controller.table[position];
            if(pos.drawCard())
                paintInfoBox(p.getName() + " Draw one Mail Card");
            else
                paintInfoBox(p.getName() + " Draw two Mail Cards");

            p.setPending(true);
        }else if(controller.table[position] instanceof FamilyCasino){
            FamilyCasino pos = (FamilyCasino) controller.table[position];
            if(pos.action(p, controller.jackpot)){
                paintInfoBox(p.getName() + " Won in Family Casino 500 euro");
                paintPlayer();
            }else{
                paintInfoBox(p.getName() + " Lost in Family Casino 500 euro");
                paintJackpot();
                paintPlayer();
            }
        } else if(controller.table[position] instanceof Sweepstakes || controller.table[position] instanceof YardSale){
            p.setPending(true);
            paintInfoBox("Throw the Dice again");

            if(p.getName().equals("Player 1")){
                diceP1.setEnabled(true);
            }else{
                diceP2.setEnabled(true);
            }
        }
    }

    /**
     * This method is used to show the player that gets a loan
     * @param p the player that gets the loan
     */
    public void getLoan(Player p){
        LoanWindow loan = new LoanWindow();
        if(Integer.parseInt(loan.getLoanOption()) != 0){
            p.setLoans(Integer.parseInt(loan.getLoanOption()));
            p.setBalance(Integer.parseInt(loan.getLoanOption()));
            paintPlayer();
            paintInfoBox(p.getName() + " got " + Integer.parseInt(loan.getLoanOption()) + " loan");
        }
    }

    /**
     * This method is used to pop up the message that a player sold Deal Cards
     * @param p the player that sells the Deal Cards
     */
    public void playerDealCards(Player p){
        if(!p.noDealCards() && p.getPending()){
            DealCard playerDealCard = p.sellCard();
            showDealCard(playerDealCard, "sell");
            p.setPending(false);
            p.setBalance(playerDealCard.getSell());
            controller.RejectedCards.push(playerDealCard);
            paintPlayer();
            paintInfoBox(p.getName() + " : Sold the card");
        }
    }

    /**
     * Shows the information of every player (money, loans, bills)
     */
    private void paintPlayer(){
        balance1Text.setText("Money: " + controller.p1.getBalance() + " Euro");
        balance2Text.setText("Money: " + controller.p2.getBalance() + " Euro");
        loan1Text.setText("Loan: " + controller.p1.getLoans() + " Euro");
        loan2Text.setText("Loan: " + controller.p2.getLoans() + " Euro");
        bills1Text.setText("Bills: " + controller.p1.getBills() + " Euro");
        bills2Text.setText("Bills: " + controller.p2.getBills() + " Euro");
    }

    /**
     * Paints the pawn of the player
     * @param p the player's pawn
     * @param pos the position of the player's pawn
     */
    public void paintPawn(String p, int pos){
        JLabel pawn = new JLabel();

        if(p.equals("Player 1")){
            ins = getImageScaled("src/resources/images/pawn_yellow.png", 50, 50);
            pawn.setIcon(ins);
        }else{
            ins = getImageScaled("src/resources/images/pawn_blue.png", 50, 50);
            pawn.setIcon(ins);
        }
        pawn_position[pos].add(pawn);
        Graphics g1 = position[pos].getGraphics();
        position[pos].paintComponents(g1);
        position[pos].repaint();
    }

    /**
     * Paints the money of the jackpot
     */
    public void paintJackpot(){
        jackpotText.setText(controller.jackpot.getMoney() + " Euro");
    }

    /**
     * Paints the InfoBox
     * @param text the text of the InfoBox
     */
    private void paintInfoBox(String text){
        monthsLeft.setText(controller.gameLog()[0]);
        turn.setText(controller.gameLog()[1]);
        command.setText(text);
    }

    /**
     * Renews the Dice that is shown to the table
     * @param p the player's Dice
     * @param diceNumber the number of the dice to renew
     */
    private void paintDice( String p, int diceNumber){
        if(p.equals("Player 1")){
            imageURL = cldr.getResource("resources/images/dice-" + diceNumber + ".jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            diceP1.setIcon(new ImageIcon(image));
        }else{
            imageURL = cldr.getResource("resources/images/dice-" + diceNumber + ".jpg");
            image = new ImageIcon(imageURL).getImage();
            image = image.getScaledInstance(70, 70, Image.SCALE_SMOOTH);
            diceP2.setIcon(new ImageIcon(image));
            diceP2.setBorder(null);
        }
    }

    /**
     *
     * @param C the DealCard that is going to show
     * @param option buy or sell option
     * @return the choice that the player chooses
     */
    public int showDealCard(DealCard C, String option){
        Object[] options = {"Buy", "Ignore Deal"};
        Object[] options2 = {"OK"};
        int n;

        URL imageURL = cldr.getResource("resources/images/" + C.getImage()); //image
        Image image = new ImageIcon(imageURL).getImage();
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);
        JOptionPane p = new JOptionPane();
        if(option.equals("show")){
            n = p.showOptionDialog(this, C.getText() + "\nΤιμή Αγοράς: " + C.getMoney() + " Ευρώ \nΤιμή Πώλησης: " + C.getSell() + " Ευρώ \n",
                    "DealCard", JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
        }else{
            n = p.showOptionDialog(this, C.getText() + "\nΤιμή Αγοράς: " + C.getMoney() + " Ευρώ \nΤιμή Πώλησης: " + C.getSell() + " Ευρώ \n",
                    "DealCard", JOptionPane.OK_OPTION, 0, new ImageIcon(image), options2, options2[0]);
        }
        return n;
    }

    /**
     *
     * @param c the card that is going to be shown
     */
    public void showMailCard(Card c){
        String s1 = "";
        String s = "";
        URL imageURL2;
        imageURL2 = cldr.getResource("resources/images/" + c.getImage());
        Image image = null;
        image = new ImageIcon(imageURL2).getImage();//see the bug
        image = image.getScaledInstance(200, 200, java.awt.Image.SCALE_SMOOTH);

        JOptionPane p = new JOptionPane();
        if(c instanceof PayTheNeighbor){
            s1 = "Πλήρωσε το γείτονα";
            s = "Πλήρωσε " + c.getMoney() + " Ευρώ στον αντίπαλο";
        }else if (c instanceof TakeFromNeighbor){
            s1 = "Πάρε λεφτά από τον γείτονα";
            s = "Πάρε " + c.getMoney() + " Ευρώ από τον αντίπαλο";
        }else if (c instanceof Charity){
            s1 = "Φιλανθρωπία";
            s = "Πλήρωσε " + c.getMoney() + " Ευρώ στο Jackpot";
        }else if (c instanceof Bill){
            s1 = "Εξόφληση λογαριασμού";
            s = "Κράτα το λογαριασμό";
        }else if (c instanceof MoveToDealBuyer){
            s1 = "Μετακίνηση σε θέση Συμφωνίας/Αγοραστή";
            s = "Εντάξει";
        }else if (c instanceof Advertisement){
            s1 = "Διαφήμιση";
            s = "Εντάξει";
        }
        Object[] options = {s};
        int n = p.showOptionDialog(this, c.getText(), s1, JOptionPane.OK_OPTION, 0, new ImageIcon(image), options, options[0]);
    }

    static ImageIcon getImageScaled(String image, int width, int height){
        Image newimg = new ImageIcon(image).getImage().getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(newimg);
    }


    /**
     * ActionListener for the cards
     */
    private class cardListener implements ActionListener{

        Player player;

        @Override
        public void actionPerformed(ActionEvent e){
            if(controller.p1.getTurn())
                player = controller.p1;
            else if(controller.p2.getTurn())
                player = controller.p2;

            if(e.getSource() == dealCards && controller.table[player.getPosition()] instanceof DealPosition){
                if(player.getPending()){
                    if (controller.DealCardsStack.isStackEmpty())
                        controller.RejectedCards.popAndSeperate(controller.MailCardsStack, controller.DealCardsStack);

                    DealCard myCard = controller.DealCardsStack.pop();
                    if(showDealCard(myCard, "show") == 0){
                        if(myCard.getMoney() > player.getBalance()){
                            paintInfoBox("Must get loan to buy this Card");
                            LoanWindow loan = new LoanWindow();

                            if(myCard.getMoney() <= player.getBalance() + Integer.parseInt(loan.getLoanOption())){
                                player.setLoans(Integer.parseInt(loan.getLoanOption()));
                                player.setBalance(Integer.parseInt(loan.getLoanOption()));
                                myCard.action(player);
                                paintInfoBox(player.getName() + " bought the card ");
                                paintPlayer();
                            }else{
                                paintInfoBox("Not enough money to buy this card");
                                controller.RejectedCards.push(myCard);
                            }
                        }else{
                            myCard.action(player);
                            paintInfoBox(player.getName() + " bought the card ");
                            paintPlayer();
                        }
                    }else{
                        controller.RejectedCards.push(myCard);
                    }
                    player.setPending(false);
                }else{

                }
            }else if(e.getSource() == mailCards && controller.table[player.getPosition()] instanceof MailCardPosition){
                if (controller.MailCardsStack.isStackEmpty())
                    controller.RejectedCards.popAndSeperate(controller.MailCardsStack, controller.DealCardsStack);

                MailCardPosition mailPosition = (MailCardPosition) controller.table[player.getPosition()];
                if(player.getPending()){
                    MailCard myCard = controller.MailCardsStack.pop();
                    showMailCard(myCard);
                    controller.RejectedCards.push(myCard);

                    mailCardGfxAction(player, myCard);
                    if(mailPosition.drawCard() && !(myCard instanceof MoveToDealBuyer)){
                        player.setPending(false);
                        paintInfoBox("Card taken");
                    }else{
                        counter++;
                        if(counter >= 2 && !(myCard instanceof MoveToDealBuyer)){
                            player.setPending(false);
                            paintInfoBox("Card taken");
                            counter = 0;
                        }
                    }
                }
            }
        }
    }

    /**
     * Action Listener for Player 1
     */
    private class buttonListener1 implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();
            if(controller.p1.getTurn()){
                if(command.equals("End Turn") && controller.p1.getDice().getRoll()){
                    if(controller.p1.getPending()){

                    }else if(radioPos||lotteryPos){

                    }
                    else if(controller.p2.getFinish()){
                        if(controller.p1.getFinish()){
                            controller.p1.setTurn(false);
                        }else{
                            diceP1.setEnabled(true);
                            controller.p1.getDice().setRoll(false);
                            controller.p1.setTurn(true);
                            paintInfoBox("");
                        }
                    }else{
                        controller.p2.setTurn(true);
                        controller.p1.setTurn(false);
                        diceP2.setEnabled(true);
                        controller.p1.getDice().setRoll(false);
                        paintInfoBox("");
                    }
                } else if(command.equals("Get Loan")){
                    getLoan(controller.p1);
                } else if(command.equals("My Deal Cards") && controller.table[controller.p1.getPosition()] instanceof Buyer){
                    playerDealCards(controller.p1);
                }
            }
        }
    }

    /**
     * Action Listener for Player 2
     */
    private class buttonListener2 implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e){
            if(controller.p2.getTurn()){
                String command = e.getActionCommand();
                if(command.equals("End Turn") && controller.p2.getDice().getRoll()){
                    if (controller.p2.getPending()){

                    }else if(radioPos||lotteryPos){

                    }else if (controller.p1.getFinish()){
                        if(controller.p2.getFinish()){
                            paintInfoBox("Game is probably finished");
                            controller.p2.setTurn(false);
                        }else{
                            diceP2.setEnabled(true);
                            controller.p2.getDice().setRoll(false);
                            controller.p2.setTurn(true);
                            paintInfoBox("");
                        }
                    }else{
                        controller.p1.setTurn(true);
                        controller.p2.setTurn(false);
                        diceP1.setEnabled(true);
                        controller.p2.getDice().setRoll(false);
                        paintInfoBox("");
                    }
                }else if (command.equals("Get Loan")){
                    getLoan(controller.p2);
                }else if (command.equals("My Deal Cards") && controller.table[controller.p2.getPosition()] instanceof Buyer){
                    playerDealCards(controller.p2);
                }
            }
        }
    }


    /**
     * ActionListener for the Menu
     */
    private class menuListener implements ActionListener{

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e){
            String command = e.getActionCommand();

            if(command.equals("New Game")){
                Window win = new Window("Yes", "No", "Do you want to play a new game?", command, null);
                if (win.getOption() == 1)
                    newGame();
            }else if(command.equals("Exit Game")){
                Window win2 = new Window("Yes", "No", "Do you want to exit?", command, null);
                if (win2.getOption() == 1)
                    System.exit(0);
            }
        }
    }

    /**
     * ActionListener for the Dice
     */
    private class diceListener implements ActionListener{

        Dice dice1 = controller.p1.getDice();
        Dice dice2 = controller.p2.getDice();
        int c1;
        int c2;

        /**
         * Invoked when an action occurs.
         *
         * @param e
         */
        @Override
        public void actionPerformed(ActionEvent e){
            if(e.getActionCommand() != null){
                if (e.getSource() == diceP1 && controller.p1.getPending()){
                    paintDice( "Player 1", dice1.rollTheDice());
                    diceP1.setEnabled(false);
                    if(controller.table[controller.p1.getPosition()] instanceof Sweepstakes){
                        Sweepstakes sweepstakesPos = (Sweepstakes) controller.table[controller.p1.getPosition()];
                        sweepstakesPos.action(controller.p1, dice1.getNumber());
                        paintPlayer();
                        paintInfoBox(controller.p1.getName() + " Won " + dice1.getNumber() * 1000 + " euro");
                        controller.p1.setPending(false);
                    }else if(controller.table[controller.p1.getPosition()] instanceof YardSale){
                        YardSale yardsalePos = (YardSale) controller.table[controller.p1.getPosition()];
                        DealCard c = controller.DealCardsStack.pop();
                        yardsalePos.action(controller.p1, dice1.getNumber(), c);
                        showDealCard(c, "");
                        paintPlayer();
                        paintInfoBox(controller.p1.getName() + " bought this card for " + dice1.getNumber() * 100);
                        controller.p1.setPending(false);
                    }
                }else if(e.getSource() == diceP2 && controller.p2.getPending()){
                    paintDice("Player 2", dice2.rollTheDice());
                    diceP2.setEnabled(false);

                    if(controller.table[controller.p2.getPosition()] instanceof Sweepstakes){
                        Sweepstakes sweepstakesPos = (Sweepstakes) controller.table[controller.p2.getPosition()];
                        sweepstakesPos.action(controller.p2, dice2.getNumber());
                        paintPlayer();
                        paintInfoBox(controller.p2.getName() + " Won " + dice2.getNumber() * 1000 + " euro");
                        controller.p2.setPending(false);
                    }else if(controller.table[controller.p2.getPosition()] instanceof YardSale){
                        YardSale yardsalePos = (YardSale) controller.table[controller.p2.getPosition()];
                        DealCard c = controller.DealCardsStack.pop();
                        yardsalePos.action(controller.p2, dice2.getNumber(), c);
                        showDealCard(c, "");
                        paintPlayer();
                        paintInfoBox(controller.p2.getName() + " bought this card for " + dice2.getNumber() * 100);
                        controller.p2.setPending(false);
                    }
                }else if(radioPos){
                    if(e.getSource() == diceP1){
                        paintDice("Player 1", dice1.rollTheDice());
                        diceP1.setEnabled(false);
                    }
                    else{
                        paintDice("Player 2", dice2.rollTheDice());
                        diceP2.setEnabled(false);
                    }

                    if(dice1.getRoll() && dice2.getRoll()){
                        if(dice1.getNumber() == dice2.getNumber()){
                            diceP1.setEnabled(true);
                            diceP2.setEnabled(true);
                            dice1.setRoll(false);
                            dice2.setRoll(false);
                            paintInfoBox("Throw the dices");
                        }else{
                            if(dice1.getNumber() > dice2.getNumber()){
                                controller.p1.setBalance(1000);
                                paintPlayer();
                                paintInfoBox("Player 1 won 1000 from the Radio Contest");
                            }else{
                                controller.p2.setBalance(1000);
                                paintPlayer();
                                paintInfoBox("Player 2 won 1000 from the Radio Contest");
                            }
                            radioPos = false;
                        }
                    }
                }else if (lotteryPos){
                    if(e.getSource() == diceP1){
                        paintDice("Player 1", dice1.rollTheDice());
                        diceP1.setEnabled(false);
                        if(dice1.getNumber() == choiseP1) {
                            controller.p1.setBalance(1000);
                            paintPlayer();
                            paintInfoBox("Player 1 won 1000 euro from the Lottery");
                            lotteryPos = false;
                        }else{
                            diceP2.setEnabled(true);
                        }
                    }
                    else{
                        paintDice("Player 2", dice2.rollTheDice());
                        diceP2.setEnabled(false);
                        if(dice2.getNumber() == choiseP2) {
                            controller.p2.setBalance(1000);
                            paintPlayer();
                            paintInfoBox("Player 2 won 1000 euro from the Lottery");
                            lotteryPos = false;
                        }else{
                            diceP1.setEnabled(true);
                        }
                    }
                }else{
                    playerMove(e);
                }
            }
        }
    }

        /**
         * This method sets the player that is going to play first
         */
        public void startingPlayer(){
            Dice dice1 = controller.p1.getDice();
            Dice dice2 = controller.p2.getDice();
            if(dice1.getRoll() && dice2.getRoll() && !controller.p1.getTurn() && !controller.p2.getTurn()){
                if(dice1.getNumber() == dice2.getNumber()){
                    diceP1.setEnabled(true);
                    diceP2.setEnabled(true);
                    dice1.setRoll(false);
                    dice2.setRoll(false);
                }else{
                    controller.playingFirst(dice1.getNumber(), dice2.getNumber());
                    dealCards.setEnabled(true);
                    mailCards.setEnabled(true);

                    controller.start = true;
                    paintInfoBox("");
                    if (controller.p1.getTurn())
                        diceP1.setEnabled(true);
                    else
                        diceP2.setEnabled(true);

                    dice1.setRoll(false);
                    dice2.setRoll(false);
                }
            }
        }
}
