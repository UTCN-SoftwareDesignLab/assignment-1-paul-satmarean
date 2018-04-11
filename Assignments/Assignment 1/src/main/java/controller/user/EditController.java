package controller.user;

import model.Client;
import model.builder.ClientBuilder;
import model.validation.ClientValidator;
import model.validation.Notification;
import repository.client.ClientRepository;
import service.client.ClientService;
import view.user.EditClientView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Paul on 08/04/2018.
 */
public class EditController {


    private ClientService clientService;
    private EditClientView editClientView;
    private Client client;

    public EditController(ClientService clientService, EditClientView editClientView, Client client) {
        this.clientService = clientService;
        this.editClientView = editClientView;
        this.client = client;
        editClientView.setSubmitActionListener(new SubmitActionListener());
        editClientView.setTitle("Edit client");
        editClientView.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        editClientView.setVisible(false);
    }

    public void populateView(){
        if(client!=null){
            editClientView.setName(client.getName());
            editClientView.setAddress(client.getAddress());
            editClientView.setCardNumber(client.getId_card_number());
            editClientView.setPnc(client.getPnc());
            editClientView.setEmail(client.getEmail());
        }
    }


    public void setFormVisible(){
        editClientView.setVisible(true);
    }

    public void setClient(Client client) {
        this.client = client;
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

    private class SubmitActionListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            Client newClient= getClientFromInput();
            newClient.setId(client.getId()); //we put the old id back
            if(new ClientValidator(newClient).validate()) {
                Notification<Boolean> notification = clientService.update(newClient);
                if(notification.getResult()==false){
                    JOptionPane.showMessageDialog(editClientView.getContentPane(), "Could not edit client.");
                }
            }
        }
    }




}
