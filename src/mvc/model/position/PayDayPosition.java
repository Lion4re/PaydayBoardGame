package mvc.model.position;

import mvc.model.Player;

public class PayDayPosition extends Position{

    /**
     * Constructor:<br>instantiates a new PayDay position
     * Preconditions:<br>image URL must be valid
     * @param image the image URL of the PayDay position
     */
    public PayDayPosition(String image) {
        super(31, image);
    }

    /**
     * Mutative Transformer:<br>In this position the player must do all the actions of the PayDay!
     * @param p the player that will take the action
     * @param loanOrPart all the loan of the player or just a part of the loan to pay
     * @param partOfLoan the part of the loan that the player is going to pay (if he choose to pay a part)
     */
    public void action(Player p, int loanOrPart, int partOfLoan){
        p.setBalance(3500);
        p.setMonthsCounter(p.getMonthsCounter() - 1);

        if(p.getBalance() < p.getBills()){
            p.setLoans(p.getBills() - p.getBalance());
            p.setBalance(-p.getBalance());
            p.setBills(-p.getBills());
        }else{
            p.setBalance(-p.getBills());
            p.setBills(-p.getBills());
        }


        int tax = p.getLoans() / 10;
        if(p.getBalance() < tax){
            p.setLoans(tax - p.getBalance());
            p.setBalance(-p.getBalance());
        }else{
            p.setBalance(-tax);
        }


        if(loanOrPart == 0){
            if(p.getBalance() < p.getLoans()){
                p.setLoans(- p.getBalance());
                p.setBalance(-p.getBalance());
            }else{
                p.setBalance(-p.getLoans());
                p.setLoans(-p.getLoans());
            }
        }else if(loanOrPart == 1){
            if(p.getBalance() < partOfLoan){
                p.setLoans(partOfLoan - p.getBalance());
                p.setBalance(-p.getBalance());
            }else{
                p.setBalance(-partOfLoan);
                p.setLoans(-partOfLoan);
            }
        }

        if(p.getMonthsCounter() <= 0)
            p.setFinish(true);

        if(!p.getFinish())
            p.setPosition(-31);
    }
}
