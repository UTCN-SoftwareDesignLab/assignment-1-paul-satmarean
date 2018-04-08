package service.bill;

import model.Account;
import model.Bill;
import model.validation.Notification;
import repository.account.bill.BillRepository;

/**
 * Created by Paul on 08/04/2018.
 */
public class BillServiceImpl implements BillService {

    BillRepository billRepository;

    public BillServiceImpl(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public Notification<Boolean> add(Bill bill) {
        Notification<Boolean> addNotification = new Notification<>();

        addNotification.setResult(billRepository.save(bill));
        return addNotification;
    }

    @Override
    public Notification<Boolean> remove(Bill bill) {
        Notification<Boolean> deleteNotification = new Notification<>();

        deleteNotification.setResult(billRepository.delete(bill));
        return deleteNotification;
    }
}
