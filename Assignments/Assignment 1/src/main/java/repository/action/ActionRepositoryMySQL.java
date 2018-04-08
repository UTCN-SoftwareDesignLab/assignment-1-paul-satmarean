package repository.action;

import model.Action;
import model.builder.ActionBuilder;
import repository.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public class ActionRepositoryMySQL implements ActionRepository {


    private Connection connection;


    public ActionRepositoryMySQL(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List<Action> findAll() {
        List<Action> actions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from history";
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                actions.add(getActionFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return actions;
    }

    private Action getActionFromResultSet(ResultSet rs) throws SQLException {
        return new ActionBuilder()
                .setId(rs.getLong("id"))
                .setUserId(rs.getLong("userId"))
                .setAction(rs.getString("action"))
                .setDate(new Date(rs.getDate("date").getTime()))
                .createAction();
    }

    @Override
    public List<Action> findByUserId(Long id) {
        List<Action> actions = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from history where userId="+id;
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                actions.add(getActionFromResultSet(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return actions;
    }

    @Override
    public Action findById(Long id) throws EntityNotFoundException {
        try {
            Statement statement = connection.createStatement();
            String sql = "Select * from history where id=" + id;
            ResultSet rs = statement.executeQuery(sql);

            if (rs.next()) {
                return getActionFromResultSet(rs);
            } else {
                throw new EntityNotFoundException(id, Action.class.getSimpleName());
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new EntityNotFoundException(id, Action.class.getSimpleName());
        }
    }

    @Override
    public boolean save(Action action) {
        try {
            PreparedStatement insertStatement = connection
                    .prepareStatement("INSERT INTO history values (null, ?, ?, ?)");
            insertStatement.setLong(1, action.getUserId());
            insertStatement.setString(2, action.getAction());
            insertStatement.setDate(3, new java.sql.Date(action.getDate().getTime()));
            insertStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void removeAll() {

    }
}
