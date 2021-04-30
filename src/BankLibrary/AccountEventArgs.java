package BankLibrary;

public class AccountEventArgs
{
    // Сообщение
    private String message;
    // Сумма, на которую изменился счет
    private double sum;

    public String getMessage() {
        return message;
    }


    public double getSum() {
        return sum;
    }


    public AccountEventArgs(String message, double sum) {
        this.message = message;
        this.sum = sum;
    }
}
