package banking;

import java.util.ArrayList;
import java.util.List;

public class Customer {
    private String firstname;
    private String lastname;
    private List<Account> accounts;
    private int numOfAccounts;
    private Account savingAccount=null;
    private Account checkingAccount=null;
    public Customer(String f,String l){
        this.firstname=f;
        this.lastname=l;
        accounts=new ArrayList();
    }

    public String getFirstName() {
        return firstname;
    }

    public String getLastName() {
        return lastname;
    }

    public void setSavingAccount(Account savingAccount) {
        this.savingAccount = savingAccount;
    }

    public void setCheckingAccount(Account checkingAccount) {
        this.checkingAccount = checkingAccount;
    }
    public void addAccount(Account account){
        if(account instanceof CheckingAccount){
            this.checkingAccount=account;
        }else this.savingAccount=account;
        numOfAccounts++;

    }

    public Account getAccount(int index) {
        return account[index];
    }
    public int getNumOfAccounts(){
        return numOfAccounts;
    }

}
