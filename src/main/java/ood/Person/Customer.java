/*Customer.java: This is a child class that extends the Person.java. Each Customer
object will have an Accounts variable for storing all of their accounts, a Transactions
variable for storing all of their transactions, and a CustomerStocks variable for
storing all the stocks they are holding. It also has methods for making all types of transactions.
 */
package ood.Person;

import ood.Account.*;
import ood.Money.Currency;
import ood.Money.Wallet;
import ood.Stock.CustomerStocks;
import ood.Stock.Stock;
import ood.Transaction.BuyStockTxn;
import ood.Transaction.CloseAccountTxn;
import ood.Transaction.DepositTxn;
import ood.Transaction.ExchangeTxn;
import ood.Transaction.OpenAccountTxn;
import ood.Transaction.Transactions;
import ood.Transaction.TransferTxn;
import ood.Transaction.WithdrawTxn;
import ood.utils.DBReader;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.UUID;

public class Customer extends Person{
  private Accounts accounts;
  private Transactions transactions;
  private CustomerStocks stocks;

  private DBReader db = new DBReader();

  public Customer(UUID ID, String username, String password) {
    super(ID, username, password);
    accounts = new Accounts();
    transactions = new Transactions();
    stocks = new CustomerStocks();
  }

  public Accounts getAccounts() {
    return accounts;
  }

  public void setAccounts(Accounts accounts) {
    this.accounts = accounts;
  }

  //has more than $5000.00 and at least 1000 in dollars in at least one savings account
  public boolean canOpenSecurity(){
    return (accounts.getSavingsForSecurity().size()>0);
  }

  public Accounts getCustomerAccounts(){ return this.accounts; }
  public CustomerStocks getCustomerStocks(){ return this.stocks; }

  public void addAccount(Account a){
    accounts.addAccount(a);
    transactions.newTransaction(new OpenAccountTxn(this,a));
  }

  public void retrieveAccount(Account a){
    accounts.addAccount(a);
  }

  public void addStock(Stock s){
    System.out.println("inside customer");
    System.out.println(s);
    stocks.addStock(s);
  }
  public void closeAccount(Account a){
    accounts.closeAccount(a);
    if(a instanceof DepositAccount){
      db.removeData("DepositAccounts", "aid", a.getAid().toString());
    }else if(a instanceof LoanAccount){
      db.removeData("LoanAccounts", "aid", a.getAid().toString());
    } else if (a instanceof SecurityAccount) {
      db.removeData("SecurityAccounts","aid",a.getAid().toString());
    }
    transactions.newTransaction(new CloseAccountTxn(this,a));
  }
  public void withdraw(DepositAccount a, Currency c, double amount){
    db.changeData("DepositAccounts","amount",String.valueOf(a.getBalance() - (c.getExchangeRate() * amount)),"aid",a.getAid().toString());
    accounts.withdraw(a,c,amount);
    transactions.newTransaction(new WithdrawTxn(this,a,c,amount));
  }
  public void deposit(DepositAccount a, Currency c, double amount){

//    LinkedList<LinkedHashMap<String, String>> curTable = db.getTable("Currencies");
    db.changeData("DepositAccounts","amount",String.valueOf(a.getBalance() + (c.getExchangeRate() * amount)),"aid",a.getAid().toString());
    accounts.deposit(a,c,amount);
    transactions.newTransaction(new DepositTxn(this,a,c,amount));
  }
  public void transfer(Account srcAccount, double moneyAmount, Account destAccount){

    if (srcAccount instanceof DepositAccount) {
      db.changeData("DepositAccounts", "amount", String.valueOf(srcAccount.getBalance() - moneyAmount), "aid", srcAccount.getAid().toString());
    } else if (srcAccount instanceof LoanAccount) {
      db.changeData("LoanAccounts", "amount", String.valueOf(srcAccount.getBalance() - moneyAmount), "aid", srcAccount.getAid().toString());
    } else if (srcAccount instanceof SecurityAccount) {
      db.changeData("SecurityAccounts", "amount", String.valueOf(srcAccount.getBalance() - moneyAmount), "aid", srcAccount.getAid().toString());
    }

    if (destAccount instanceof DepositAccount) {
      db.changeData("DepositAccounts", "amount", String.valueOf(destAccount.getBalance() + moneyAmount), "aid", destAccount.getAid().toString());
    } else if (destAccount instanceof LoanAccount) {
      db.changeData("LoanAccounts", "amount", String.valueOf(destAccount.getBalance() + moneyAmount), "aid", destAccount.getAid().toString());
    } else if (destAccount instanceof SecurityAccount) {
      db.changeData("SecurityAccounts", "amount", String.valueOf(destAccount.getBalance() + moneyAmount), "aid", destAccount.getAid().toString());
    }

//    LinkedHashMap<String, String> row = new LinkedHashMap<>();
//    row.put("tid", UUID.randomUUID().toString());
//    row.put("From_aid", srcAccount.getAid().toString());
//    row.put("To_aid", destAccount.getAid().toString());
//    row.put("amount", String.valueOf(moneyAmount));
//    row.put("fee", "10");
//    row.put("date", LocalDateTime.now().toString());
//    row.put("From_accNum", "888");
//    row.put("To_accNum", "999");
//    db.addData("Transactions",row);
    accounts.transfer(srcAccount,moneyAmount,destAccount);
    transactions.newTransaction(new TransferTxn(this,srcAccount,destAccount,moneyAmount));
  }
  public void exchange(DepositAccount a, Currency srcC, Currency destC, double srcAmount){
    accounts.exchange(a,srcC,destC,srcAmount);
    transactions.newTransaction(new ExchangeTxn(this,a,srcC,destC, Wallet.getDollarAmount(srcC,srcAmount)));
  }

  public void newLoan(double amount){
    if(accounts.checkCollateral(amount)){
      UUID aid = UUID.randomUUID();
      LinkedHashMap<String, String> row = new LinkedHashMap<>();
      row.put("uid",this.getID().toString());
      row.put("aid", aid.toString());
      row.put("amount", String.valueOf(amount));
      row.put("accNum", "123456");
      db.addData("LoanAccounts",row);
      this.addAccount(new LoanAccount(aid,this,amount));
    }
    else throw new UnsupportedOperationException("You do not have enough collateral");
  }

  public Transactions getTransactions() {
    return transactions;
  }

  public void setTransactions(Transactions transactions) {
    this.transactions = transactions;
  }

  @Override
  public void write(){

  }


}