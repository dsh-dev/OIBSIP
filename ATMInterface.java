import java.util.*;
class Transaction{
    String type;
    double amount;
    String details;
    Transaction(String type,double amount,String details){
        this.type=type;
        this.amount=amount;
        this.details=details;
    }
    public String toString(){
        return type+" | Amount: "+amount+" | "+details;
    }
}
class BankAccount{
    private double balance;
    private ArrayList<Transaction> history;
    BankAccount(double balance){
        this.balance=balance;
        this.history=new ArrayList<Transaction>();
    }
    public double getBalance()
    {
        return balance;
    }
    public void deposit(double amount){
        balance+=amount;
        history.add(new Transaction("Deposit",amount,"Money Deposited"));
        System.out.println("₹"+amount+" deposited successfully.");
    }
    public void withdraw(double amount)
    {
        if(amount>balance)
        {
            System.out.println("Insufficient balance.");
        }
        else{
            balance-=amount;
            history.add(new Transaction("Withdraw",amount,"Money Withdrawn"));
            System.out.println("Please collect your cash.");
        } 
    }
    public void transfer(double amount,String receiver)
    {
        if(amount>balance)
        {
            System.out.println("Insufficient balance!");
        }
        else{
            balance-=amount;
            history.add(new Transaction("Transfer",amount,"Transferred to Account" +receiver));
            System.out.println("₹"+amount+" transferred successfully.");
        }
    }
    public void showTransactionHistory()
    {
        if(history.isEmpty())
        {
            System.out.println("No transaction history available.");
        }
        else{
            System.out.println("Transaction History:"); 
            for(Transaction t:history)
            {
                System.out.println(t);
            }
        }
    }
}
class User{
    private String userId;
    private String userPin;
    private BankAccount account;
    User(String userId,String userPin,double balance){
        this.userId=userId;
        this.userPin=userPin;
        this.account=new BankAccount(balance);
    }
    public boolean login(String id,String pin)
    {
        return userId.equals(id) && userPin.equals(pin);
    }
    public BankAccount getAccount(){
        return account;
    }
}

class ATM{
    private User user;
    private Scanner sc;
    ATM(User user)
    {
        this.user=user;
        sc=new Scanner(System.in);
    }
    public void start()
    {
        System.out.println("Welcome to ATM");
        System.out.println("Enter the user id: ");
        String id=sc.nextLine();
        System.out.println("Enter the PIN: ");
        String pin=sc.nextLine();
        if(user.login(id,pin)){
            System.out.println("\nLogin successful!");
            int choice;
            do{
                System.out.println("\n========== ATM MENU ==========");
                System.out.println("1.Transaction History");
                System.out.println("2.Withdraw");
                System.out.println("3.Deposit");
                System.out.println("4.Transfer");
                System.out.println("5.Balance Check");
                System.out.println("6.Quit");
                System.out.print("Enter your choice: ");
                choice=sc.nextInt();
                sc.nextLine();
               switch(choice){
                case 1:
                    user.getAccount().showTransactionHistory();
                    break;
                case 2:
                    System.out.println("Enter the amount to withdraw: ");
                    double withdrawAmount=sc.nextDouble();
                    user.getAccount().withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.println("Enter the amount to deposit: ");
                    double depositAmount=sc.nextDouble();
                    user.getAccount().deposit(depositAmount);
                    break;
                case 4:
                    System.out.println("Enter the receiver account number: ");
                    String receiver=sc.nextLine();
                    System.out.println("Enter the amount to transfer: ");
                    double transferAmount=sc.nextDouble();
                    user.getAccount().transfer(transferAmount,receiver);
                    break;
                case 5:
                    System.out.println("Current balance is: "+user.getAccount().getBalance());
                    break;
                case 6:
                    System.out.println("Thank you for using the ATM!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
               }
            }while(choice!=6);
        }
        else{
            System.out.println("Invalid user id or PIN!");

        }
    }
}
public class ATMInterface{
    public static void main(String args[])
    {
        User user=new User("Admin","1234",10000);
        ATM atm=new ATM(user);
        atm.start();
    }
}