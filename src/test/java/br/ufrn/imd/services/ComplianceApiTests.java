package br.ufrn.imd.services;


import br.ufrn.imd.models.BankAccount;
import br.ufrn.imd.repositories.Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

public class ComplianceApiTests {

    private BankService bankService;
    private Repository repository;
    private ComplianceApi complianceApi;


    @BeforeEach
    public void Setup() {
        repository = mock(Repository.class);
        complianceApi = new ComplianceApiImpl();

        bankService = new BankService(repository, complianceApi);
    }


    @Test
    public void testDepositAllowed(){
        var account = new BankAccount("1de7d918-badf-412b-893d-0c0aa1ee16e7", 123456, 123, 0);

        var result = bankService.deposit(account, 100);

        assertEquals(200, result.getStatusCode());
        assertTrue(result.getBankAccount().isPresent());
    }


    @Test
    public void testDepositNotAllowed(){
        var account = new BankAccount("690713d9-0deb-4ab3-a978-a9b15571052d", 123456, 123, 0);

        var result = bankService.deposit(account, 100);
        assertTrue(result.getBankAccount().isEmpty());
        assertEquals(401, result.getStatusCode());
        assertEquals("THIS ACCOUNT CAN'T DEPOSIT: THE COMPLIANCE NOT ALLOWED THIS TRANSACTION!", result.getMessages()[0]);
    }


    @Test
    public void testDepositException(){
        var account = new BankAccount("79d8c19c-316e-496c-b280-9aed815f6cb2", 123456, 123, 0);

        var result = bankService.deposit(account, 100);
        assertTrue(result.getBankAccount().isEmpty());
        assertEquals(500, result.getStatusCode());
        assertEquals("THIS ACCOUNT CAN'T DEPOSIT: CONNECTION FAILED!", result.getMessages()[0]);
    }

    @Test
    public void testWithdrawAllowed(){
        var account = new BankAccount("1de7d918-badf-412b-893d-0c0aa1ee16e7", 123456, 123, 1000);

        var result = bankService.withdraw(account, 100);

        assertEquals(200, result.getStatusCode());
        assertTrue(result.getBankAccount().isPresent());
    }

    @Test
    public void testWithdrawNotAllowed(){
        var account = new BankAccount("690713d9-0deb-4ab3-a978-a9b15571052d", 123456, 123, 0);

        var result = bankService.withdraw(account, 100);
        assertTrue(result.getBankAccount().isEmpty());
        assertEquals(401, result.getStatusCode());
        assertEquals("THIS ACCOUNT CAN'T WITHDRAW: THE COMPLIANCE NOT ALLOWED THIS TRANSACTION!", result.getMessages()[0]);
    }

    @Test
    public void testWithidrawException(){
        var account = new BankAccount("79d8c19c-316e-496c-b280-9aed815f6cb2", 123456, 123, 0);

        var result = bankService.withdraw(account, 100);
        assertTrue(result.getBankAccount().isEmpty());
        assertEquals(500, result.getStatusCode());
        assertEquals("THIS ACCOUNT CAN'T WITHDRAW: CONNECTION FAILED!", result.getMessages()[0]);
    }
}
