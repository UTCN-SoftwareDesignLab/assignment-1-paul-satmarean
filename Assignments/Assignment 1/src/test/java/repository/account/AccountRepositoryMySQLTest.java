package repository.account;

import database.DBConnectionFactory;
import model.Account;
import model.Client;
import model.builder.AccountBuilder;
import model.builder.ClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Paul on 06/04/2018.
 */
public class AccountRepositoryMySQLTest {

    //aici trebuie sa punem si un client
    //sau mai multi da mno
    private static Long id;

    private static ClientRepository clientRepository;

    private static AccountRepository accountRepository;


    @BeforeClass
    public static void setupClass(){
        clientRepository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );

        accountRepository = new AccountRepositoryMySQL(
                new DBConnectionFactory().getConnectionWrapper(true).getConnection());
    }

    @Before
    public void cleanUp() throws Exception {
        clientRepository.removeAll();
        accountRepository.removeAll();

        Client client = new ClientBuilder()
                .setName("test")
                .setPnc(20L)
                .setId_card_number(30L)
                .setAddress("Test address.")
                .setEmail("test@test.com")
                .createClient();

        assertTrue(clientRepository.save(client));
        client = clientRepository.findByName(client.getName());
        this.id = client.getId();


    }

    @Test
    public void findAll() throws Exception{
        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 0);
    }

    @Test
    public void findAllWhenNotEmpty() throws  Exception{
        Account account1 = new AccountBuilder()
                .setClientId(this.id)
                .setAmmount(0.00)
                .setCreation_date(new Date())
                .setTypeId(1L)
                .createAccount();
        Account account2 = new AccountBuilder()
                .setClientId(this.id)
                .setAmmount(0.00)
                .setCreation_date(new Date())
                .setTypeId(2L)
                .createAccount();

        accountRepository.save(account1);
        accountRepository.save(account2);

        List<Account> accounts = accountRepository.findAll();
        assertEquals(accounts.size(), 2);
    }

    @Test
    public void save(){
        assertTrue(accountRepository.save(new AccountBuilder()
                .setClientId(this.id)
                .setAmmount(0.00)
                .setCreation_date(new Date())
                .setTypeId(2L)
                .createAccount()));
    }

    @After
    public void removeAll(){
        accountRepository.removeAll();
        clientRepository.removeAll();
    }

}

