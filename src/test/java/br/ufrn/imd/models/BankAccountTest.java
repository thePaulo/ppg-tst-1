package br.ufrn.imd.models;

import jdk.jfr.Description;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountTest {

    private final BankAccountTestFixture fixture = new BankAccountTestFixture();
    private BankAccount bankAccount;

    @BeforeEach
    public void Setup()
    {
        bankAccount = fixture.getNewBankAccount();
    }

    @Test
    @Description("Bank Account should have an account number and agency number")
    public void testBankAccountShouldHaveAccountNumberAndAgency(){
        assertNotNull(bankAccount.getAccountNumber());
        assertNotNull(bankAccount.getAgency());
    }

    @Test
    public void testDepositShouldChangeTheBalance(){
        bankAccount.deposit(100);
        assertEquals(100, bankAccount.getBalance());
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -5, -3, -15})
    public void testDepositShouldNotBeZeroOrNegativeValue(double value){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.deposit(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -5, -3, -15})
    public void testInvalidValueToWithdraw(double value){
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {110, 1000, 534, 341, 94823})
    public void testNotEnoughBalanceToWithdraw(double value){
        bankAccount.deposit(100);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(value));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -10, -5, -3, -15})
    public void testInvalidValueToTransfer(double value){
        BankAccount newBankAccount = fixture.getNewBankAccount();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(newBankAccount,value));
    }

    @ParameterizedTest
    @ValueSource(ints = {110, 1000, 534, 341, 94823})
    public void testNotEnoughBalanceToTransfer(double value){
        BankAccount newBankAccount = fixture.getNewBankAccount();
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(newBankAccount,value));
    }

    @Test
    public void testWithdrawWithBalance(){
        bankAccount.deposit(100);
        bankAccount.withdraw(10);
        assertEquals(90,bankAccount.getBalance());
    }

    @Test
    public void testTransferWithBalance(){
        bankAccount.deposit(100);
        BankAccount newBankAccount = fixture.getNewBankAccount();
        bankAccount.transfer(newBankAccount,50);

        assertEquals(50,bankAccount.getBalance());
        assertEquals(50,newBankAccount.getBalance());
    }
}

