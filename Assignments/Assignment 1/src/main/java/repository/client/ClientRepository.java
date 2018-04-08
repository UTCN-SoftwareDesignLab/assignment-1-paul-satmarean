package repository.client;

import model.Client;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public interface ClientRepository {

    List<Client> findAll();

    Client findById(Long id) throws EntityNotFoundException;

    Client findByName(String name) throws EntityNotFoundException;

    boolean save(Client client);

    boolean update(Client client);

    void removeAll();

}
