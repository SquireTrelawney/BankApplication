package BankLibrary;

public class AccountStateHandler implements IHandler{
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
    @Override
    public void accountEventArgs(String message, double sum) {
        this.message = message;
        this.sum = sum;
    }

}
