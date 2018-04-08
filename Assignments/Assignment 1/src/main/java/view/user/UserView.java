package view.user;

import javax.swing.*;

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

    void setUserData(JTable userData){
        this.userData = userData;
        this.tableScrollPane = new JScrollPane(userData);
    }




}
