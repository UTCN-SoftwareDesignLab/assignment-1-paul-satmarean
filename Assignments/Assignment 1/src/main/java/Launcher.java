import controller.LoginController;
import controller.user.AccountController;
import controller.user.AddController;
import controller.user.EditController;
import controller.user.UserController;
import view.LoginView;
import view.user.*;
import view.admin.*;

/**
 * Created by Alex on 18/03/2017.
 */
public class Launcher {

    public static void main(String[] args) {
        ComponentFactory componentFactory = ComponentFactory.instance(false);
        new LoginController(
                new LoginView(),
                componentFactory.getAuthenticationService(),

                new UserController(
                        componentFactory.getAuthenticationService(),
                        componentFactory.getClientService(),
                        componentFactory.getBillService(),
                        componentFactory.getAccountService(),
                        new EditController(
                                componentFactory.getClientService(),
                                new EditClientView("Edit"),
                                null
                        ),
                        new AddController(
                            componentFactory.getClientService(),
                            new EditClientView("Add")
                        ),
                        new AccountController(
                                componentFactory.getAccountService(),
                                componentFactory.getClientService(),
                                componentFactory.getTypeService(),
                                new UserView("User"),
                                null
                        ),
                        new UserView("User")
                )
        );
    }

}
