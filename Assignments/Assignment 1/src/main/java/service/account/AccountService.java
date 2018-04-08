package service.account;

import model.Account;
import model.Bill;
import model.Client;
import model.User;
import model.validation.Notification;

import java.util.List;

/**
 * Created by Paul on 01/04/2018.
 */
public interface AccountService {

    Notification<Account> findByClient(Client client);

    Notification<Boolean> transferMoney(Account src, Account dst, Double ammount);

    Notification<Boolean> payBills(Account account);

    Notification<Boolean> createAccount(Account account, Client client);

    Notification<Boolean> removeAccount(Account account);

    Notification<List<Bill>> getBills(Account account);

}
