package br.ufrn.imd.models;

import java.util.Optional;

public class BankAccountResult {

    private Optional<BankAccount> bankAccount;
    private int statusCode;
    private String[] messages;


    private BankAccountResult (Optional<BankAccount> bankAccount, int statusCode, String[] messages){
        this.bankAccount = bankAccount;
        this.statusCode = statusCode;

        this.messages = messages;
    }

    public static BankAccountResult success(BankAccount bankAccount){
        return new BankAccountResult(Optional.of(bankAccount), 200, new String[]{"SUCCESS"});
    }

    public static BankAccountResult fail(int statusCode, String[] messages){
        return new BankAccountResult(Optional.empty(), statusCode, messages);
    }

    public int getStatusCode() {
        return statusCode;
    }

    public Optional<BankAccount> getBankAccount() {
        return bankAccount;
    }

    public String[] getMessages() {
        return messages;
    }
}
