package clinic.repository;

import clinic.entity.Client;
import clinic.entity.Consult;
import clinic.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */
@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {

    List<Consult> findByUser(User user);

    List<Consult> findByClient(Client client);

}
