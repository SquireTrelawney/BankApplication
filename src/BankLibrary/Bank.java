package BankLibrary;

import java.util.ArrayList;

// тип счета


public class Bank {

    ArrayList<Account> accounts;

    public String name;

    public Bank(String name) {
        this.name = name;
        accounts = new ArrayList<Account>();

    }

    // метод создания счета
    public void Open(AccountType accountType, double sum,
                     AccountStateHandler addSumHandler, AccountStateHandler withdrawSumHandler,
                     AccountStateHandler calculationHandler, AccountStateHandler closeAccountHandler,
                     AccountStateHandler openAccountHandler) {
        Account newAccount = null;

        switch (accountType) {
            case Ordinary:
                newAccount = new DemandAccount(sum, 1);
                break;
            case Deposit:
                newAccount = new DepositAccount(sum, 40);
                break;
        }

        if (newAccount == null){
            final String ANSI_RED = "\u001B[31m";
            System.out.println(ANSI_RED + "Ошибка создания счета");
        }
        else {
            // установка обработчиков событий счета
            newAccount.added = addSumHandler;
            newAccount.withdrawed = withdrawSumHandler;
            newAccount.closed = closeAccountHandler;
            newAccount.opened = openAccountHandler;
            newAccount.calculated = calculationHandler;
            // добавляем новый счет в список счетов;
            accounts.add(newAccount);
            newAccount.open();
        }
    }

    //добавление средств на счет
    public void put(double sum, int id) {
        Account account = findAccount(id);
        if (account == null)  {
            final String ANSI_RED = "\u001B[31m";
            System.out.println(ANSI_RED + "Счет не найден");
        }
        else  account.put(sum);
    }

    // вывод средств
    public void withdraw(double sum, int id) {
        Account account = findAccount(id);
        if (account == null)  {
            final String ANSI_RED = "\u001B[31m";
            System.out.println(ANSI_RED + "Счет не найден");
        }
        else account.withdraw(sum);
    }

    // закрытие счета
    public void close(int id) {
        Account account = findAccount(id);
        if (account == null) {
            final String ANSI_RED = "\u001B[31m";
            System.out.println(ANSI_RED + "Счет не найден");
        }else {
            accounts.remove(account);
            account.close();
        }
    }

    // начисление процентов по счетам
    public void calculatePercentage() {
        if (accounts == null) // если массив не создан, выходим из метода
            return;
        for (int i = 0; i < accounts.size(); i++) {
            accounts.get(i).incrementDays();
            accounts.get(i).calculate();
        }
    }

    // поиск счета по id
    public Account findAccount(int id) {
        for (int i = 0; i < accounts.size(); i++) {
            if (accounts.get(i).id == id)
                return accounts.get(i);
        }
        return null;
    }
}
