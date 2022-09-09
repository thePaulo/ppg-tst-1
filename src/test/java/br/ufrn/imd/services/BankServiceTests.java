package br.ufrn.imd.services;

import br.ufrn.imd.models.BankAccount;
import br.ufrn.imd.models.BankAccountTestFixture;
import br.ufrn.imd.repositories.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.internal.matchers.Null;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BankServiceTests {

    private final BankAccountTestFixture fixture = new BankAccountTestFixture();
    private BankService bankService;
    private Repository repository;
    private ComplianceApi complianceApi;


    @BeforeEach
    public void Setup() {
        repository = mock(Repository.class);
        complianceApi = mock(ComplianceApi.class);

        when(repository.get(any(String.class))).thenReturn(fixture.getNewBankAccount());
        when(complianceApi.CanItReceiveNewDeposit(any(BankAccount.class), any(double.class))).thenReturn(true);

        bankService = new BankService(repository, complianceApi);
    }

    @Test
    public void testCreateNewAccount() {
        bankService.createNewAccount(123456, 1234);
        verify(repository).create(any(BankAccount.class));
    }

    @Test
    public void testDeposit(){
        var account = repository.get(UUID.randomUUID().toString());
        var result = bankService.deposit(account, 1000);

        // Assets
        assertTrue(result.getBankAccount().isPresent());
        assertEquals(1000, result.getBankAccount().get().getBalance());

        // Verify
        verify(repository).update(any(BankAccount.class));
    }

    @Test
    public void testWithdraw(){
        var account = repository.get(UUID.randomUUID().toString());
        var result = bankService.deposit(account, 1000);

        account.withdraw(100);
        assertTrue(result.getBankAccount().isPresent());
        assertEquals(900, result.getBankAccount().get().getBalance());

        verify(repository).update(any(BankAccount.class));
    }

    @Test
    public void testDepositException(){
        var account = repository.get(UUID.randomUUID().toString());
        when(repository.update(account)).thenThrow();
        var result = bankService.deposit(account, 1000);

        assertTrue(result.getBankAccount().isEmpty());
        verify(repository).update(any(BankAccount.class));
    }
}
