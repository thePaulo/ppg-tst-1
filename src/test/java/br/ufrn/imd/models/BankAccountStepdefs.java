package br.ufrn.imd.models;

import br.ufrn.imd.models.BankAccount;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.pt.E;
import io.cucumber.java.pt.Quando;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class BankAccountStepdefs {

    private BankAccount bankAccount;
    private BankAccount bankAccountBeneficiary;


    @Given("um BankAccount com saldo de R$ {double}")
    public void um_bank_account_com_saldo_de_r$(Double double1) {
        //TODO
        bankAccount = new BankAccount(UUID.randomUUID().toString(), 123456, 123, double1);
    }

    @E("um BankAccount de destino com saldo de R$ {double}")
    public void umBankAccountDeDestinoComSaldoDeR$(double arg0) {
        bankAccountBeneficiary = new BankAccount(UUID.randomUUID().toString(), 654321, 321, arg0);
    }

    @When("depositar R$ {double}")
    public void depositar_r$(Double double1) {
        //TODO
        bankAccount.deposit(double1);
    }

    @When("retirar R$ {double}")
    public void retirarR$(double arg0) {
        bankAccount.withdraw(arg0);
    }

    @Quando("tranferir R$ {double} para o BankAccount de destino")
    public void tranferirR$ParaOBankAccountDeDestino(double arg0) {
        bankAccount.transfer(bankAccountBeneficiary, arg0);
    }

    @Then("o saldo do BankAccount deve ser R$ {double}")
    public void o_saldo_deve_ser_r$(Double double1) {
        var saldo = bankAccount.getBalance();
        assertEquals(double1, saldo);
    }

    @E("o saldo do BankAccount de destino deve ser R$ {double}")
    public void oSaldoDoBankAccountDeDestinoDeveSerR$(double arg0) {
        var saldo = bankAccountBeneficiary.getBalance();
        assertEquals(arg0, saldo);
    }
}