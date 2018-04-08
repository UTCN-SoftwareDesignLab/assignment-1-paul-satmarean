package service.bill;

import model.Account;
import model.Bill;
import model.validation.Notification;

/**
 * Created by Paul on 08/04/2018.
 */
public interface BillService {

    Notification<Boolean> add(Bill bill);

    Notification<Boolean> remove(Bill bill);
}
