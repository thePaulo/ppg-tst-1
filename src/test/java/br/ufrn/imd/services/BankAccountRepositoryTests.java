package br.ufrn.imd.services;

import br.ufrn.imd.models.BankAccount;
import br.ufrn.imd.repositories.BankAccountRepository;
import br.ufrn.imd.repositories.Repository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

public class BankAccountRepositoryTests {
    private BankService bankService;
    private Repository repository;
    private ComplianceApi complianceApi;


    @BeforeEach
    public void Setup() {
        repository = new BankAccountRepository();
        complianceApi = new ComplianceApiImpl();
        bankService = new BankService(repository, complianceApi);
    }

    @AfterEach
    public void Teardown(){

    }

    @Test
    public void testDepositAllowed(){
        var result = bankService.createNewAccount(123456, 123);
        var insertedAccount = repository.get(result.getBankAccount().get().getId());

        assertNotNull(insertedAccount);
        assertEquals(123456, insertedAccount.getAccountNumber());
    }
}
