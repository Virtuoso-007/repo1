package banking;

public class SavingsAccount extends Account {
    double interestRate;


    public SavingsAccount(double balance, double interestRate) {
        super(balance);
        this.interestRate=interestRate;
    }
}
