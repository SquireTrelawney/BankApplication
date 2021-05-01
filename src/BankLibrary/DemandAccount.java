package BankLibrary;

public class DemandAccount extends Account{

    public DemandAccount(double sum, int percentage){
        super(sum, percentage);
    }
    @Override
    protected void open() {
        onOpened(new AccountEventArgs("Открыт новый счет до востребования! Id счета: " + id, sum));
    }
}
