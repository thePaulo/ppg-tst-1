package br.ufrn.imd.models;

public class BankAccountTestFixture {
    private BankAccount bankAccount;

    protected BankAccountTestFixture() {
        bankAccount = new BankAccount(123456, 123, 0);
    }

    public BankAccount getBankAccount() {
        return bankAccount;
    }

    public BankAccount getNewBankAccount() {
        return new BankAccount();
    }
}
