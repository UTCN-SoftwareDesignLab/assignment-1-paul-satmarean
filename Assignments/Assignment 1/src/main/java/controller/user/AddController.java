package controller.user;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import service.client.ClientService;
import view.user.EditClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Paul on 08/04/2018.
 */
public class AddController {


    private ClientService clientService;
    private EditClientView editClientView;

    public AddController(ClientService clientService, EditClientView editClientView) {
        this.clientService = clientService;
        this.editClientView = editClientView;
        editClientView.setSubmitActionListener(new SubmitActionListener());
        editClientView.setTitle("Edit client");
        editClientView.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        editClientView.setVisible(true);
    }

    private Client getClientFromInput(){
        return new ClientBuilder()
                .setAddress(editClientView.getAddress())
                .setName(editClientView.getName())
                .setEmail(editClientView.getEmail())
                .setId_card_number(editClientView.getCardNumber())
                .setPnc(editClientView.getPnc())
                .createClient();
    }

    private class SubmitActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Client newClient= getClientFromInput();
            if(new ClientValidator(newClient).validate()) {
                Notification<Boolean> notification = clientService.save(newClient);
                if(notification.getResult()==false){
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "Could not add client.");
                }
            }
        }
    }



}
