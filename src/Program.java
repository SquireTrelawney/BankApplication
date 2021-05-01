import BankLibrary.AccountEventArgs;
import BankLibrary.AccountStateHandler;
import BankLibrary.Bank;
import BankLibrary.AccountType;

import java.util.Scanner;

class Program {
    Scanner sc = new Scanner(System.in);
    Program() {
        Bank bank = new Bank("Зеленый Банк");
        boolean alive = true;
        while (alive) {
            final String ANSI_GREEN = "\u001B[32m"; // выводим список команд зеленым цветом
            System.out.println(ANSI_GREEN + "1. Открыть счет \t 2. Вывести средства  \t 3. Добавить на счет");
            System.out.println(ANSI_GREEN + "4. Закрыть счет \t 5. Пропустить день \t 6. Выйти из программы");
            System.out.println(ANSI_GREEN + "Введите номер пункта:");
            int command = sc.nextInt();

            switch (command) {
                case 1:
                    OpenAccount(bank);
                    break;
                case 2:
                    Withdraw(bank);
                    break;
                case 3:
                    put(bank);
                    break;
                case 4:
                    closeAccount(bank);
                    break;
                case 5:
                    break;
                case 6:
                    alive = false;
                    continue;
            }
            bank.calculatePercentage();

        }
    }

    private void OpenAccount(Bank bank) {
        System.out.println("Укажите сумму для создания счета:");

        double sum = sc.nextDouble();
        System.out.println("Выберите тип счета: 1. До востребования 2. Депозит");
        AccountType accountType;

        int type = sc.nextInt();

        if (type == 2)
            accountType = AccountType.Deposit;
        else
            accountType = AccountType.Ordinary;

        bank.Open(accountType,
                sum,
                addSumHandler,  // обработчик добавления средств на счет
                withdrawSumHandler, // обработчик вывода средств
                (e) -> System.out.println(e.getMessage()), // обработчик начислений процентов в виде лямбда-выражения
                closeAccountHandler, // обработчик закрытия счета
                openAccountHandler); // обработчик открытия счета
    }

    private void Withdraw(Bank bank) {
        System.out.println("Укажите сумму для вывода со счета:");

        double sum = sc.nextDouble();
        System.out.println("Введите id счета:");
        int id = sc.nextInt();

        bank.withdraw(sum, id);
    }

    private void put(Bank bank) {
        System.out.println("Укажите сумму, чтобы положить на счет:");
        double sum = sc.nextDouble();
        System.out.println("Введите Id счета:");
        int id = sc.nextInt();
        bank.put(sum, id);
    }

    private void closeAccount(Bank bank) {
        System.out.println("Введите id счета, который надо закрыть:");
        int id = sc.nextInt();
        bank.close(id);
    }

    // обработчики событий класса Account
    // обработчик открытия счета
    AccountStateHandler openAccountHandler = (AccountEventArgs e) -> System.out.println(e.getMessage());

    // обработчик добавления денег на счет
    AccountStateHandler addSumHandler = (AccountEventArgs e) -> System.out.println(e.getMessage());


    // обработчик вывода средств
    AccountStateHandler withdrawSumHandler = (AccountEventArgs e) -> {
        System.out.println(e.getMessage());
        if (e.getSum() > 0)
            System.out.println("Идем тратить деньги");
    };

    // обработчик закрытия счета
    AccountStateHandler closeAccountHandler = (AccountEventArgs e) -> System.out.println(e.getMessage());;
}