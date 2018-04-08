package service.client;

import model.Client;
import model.validation.Notification;

/**
 * Created by Paul on 25/03/2018.
 */
public interface ClientService {

    Notification<Client> findByName(String name);

    Notification<Client> findByPnc(Long pnc);
}
