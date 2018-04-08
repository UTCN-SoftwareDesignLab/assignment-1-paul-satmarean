import database.DBConnectionFactory;
import repository.Cache;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.action.ActionRepository;
import repository.action.ActionRepositoryCacheDecorator;
import repository.action.ActionRepositoryMySQL;
import repository.client.ClientRepository;
import repository.client.ClientRepositoryCacheDecorator;
import repository.client.ClientRepositoryMySQL;
import repository.security.RightsRolesRepository;
import repository.security.RightsRolesRepositoryMySQL;
import repository.user.UserRepository;
import repository.user.UserRepositoryMySQL;
import service.account.AccountService;
import service.client.ClientService;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    //services
    private final AuthenticationService authenticationService;
    //private final AccountService accountService;
    //private final ClientService clientService;

    //repos
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final ActionRepository actionRepository;
    private final AccountRepository accountRepository;


    private static ComponentFactory instance;

    public static ComponentFactory instance(Boolean componentsForTests) {
        if (instance == null) {
            instance = new ComponentFactory(componentsForTests);
        }
        return instance;
    }

    private ComponentFactory(Boolean componentsForTests) {
        Connection connection = new DBConnectionFactory().getConnectionWrapper(componentsForTests).getConnection();

        //repo
        this.rightsRolesRepository = new RightsRolesRepositoryMySQL(connection);
        this.userRepository = new UserRepositoryMySQL(connection, rightsRolesRepository);

        this.clientRepository = new ClientRepositoryCacheDecorator(new ClientRepositoryMySQL(connection), new Cache());
        this.actionRepository = new ActionRepositoryCacheDecorator(new ActionRepositoryMySQL(connection), new Cache());
        this.accountRepository = new AccountRepositoryMySQL(connection);

        //service
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);

    }

    public AuthenticationService getAuthenticationService() {
        return authenticationService;
    }

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public RightsRolesRepository getRightsRolesRepository() {
        return rightsRolesRepository;
    }
}
