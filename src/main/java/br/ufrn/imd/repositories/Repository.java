package br.ufrn.imd.repositories;

import br.ufrn.imd.models.BankAccount;

import java.util.UUID;

public interface Repository {
    BankAccount get(String uuid);
    Throwable create(BankAccount bankAccount);
    Throwable update (BankAccount bankAccount);
}
