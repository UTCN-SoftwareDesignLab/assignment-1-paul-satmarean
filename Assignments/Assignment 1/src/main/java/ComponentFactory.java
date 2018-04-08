import database.DBConnectionFactory;
import repository.Cache;
import repository.account.AccountRepository;
import repository.account.AccountRepositoryMySQL;
import repository.account.bill.BillRepository;
import repository.account.bill.BillRepositoryMySQL;
import repository.account.type.TypeRepository;
import repository.account.type.TypeRepositoryMySQL;
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
import service.account.AccountServiceImpl;
import service.action.ActionService;
import service.action.ActionServiceImpl;
import service.bill.BillService;
import service.bill.BillServiceImpl;
import service.client.ClientService;
import service.client.ClientServiceImpl;
import service.user.AuthenticationService;
import service.user.AuthenticationServiceMySQL;

import java.sql.Connection;

/**
 * Created by Alex on 18/03/2017.
 */
public class ComponentFactory {

    //services
    private final AuthenticationService authenticationService;
    private final AccountService accountService;
    private final ClientService clientService;
    private final ActionService actionService;
    private final BillService billService;

    //repos
    private final UserRepository userRepository;
    private final RightsRolesRepository rightsRolesRepository;
    private final ClientRepository clientRepository;
    private final ActionRepository actionRepository;
    private final AccountRepository accountRepository;
    private final TypeRepository typeRepository;
    private final BillRepository billRepository;


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
        this.typeRepository = new TypeRepositoryMySQL(connection);
        this. billRepository = new BillRepositoryMySQL(connection);

        //service
        this.authenticationService = new AuthenticationServiceMySQL(this.userRepository, this.rightsRolesRepository);
        this.actionService = new ActionServiceImpl(actionRepository, authenticationService);
        this.accountService = new AccountServiceImpl(accountRepository, authenticationService, actionService, typeRepository, billRepository);
        this.clientService = new ClientServiceImpl(authenticationService, actionService, clientRepository);
        this.billService = new BillServiceImpl(billRepository);

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
