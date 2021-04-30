import BankLibrary.Account;
import BankLibrary.AccountEventArgs;
import BankLibrary.IHandler;
import BankLibrary.DepositAccount;
public class Bank {
    public void Open(double sum, IHandler openAccountHandler)
    {
        Account newAccount = new DepositAccount(sum, 40);
        newAccount.Opened = openAccountHandler;

        newAccount.Open();
    }
}
