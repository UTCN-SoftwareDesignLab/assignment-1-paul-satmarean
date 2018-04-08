package view.user;

import model.Client;
import service.account.AccountService;
import service.client.ClientService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Paul on 08/04/2018.
 */
public class EditClientView extends JFrame{

    private JLabel nameLabel;
    private JLabel cardLabel;
    private JLabel pncLabel;
    private JLabel addressLabel;
    private JLabel emailLabel;

    private JTextField name;
    private JTextField card_number;
    private JTextField pnc;
    private JTextField address;
    private JTextField email;

    private JButton submit;

    public EditClientView(String title) throws HeadlessException {
        super(title);
    }

    private void initialize(){
        nameLabel = new JLabel("Name:");
        cardLabel = new JLabel("Card number:");
        pncLabel =new JLabel("Pnc:");
        addressLabel = new JLabel("Address: ");
        emailLabel = new JLabel("Email: ");

        name = new JTextField(30);
        card_number = new JTextField(30);
        pnc = new JTextField(30);
        address = new JTextField(30);
        email = new JTextField(30);

        submit = new JButton("Submit");
    }

    public void setSubmitActionListener(ActionListener actionListener){
        this.submit.addActionListener(actionListener);
    }


    public String getName() {
        return this.name.getText();
    }

    public void setName(String name){
        this.name.setText(name);
    }

    public void setPnc(Long pnc){
        this.pnc.setText(Long.toString(pnc));
    }

    public Long getPnc(){
        return Long.parseLong(this.pnc.getText());
    }

    public void setCardNumber(Long number){
        this.card_number.setText(Long.toString(number));
    }

    public Long getCardNumber(){
        return Long.parseLong(this.card_number.getText());
    }

    public void setAddress(String address){
        this.address.setText(address);
    }

    public String getAddress(){
        return this.address.getText();
    }

    public void setEmail(String email){
        this.email.setText(email);
    }

    public String getEmail(){
        return this.email.getText();
    }


}
