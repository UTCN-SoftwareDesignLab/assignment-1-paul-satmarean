package clinic.repository;

import clinic.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 17/05/2018.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
