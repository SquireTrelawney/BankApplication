package BankLibrary;

public class DepositAccount extends Account {
    public DepositAccount(double sum, int percentage)
    {
        super(sum, percentage);
    }
    @Override
    protected void open()
    {
        onOpened(new AccountEventArgs("Открыт новый депозитный счет! Id счета: " + id, sum));
    }
    @Override
    public void put(double sum)
    {
        if (_days > 30)
            super.put(sum);
        else
            onAdded(new AccountEventArgs("На счет можно положить только после 30-ти дневного периода", 0));
    }
    @Override
    public double withdraw(double sum)
    {
        if (_days > 30)
            return super.withdraw(sum);
        else
            super.onWithdrawed(new AccountEventArgs("Вывести средства можно только после 30-ти дневного периода", 0));
        return 0;
    }
    @Override
    protected void calculate()
    {
        if (_days % 30 == 0)
            super.calculate();
    }
}
