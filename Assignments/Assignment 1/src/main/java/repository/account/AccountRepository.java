package repository.account;

import model.Account;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public interface AccountRepository {

    List<Account> findAll();

    Account findById(Long id) throws EntityNotFoundException;

    boolean save(Account account);

    boolean edit(Account account);

    boolean delete(Account account);

    void removeAll();

}
