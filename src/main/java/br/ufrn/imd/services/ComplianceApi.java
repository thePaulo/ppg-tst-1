package br.ufrn.imd.services;

import br.ufrn.imd.models.BankAccount;

public interface ComplianceApi {
    boolean CanItReceiveNewDeposit(BankAccount account, double value);
    boolean CanItReceiveNewWithdraw(BankAccount account, double value);
}
