package clinic.service.security;

import clinic.security.Credentials;
import org.springframework.stereotype.Service;

/**
 * Created by Paul on 17/05/2018.
 */
@Service
public interface AuthenticationService {

    public String login(Credentials credentials);

}
