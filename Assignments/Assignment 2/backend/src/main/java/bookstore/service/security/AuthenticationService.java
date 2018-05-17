package bookstore.service.security;

import bookstore.security.Credentials;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 02/05/2018.
 */
@Service
public interface AuthenticationService {

    public String login(Credentials credentials);
}
