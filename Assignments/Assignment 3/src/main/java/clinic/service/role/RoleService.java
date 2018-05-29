package clinic.service.role;

import clinic.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */
@Service
public interface RoleService {

    List<Role> findAll();

    Role findById(Long id);

    Role findByName(String name);
}
