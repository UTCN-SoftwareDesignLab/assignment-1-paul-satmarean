package repository.account.type;


import model.Type;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 06/04/2018.
 */
public class TypeRepositoryMySQL implements TypeRepository{

    Connection connection;

    public TypeRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Type> findAll() {
        List<Type> types = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from account";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                types.add(getTypeFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return types;
    }
    private Type getTypeFromResultSet(ResultSet rs) throws SQLException {
        Type type = new Type();
        type.setId(rs.getLong("id"));
        type.setName(rs.getString("name"));
        return type;
    }

    @Override
    public boolean save(Type t) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO type values (null, ?)");
            insertStatement.setString(1, t.getName());
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public Type findById(Long id) throws EntityNotFoundException{
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from type where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getTypeFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Type.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Type.class.getSimpleName());
        }
    }

    @Override
    public void deleteAll() {
        try {
            Statement statement = connection.createStatement();
            String sql = "DELETE from type where id >= 0";
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
