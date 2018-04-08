package repository.action;

import database.DBConnectionFactory;
import database.JDBConnectionWrapper;
import model.Account;
import model.Action;
import model.builder.ActionBuilder;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import repository.Cache;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by Paul on 08/04/2018.
 */
public class ActionRepositoryMySQLTest {


    private static ActionRepository actionRepository;


    @BeforeClass
    public static void setupClass(){
        actionRepository = new ActionRepositoryCacheDecorator(
                new ActionRepositoryMySQL(
                        new DBConnectionFactory().getConnectionWrapper(true).getConnection()
                        ),new Cache<>()
        );
    }


    @Before
    public void cleanUp(){
        actionRepository.removeAll();
    }

    @Test
    public void findAll() throws  Exception{
        List<Action> actionList = actionRepository.findAll();
        assertEquals(actionList.size(), 0);
    }

    @Test
    public void findAllDbNotEmpty() throws Exception{
        Action action = new ActionBuilder()
                .setAction("Test action.")
                .setDate(new Date())
                .setUserId(1L)
                .createAction();

        actionRepository.save(action);
        actionRepository.save(action);

        List<Action> actions = actionRepository.findAll();

        assertEquals(actions.size(), 2);
    }

    @After
    public void removeAll(){
        actionRepository.removeAll();
    }
}
