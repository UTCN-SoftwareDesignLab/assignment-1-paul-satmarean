package repository.client;

import database.DBConnectionFactory;
import model.Client;
import model.builder.ClientBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

/**
 * Created by Paul on 06/04/2018.
 */
public class ClientRepositoryMySQLTest {

    private static ClientRepository clientRepository;


    @BeforeClass
    public static void setupClass(){
        clientRepository = new ClientRepositoryCacheDecorator(
                new ClientRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                ),
                new Cache<>()
        );
    }

    @Before
    public void cleanUp() {
        clientRepository.removeAll();
    }

    @Test
    public void findAll() throws Exception {
        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 0);
    }



    @Test
    public void findAllWhenDbNotEmpty() throws Exception {
        Client client = new ClientBuilder()
                .setName("test")
                .setPnc(20L)
                .setId_card_number(30L)
                .setAddress("Test address.")
                .setEmail("test@test.com")
                .createClient();
        clientRepository.save(client);
        clientRepository.save(client);
        clientRepository.save(client);

        List<Client> clients = clientRepository.findAll();
        assertEquals(clients.size(), 3);
    }

    @Test
    public void findById() throws Exception {

    }

    @Test
    public void save() throws Exception {
        assertTrue(clientRepository.save(
                 new ClientBuilder()
                .setName("test")
                .setPnc(20L)
                .setId_card_number(30L)
                .setAddress("Test address.")
                .setEmail("test@test.com")
                .createClient())
        );
    }

    @After
    public void removeAll() throws Exception {
        clientRepository.removeAll();
    }

}
