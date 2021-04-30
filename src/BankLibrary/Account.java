package BankLibrary;

public abstract class Account implements IAccount {

    public  IHandler Opened;
//
//
    static int counter = 0;
//    protected int _days = 0; // время с момента открытия счета
//
    public Account(double sum, int percentage)
    {
        this.sum = sum;
//        Percentage = percentage;
        Id = ++counter;
    }
//
//    // Текущая сумма на счету
    public double sum;
//    // Процент начислений
//    public int Percentage { get; private set; }
//    // Уникальный идентификатор счета
    public int Id;
//    // вызов событий
//
    private void CallEvent(AccountEventArgs e,IHandler handler)
    {
        if (e != null)
            handler.invoke(e);
    }
    // вызов отдельных событий. Для каждого события определяется свой витуальный метод
    protected  void OnOpened(AccountEventArgs e)
    {
        CallEvent(e, Opened);
    }

    // открытие счета
    public void Open()
    {
        OnOpened(new AccountEventArgs("Открыт новый счет! Id счета: {Id}", sum));
    }

}

