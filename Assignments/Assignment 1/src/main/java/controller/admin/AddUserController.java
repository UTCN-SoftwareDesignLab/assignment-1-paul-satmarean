package controller.admin;

import service.user.UserService;
import view.admin.EditUserView;

import javax.swing.*;

/**
 * Created by Paul on 16/04/2018.
 */
public class UserEditController {

    private UserService userService;

    private EditUserView editUserView;

    public UserEditController(UserService userService, EditUserView editUserView) {
        this.userService = userService;
        this.editUserView = editUserView;

        editUserView.setDefaultCloseOperation(JFrame
        .HIDE_ON_CLOSE);
    }

}
