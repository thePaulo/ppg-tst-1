package br.ufrn.imd.models;

import java.util.UUID;

public class BankAccount {

    private final String id;
    private int accountNumber;
    private int agency;
    private double balance;

    public BankAccount() {
        this.id = UUID.randomUUID().toString();
    }

    public BankAccount(String uuid, int accountNumber, int agency, double balance) {
        this.id = uuid;
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.balance = balance;
    }

    public String getId() {
        return id;
    }

    public int getAgency() {
        return agency;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setAgency(int agency) {
        this.agency = agency;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void deposit(double value) {
        if (value <= 0) throw new IllegalArgumentException();
        this.balance += value;
    }

    public void withdraw(double value){
        if (value <= 0) throw new IllegalArgumentException();
        if(this.balance < value) throw new IllegalArgumentException();
        this.balance -= value;
    }

    public void transfer(BankAccount beneficiaryAccount, double value){
        this.withdraw(value);
        beneficiaryAccount.deposit(value);
    }
}