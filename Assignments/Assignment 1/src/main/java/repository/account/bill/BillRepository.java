package repository.account.bill;


import model.Bill;
import repository.EntityNotFoundException;

import java.util.List;

/**
 * Created by Paul on 08/04/2018.
 */
public interface BillRepository {
    public List<Bill> findAll();

    public List<Bill> findByAccountId(Long id) throws EntityNotFoundException;

    public boolean save(Bill bill);

    public void deleteAll();
}
