package BankLibrary;

public interface IAccount {
    // Положить деньги на счет
    void put(double sum);
    // Взять со счета
    double withdraw(double sum);
}
