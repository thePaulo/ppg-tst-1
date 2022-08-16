package br.ufrn.imd.models;

import br.ufrn.imd.models.BankAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankAccountStepdefs {

    private BankAccount bankAccount;

    @Given("um BankAccount com saldo de R$ {double}")
    public void um_bank_account_com_saldo_de_r$(Double double1) {
        //TODO
        bankAccount = new BankAccount(123456, 123, double1);
    }

    @When("depositar R$ {double}")
    public void depositar_r$(Double double1) {
        //TODO
        bankAccount.deposit(double1);
    }

    @Then("o saldo do BankAccount deve ser R$ {double}")
    public void o_saldo_deve_ser_r$(Double double1) {
        //TODO
        var saldo = bankAccount.getBalance();
        assertEquals(double1, saldo);
    }

}
