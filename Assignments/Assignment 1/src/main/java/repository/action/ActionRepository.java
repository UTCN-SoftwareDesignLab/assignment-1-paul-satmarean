package repository.action;

import model.Account;
import model.Action;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public interface ActionRepository {

    public List<Action> findAll();

    public List<Action> findByUserId(Long id);

    public Action findById(Long id) throws EntityNotFoundException;

    public boolean save(Action action);

    public void removeAll();

}
