package BankLibrary;

public class DepositAccount extends Account{
public DepositAccount(double sum, int percentage)
        {
            super(sum, percentage);
        }
    public  void Open()
        {
            OnOpened(new AccountEventArgs("Открыт новый депозитный счет! Id счета: {this.Id}", this.sum));
        }

//protected internal override void Calculate()
//        {
//        if (_days % 30 == 0)
//        base.Calculate();
//        }

    @Override
    public void put(double sum) {

    }

    @Override
    public double withdraw(double sum) {
        return 0;
    }
}
