package BankLibrary;

public class Account implements IAccount {

    //Событие, возникающее при выводе денег
    protected   AccountStateHandler withdrawed;
    // Событие возникающее при добавление на счет
    protected   AccountStateHandler added;
    // Событие возникающее при открытии счета
    protected   AccountStateHandler opened;
    // Событие возникающее при закрытии счета
    protected   AccountStateHandler closed;
    // Событие возникающее при начислении процентов
    protected   AccountStateHandler calculated;

    static int counter = 0;
    protected int _days = 0; // время с момента открытия счета

    public Account(double sum, int percentage)
    {
        this.sum = sum;
        this.percentage = percentage;
        id = ++counter;
    }

    // Текущая сумма на счету
    public double sum ;
    // Процент начислений
    public int percentage ;
    // Уникальный идентификатор счета
    public int id;
    // вызов событий
    private void callEvent(AccountEventArgs e, AccountStateHandler handler)
    {
        if (e != null)
            handler.invoke(e);
    }
    // вызов отдельных событий. Для каждого события определяется свой витуальный метод
    protected  void onOpened(AccountEventArgs e)
    {
        callEvent(e, opened);
    }
    protected  void onWithdrawed(AccountEventArgs e)
    {
        callEvent(e, withdrawed);
    }
    protected  void onAdded(AccountEventArgs e)
    {
        callEvent(e, added);
    }
    protected  void onClosed(AccountEventArgs e)
    {
        callEvent(e, closed);
    }
    protected  void onCalculated(AccountEventArgs e)
    {
        callEvent(e, calculated);
    }
    @Override
    public  void put(double sum)
    {
        this.sum += sum;
        onAdded(new AccountEventArgs("На счет поступило " + sum, this.sum));
    }
    // метод снятия со счета, возвращает сколько снято со счета
    @Override
    public double withdraw(double sum)
    {
        double result = 0;
        if (this.sum >= sum)
        {
            this.sum -= sum;
            result = this.sum;
            onWithdrawed(new AccountEventArgs("Сумма " + sum + " снята со счета " + id, this.sum));
        }
        else
        {
            onWithdrawed(new AccountEventArgs("Недостаточно денег на счете " + id, 0));
        }
        return result;
    }
    // открытие счета
    protected void open()
    {
        onOpened(new AccountEventArgs("Открыт новый счет! Id счета: " + id, sum));
    }
    // закрытие счета
    protected void close()
    {
        onClosed(new AccountEventArgs("Счет " + id + " закрыт.  Итоговая сумма: " + sum, sum));
    }

    protected  void incrementDays()
    {
        _days++;
    }
    // начисление процентов
    protected void calculate()
    {
        double increment = sum * percentage / 100;
        sum = sum + increment;
        onCalculated(new AccountEventArgs("Начислены проценты в размере: " + increment, increment));
    }
}

