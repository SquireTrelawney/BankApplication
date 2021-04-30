package BankLibrary;

public abstract class Account implements IAccount {
    //Событие, возникающее при выводе денег
    protected internal event AccountStateHandler Withdrawed;
    // Событие возникающее при добавление на счет
    protected internal event AccountStateHandler Added;
    // Событие возникающее при открытии счета
    protected internal event AccountStateHandler Opened;
    // Событие возникающее при закрытии счета
    protected internal event AccountStateHandler Closed;
    // Событие возникающее при начислении процентов
    protected internal event AccountStateHandler Calculated;

    static int counter = 0;
    protected int _days = 0; // время с момента открытия счета

    public Account(double sum, int percentage)
    {
        this.sum = sum;
        Percentage = percentage;
        Id = ++counter;
    }

    // Текущая сумма на счету
    public decimal Sum { get; private set; }
    // Процент начислений
    public int Percentage { get; private set; }
    // Уникальный идентификатор счета
    public int Id { get; private set; }
    // вызов событий
    private void CallEvent(IEvent e, AccountS   tateHandler handler)
    {
        if (e != null)
            handler?.Invoke(this, e);
    }
    // вызов отдельных событий. Для каждого события определяется свой витуальный метод
    protected  void OnOpened(IEvent e)
    {
        CallEvent(e, Opened);
    }

    // открытие счета
    protected internal virtual void Open()
    {
        OnOpened(new AccountEventArgs($"Открыт новый счет! Id счета: {Id}", Sum));
    }

}

