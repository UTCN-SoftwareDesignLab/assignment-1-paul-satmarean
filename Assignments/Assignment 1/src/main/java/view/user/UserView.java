package view.user;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Created by Paul on 08/04/2018.
 */
public class UserView extends JFrame {


    private JButton viewDetails;
    private JButton delete;
    private JButton edit;
    private JButton add;

    private JTable userData;
    private JScrollPane tableScrollPane;

    private Object[][] data; // table data

    private void initialize(){

        userData = new JTable(new Object[0][0], new String[0]);
        tableScrollPane = new JScrollPane(userData);
//        //table
//        String[] columns = new String[]{"Name", "Card Number", "pnc", "Address", "email"};
//        data = new Object[0][0]; // empty
//        userData = new JTable(data, columns);
//        tableScrollPane = new JScrollPane(userData);

        //buttons
        viewDetails = new JButton("View Details");
        delete = new JButton("Delete");
        add = new JButton("Add");
        edit = new JButton("Edit");
    }

    public void setViewDetailsActionListener(ActionListener actionListener){
        this.viewDetails.addActionListener(actionListener);
    }

    public void setDeleteActionListener(ActionListener actionListener){
        this.delete.addActionListener(actionListener);
    }

    public void setEditActionListener(ActionListener actionListener){
        this.edit.addActionListener(actionListener);
    }
    public void setAddActionListener(ActionListener actionListener){
        this.add.addActionListener(actionListener);
    }


    public void setUserData(String[] columns, Object[][] data){
        this.userData = new JTable(data, columns);
        this.tableScrollPane = new JScrollPane(userData);
    }

    public UserView(String title) throws HeadlessException {
        super(title);
        initialize();
        this.setSize(600, 400);
        this.setLayout(new FlowLayout());
        this.add(this.viewDetails);
        this.add(this.delete);
        this.add(this.edit);
        this.add(this.add);
        this.add(this.tableScrollPane);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }


    public String getSelectedClientName(){
        return userData.getModel().getValueAt(userData.getSelectedRow(), 0).toString();
    }

}
