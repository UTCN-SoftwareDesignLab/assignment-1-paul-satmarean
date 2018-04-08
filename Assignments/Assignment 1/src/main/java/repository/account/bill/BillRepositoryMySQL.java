package repository.account.bill;

import model.Bill;
import model.Type;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 08/04/2018.
 */
public class BillRepositoryMySQL implements BillRepository{
    Connection connection;

    public BillRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Bill> findAll() {
        List<Bill> bills = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from bill";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                bills.add(getBillFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bills;
    }

    private Bill getBillFromResultSet(ResultSet rs) throws SQLException {
        Bill bill = new Bill();
        bill.setAmmount(rs.getDouble("ammount"));
        bill.setName(rs.getString("name"));
        bill.setId(rs.getLong("id"));
        bill.setAccount_number(rs.getLong("accountId"));
        return bill;
    }

    @Override
    public boolean save(Bill bill) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO bill values (null, ?, ?, ?)");
            insertStatement.setLong(1, bill.getAccount_number());
            insertStatement.setString(2, bill.getName());
            insertStatement.setDouble(3, bill.getAmmount());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Bill> findByAccountId(Long id) throws EntityNotFoundException {
        List<Bill> bills = new ArrayList<>();

        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from bill where accountId=" + id;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                bills.add(getBillFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Bill.class.getSimpleName());
        }
        return bills;
    }

    @Override
    public void deleteAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from bill where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
