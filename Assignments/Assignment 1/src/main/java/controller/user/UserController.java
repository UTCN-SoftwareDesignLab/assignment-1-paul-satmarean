package controller.user;

import model.Client;
import model.validation.Notification;
import service.account.AccountService;
import service.bill.BillService;
import service.client.ClientService;
import service.user.AuthenticationService;
import view.user.UserView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public class UserController {

    private AuthenticationService authenticationService;
    private ClientService clientService;
    private BillService billService;
    private AccountService accountService;


    private UserView userView;

    public UserController(AuthenticationService authenticationService, ClientService clientService, BillService billService, AccountService accountService, UserView userView) {
        this.authenticationService = authenticationService;
        this.clientService = clientService;
        this.billService = billService;
        this.accountService = accountService;
        this.userView = userView;

        userView.setTitle("Clients");
        userView.setAddActionListener(new AddActionListener());
        userView.setViewDetailsActionListener(new ViewDetailsActionListener());
        userView.setEditActionListener(new EditActionListener());
        userView.setDeleteActionListener(new DeleteActionListener());
        updateTable();
        userView.setVisible(true);
    }

    private void updateTable(){

        String[] colums = new String[]{"Name", "Card Number", "pnc", "Address", "email"};
        Notification<List<Client>> notification = clientService.findAll();
        List<Client> clients = notification.getResult();
        if(notification.hasErrors()){

            JOptionPane.showMessageDialog(userView.getContentPane(), notification.getFormattedErrors());
            return;

        }else{
            Object[][] data = new Object[clients.size()][5];
            int index =0;
            for(Client client:clients){
                data[index][0] = client.getName();
                data[index][1] = client.getId_card_number();
                data[index][2] = client.getPnc();
                data[index][3] = client.getAddress();
                data[index][4] = client.getEmail();
                index++;
            }

            userView.setUserData(colums, data);
        }


    }

    private class ViewDetailsActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = userView.getSelectedClientName();
            Notification<Client> getClientNotification = clientService.findByName(name);
            if(getClientNotification.hasErrors()){
                JOptionPane.showMessageDialog(userView.getContentPane(), getClientNotification.getFormattedErrors());
                return;
            }
            Client client = getClientNotification.getResult();
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
