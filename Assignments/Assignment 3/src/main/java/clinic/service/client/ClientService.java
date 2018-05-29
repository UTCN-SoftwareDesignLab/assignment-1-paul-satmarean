package clinic.service.client;

import clinic.entity.Client;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */
@Service
public interface ClientService {

    List<Client> findAll();

    Client findByName(String name);

    Client findById(Long id);

    Client save(Client client);

    void delete(Client client);

    void delete(Long id);
}
