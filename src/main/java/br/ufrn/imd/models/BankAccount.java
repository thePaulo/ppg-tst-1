package br.ufrn.imd.models;

import org.jetbrains.annotations.NotNull;

public class BankAccount {
    private long accountNumber;
    private int agency;
    private double balance;

    public BankAccount() {
        this.accountNumber = 0;
        this.agency = 0;
        this.balance = 0;
    }

    public BankAccount(long accountNumber, int agency, double balance) {
        this.accountNumber = accountNumber;
        this.agency = agency;
        this.balance = balance;
    }

    public int getAgency() {
        return agency;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double value) {
        if (value <= 0) throw new IllegalArgumentException();
        this.balance += value;
    }

    public void withdraw(double value){
        //TODO.: se o valor for menor igual a zero, lançar uma exceção
        //TODO.: se a conta não tiver saldo suficiente, lançar uma exceção
        //TODO.: se a conta tiver saldo suficiente, debitar o valor do saldo
    }

    public void transfer(BankAccount beneficiaryAccount, double value){
        //TODO.: se o valor for menor igual a zero, lançar uma exceção
        //TODO.: se a conta não tiver saldo suficiente, lançar uma exceção
        //TODO.: se a conta tiver saldo suficiente, debitar o valor do saldo e realizar deposito
        // na BankAccount beneficiaryAccount
    }
}
