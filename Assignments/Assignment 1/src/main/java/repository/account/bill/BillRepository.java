package repository.account.bill;


import model.Bill;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 08/04/2018.
 */
public interface BillRepository {
    List<Bill> findAll();

    List<Bill> findByAccountId(Long id) throws EntityNotFoundException;

    boolean save(Bill bill);

    boolean delete(Bill bill);

    void deleteAll();
}
