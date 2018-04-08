package controller.user;

import model.Account;
import model.Client;
import model.Type;
import model.validation.Notification;
import service.account.AccountService;
import service.client.ClientService;
import service.type.TypeService;
import view.user.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Paul on 08/04/2018.
 */
public class AccountController {

    private AccountService accountService;
    private ClientService clientService;
    private TypeService typeService;

    private UserView view;

    private Client client;

    public AccountController(AccountService accountService, ClientService clientService, TypeService typeService, UserView view, Client client) {
        this.accountService = accountService;
        this.clientService = clientService;
        this.typeService = typeService;
        this.view = view;
        this.client = client;

        view.setTitle("Clients");
        view.setAddActionListener(new AddActionListener());
        view.setViewDetailsActionListener(new ViewDetailsActionListener());
        view.setEditActionListener(new EditActionListener());
        view.setDeleteActionListener(new DeleteActionListener());
        view();
        view.setVisible(true);



    }

    private void updateTable(){

        String[] colums = new String[]{"Type", "Ammount", "Created", "Client"};

        Notification<Account> notification = accountService.findByClient(this.client);
        if(notification.hasErrors()){
            JOptionPane.showMessageDialog(view.getContentPane(), notification.getFormattedErrors());
            return;
        }
        Account account = notification.getResult();

        Notification<Type> findNotification = typeService.findById(account.getTypeId());
        if(findNotification.hasErrors()){
            JOptionPane.showMessageDialog(view.getContentPane(), findNotification.getFormattedErrors());
            return;
        }

        Type type = findNotification.getResult();

        if(notification.hasErrors()){

            JOptionPane.showMessageDialog(view.getContentPane(), notification.getFormattedErrors());
            return;

        }else{
            Object[][] data = new Object[1][4];
            data[1][0]=type.getName();
            data[1][1]=account.getAmmount();
            data[1][2]=account.getCreation_date();
            data[1][3]=client.getName();

            view.setUserData(colums, data);
        }


    }

    private class ViewDetailsActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            
            // TODO: 08/04/2018
        }
    }
    private class AddActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class EditActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
    private class DeleteActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }




}
