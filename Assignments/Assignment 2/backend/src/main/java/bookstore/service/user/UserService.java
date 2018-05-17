package bookstore.service.user;

import bookstore.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Paul on 23/04/2018.
 */
@Service
public interface UserService {

    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);

    User findByUsername(String username);

    User findOne(Integer id);

    void delete(Integer id);

    User save(User user);

}
