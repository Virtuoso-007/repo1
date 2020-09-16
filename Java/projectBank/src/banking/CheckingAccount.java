package banking;

public class CheckingAccount extends Account {
    SavingsAccount protectedBy=null;
    public CheckingAccount(double init_balance) {
        super(init_balance);
    }

    public CheckingAccount(double init_balance, SavingsAccount protect) {
        super(init_balance);
        this.protectedBy=protect;
    }
    public CheckingAccount(double init_balance, double protect) {
        super(init_balance);
        this.protectedBy.interestRate=protect;
    }

    public void withdraw(double amt) throws OverdraftException {
        if(balance>=amt){
            balance-=amt;
        }else if((balance+protectedBy.interestRate)>=amt){
            protectedBy.interestRate+=balance-amt;
            balance=0.00;
        }else throw new OverdraftException("加上余额也不够！",amt-balance-protectedBy.interestRate);

    }
}
