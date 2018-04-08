package repository.client;

import model.Client;
import model.builder.ClientBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public class ClientRepositoryMySQL implements ClientRepository {

    private final Connection connection;

    public ClientRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Client> findAll() {
        List<Client> clients = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                clients.add(getClientFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clients;
    }

    private Client getClientFromResultSet(ResultSet rs) throws  SQLException{
        return new ClientBuilder()
                .setId(rs.getLong("id"))
                .setName(rs.getString("name"))
                .setAddress(rs.getString("address"))
                .setEmail(rs.getString("email"))
                .setId_card_number(rs.getLong("card_number"))
                .setPnc(rs.getLong("pnc"))
                .createClient();
    }

    @Override
    public Client findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Client.class.getSimpleName());
        }
    }

    @Override
    public Client findByName(String name) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from client where name=\"" + name+"\";";
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getClientFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(new Long(0), Client.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(new Long(0), Client.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Client client) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO client values (null, ?, ?, ?, ?, ?)");
            insertStatement.setString(1, client.getName());
            insertStatement.setLong(2, client.getId_card_number());
            insertStatement.setLong(3, client.getPnc());
            insertStatement.setString(4, client.getAddress());
            insertStatement.setString(5, client.getEmail());
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
            String sql = "DELETE from client where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
