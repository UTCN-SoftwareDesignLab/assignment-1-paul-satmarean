package repository.account;

import model.Account;
import model.builder.AccountBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public class AccountRepositoryMySQL implements AccountRepository{


    Connection connection;

    @Override
    public boolean delete(Account account) {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id ="+account.getId();
            statement.executeUpdate(sql);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public AccountRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }


    @Override
    public List<Account> findAll() {
        List<Account> accounts = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                accounts.add(getAccountFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return accounts;
    }

    private Account getAccountFromResultSet(ResultSet rs) throws SQLException {
        return new AccountBuilder()
                .setId(rs.getLong("id"))
                .setAmmount(rs.getDouble("ammount"))
                .setTypeId(rs.getLong("typeId"))
                .setCreation_date(new Date(rs.getDate("created").getTime()))
                .setClientId(rs.getLong("clientId"))
                .createAccount();

    }

    @Override
    public Account findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getAccountFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Account.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Account.class.getSimpleName());
        }
    }


    @Override
    public boolean edit(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("UPDATE account SET typeId= ? ,ammount= ?, created=?, clientId=? WHERE `id`= ? ;");
            insertStatement.setLong(1, account.getTypeId());
            insertStatement.setDouble(2, account.getAmmount());
            insertStatement.setDate(3, new java.sql.Date(account.getCreation_date().getTime()));
            insertStatement.setLong(4, account.getClientId());
            insertStatement.setLong(5, account.getId());

            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean save(Account account) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO account values (null, ?, ?, ?, ?)");
            insertStatement.setLong(1, account.getTypeId());
            insertStatement.setDouble(2, account.getAmmount());
            insertStatement.setDate(3, new java.sql.Date(account.getCreation_date().getTime()));
            insertStatement.setLong(4, account.getClientId());

            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }



    @Override
    public void removeAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from account where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
