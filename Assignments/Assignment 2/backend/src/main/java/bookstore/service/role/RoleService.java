package bookstore.service.role;

import bookstore.entity.Role;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 14/05/2018.
 */
@Service
public interface RoleService {

    List<Role> getRoles();

}
