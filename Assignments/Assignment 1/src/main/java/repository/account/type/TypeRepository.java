package repository.account.type;

import model.Type;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 06/04/2018.
 */
public interface TypeRepository {

    public List<Type> findAll();

    public Type findById(Long id) throws EntityNotFoundException;

    public boolean save(Type t);

    public void deleteAll();

}
