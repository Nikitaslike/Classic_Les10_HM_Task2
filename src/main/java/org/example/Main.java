package org.example;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Paying paying1 = new Paying(123456, 1000, 1);
        Paying paying2 = new Paying(654321, 500, 2);
        User user1 = new User("Петро", "вулиця Айзберг");
        User user2 = new User("Тарас", "проспект Тараса Шевченка");
        user1.addPaying(paying1);
        user1.addPaying(paying2);
        System.out.println("Інформація про платежі:");
        paying1.printInfo();
        paying2.printInfo();
        System.out.println("Інформація про клієнтів:");
        user1.printInfo();
        user2.printInfo();
        user1.withdrawMoney(paying1, 100);
        user1.depositMoney(paying2, 200);
        System.out.println("Результат платіжів:");
        paying1.printInfo();
        paying2.printInfo();
        System.out.println("Результат клієнтів:");
        user1.printInfo();
        user2.printInfo();
    }
}

class Paying {
    private double payingID;
    private double balance;
    private int option;
    private ArrayList<String> operations;
    public Paying(double payingID, double balance, int option) {
        this.payingID = payingID;
        this.balance = balance;
        this.option = option;
        this.operations = new ArrayList<String>();
    }
    public double getPayingID() {
        return payingID;
    }
    public void setPayingID(double payingID) {
        if (payingID > 0) {
            this.payingID = payingID;
        }
    }
    public double getBalance() {
        return balance;
    }
    public void setBalance(double balance) {
        this.balance = balance;
    }
    public int getOption() {
        return option;
    }
    public void setOption(int option) {
        if (option <= 3 && option >= 0) {
            this.option = option;
        }
    }
    public void printInfo() {
        System.out.println("Номер рахунку: " + payingID);
        System.out.println("Баланс: " + balance);
        System.out.println("Опція: " + option);
    }
    public void addOperation(String operation) {
        operations.add(operation);
    }
    public void printOperations() {
        System.out.println("Список операцій:");
        for (String operation : operations) {
            System.out.println(operation);
        }
    }
}

class User {
    private String name;
    private String address;
    private ArrayList<Paying> listOfPayings;
    public User(String name, String address) {
        this.name = name;
        this.address = address;
        this.listOfPayings = new ArrayList<Paying>();
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public ArrayList<Paying> getListOfPayings() {
        return listOfPayings;
    }
    public void setListOfPayings(ArrayList<Paying> listOfPayings) {
        this.listOfPayings = listOfPayings;
    }
    public void addPaying(Paying paying) {
        listOfPayings.add(paying);
    }
    public void printInfo() {
        System.out.println("Ім'я: " + name);
        System.out.println("Адреса: " + address);
    }
    public void withdrawMoney(Paying paying, double amount) {
        if (amount > 0 && amount <= paying.getBalance()) {
            paying.setBalance(paying.getBalance() - amount);
            paying.addOperation("Знято " + amount + " грн з рахунку " + paying.getPayingID());
            System.out.println("Операція успішна. Знято " + amount + " грн з рахунку " + paying.getPayingID());
        } else {
            System.out.println("Операція неможлива. Недостатньо коштів на рахунку " + paying.getPayingID());
        }
    }
    public void depositMoney(Paying paying, double amount) {
        if (amount > 0) {
            paying.setBalance(paying.getBalance() + amount);
            paying.addOperation("Поповнено " + amount + " грн на рахунок " + paying.getPayingID());
            System.out.println("Операція успішна. Поповнено " + amount + " грн на рахунок " + paying.getPayingID());
        } else {
            System.out.println("Операція неможлива. Сума поповнення має бути додатною.");
        }
    }
}