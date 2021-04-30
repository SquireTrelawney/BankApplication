import BankLibrary.AccountEventArgs;
import BankLibrary.IHandler;

import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        OpenAccount(new Bank());
    }
    private static void OpenAccount(Bank bank)
    {
        System.out.println("Укажите сумму для создания счета:");
        Scanner sc = new Scanner(System.in);
        double sum = sc.nextDouble();
        System.out.println("Выберите тип счета: 1. До востребования 2. Депозит");

        bank.Open(sum, OpenAccountHandler); // обработчик открытия счета
    }



    static IHandler OpenAccountHandler = (AccountEventArgs e) -> System.out.println(e.getMessage());
}
