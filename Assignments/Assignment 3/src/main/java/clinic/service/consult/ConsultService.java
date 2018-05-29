package clinic.service.consult;

import clinic.entity.Client;
import clinic.entity.Consult;
import clinic.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */
@Service
public interface ConsultService {

    List<Consult> findAll();

    List<Consult> findByUser(User user);

    List<Consult> findByUserId(Long id);

    List<Consult> findByClientId(Long id);

    Consult findById(Long id);

    List<Consult> findByClient(Client client);

    Consult save(Consult consult);

    void delete(Consult consult);

    void delete(Long id);
}
