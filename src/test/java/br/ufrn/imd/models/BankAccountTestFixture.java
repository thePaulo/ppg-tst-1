package br.ufrn.imd.models;

import java.util.UUID;

public class BankAccountTestFixture {
    private BankAccount bankAccount;

    public BankAccountTestFixture() {
        bankAccount = new BankAccount(UUID.randomUUID().toString(), 123456, 123, 0);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public BankAccount getNewBankAccount() {
        return new BankAccount();
    }
}
