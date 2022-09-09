package br.ufrn.imd.services;

import br.ufrn.imd.models.BankAccount;
import br.ufrn.imd.models.BankAccountResult;
import br.ufrn.imd.repositories.Repository;

import java.util.Optional;
import java.util.UUID;

public class BankService {

    private final Repository repository;
    private final ComplianceApi complianceApi;

    public BankService(Repository repository, ComplianceApi complianceApi){
        this.repository = repository;
        this.complianceApi = complianceApi;
    }

    public BankAccountResult createNewAccount(int accountNumber, int agency){
        var bankAccount = new BankAccount(UUID.randomUUID().toString(), accountNumber, agency, 0);
        this.repository.create(bankAccount);
        return BankAccountResult.success(bankAccount);
    }

    public BankAccountResult deposit(BankAccount account, double value){
        try {
            if (!complianceApi.CanItReceiveNewDeposit(account, value))
                return BankAccountResult.fail(401, new String[]{"THIS ACCOUNT CAN'T DEPOSIT: THE COMPLIANCE NOT ALLOWED THIS TRANSACTION!"});

            account.deposit(value);
            this.repository.update(account);
            return BankAccountResult.success(account);
        }catch (Exception e){
            return BankAccountResult.fail(500, new String[]
                    {
                            "THIS ACCOUNT CAN'T DEPOSIT: CONNECTION FAILED!",
                            e.getMessage()
                    });
        }
    }

    public BankAccountResult withdraw(BankAccount account, double value){
        try{
            if (!complianceApi.CanItReceiveNewWithdraw(account, value))
                return BankAccountResult.fail(401, new String[]{"THIS ACCOUNT CAN'T WiTHDRAW: THE COMPLIANCE NOT ALLOWED THIS TRANSACTION!"});

            account.withdraw(value);
            this.repository.update(account);
            return BankAccountResult.success(account);
        }catch (Exception e){
            return BankAccountResult.fail(500, new String[]{"THIS ACCOUNT CAN'T WHITDRAW: CONNECTION FAILED!", e.getMessage()});
        }
    }
}
