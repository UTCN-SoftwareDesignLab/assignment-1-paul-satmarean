package bookstore.repository;

import bookstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 23/04/2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByName(String name);

}
