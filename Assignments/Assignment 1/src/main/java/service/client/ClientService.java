package service.client;

import model.Client;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Paul on 25/03/2018.
 */
public interface ClientService {

    Notification<Client> findByName(String name);

    Notification<List<Client>> findAll();

    Notification<Boolean> save(Client client);

    Notification<Boolean> update(Client client);

    Notification<Boolean> delete(Client client);

    Notification<Client> findByPnc(Long pnc);
}
