package clinic.repository;

import clinic.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 17/05/2018.
 */
@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
}
