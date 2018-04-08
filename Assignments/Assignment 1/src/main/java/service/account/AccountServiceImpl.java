package service.account;

import com.sun.tools.corba.se.idl.constExpr.Not;
import database.Constants;
import model.*;
import model.builder.ActionBuilder;
import model.validation.AccountValidator;
import model.validation.Notification;
import repository.EntityNotFoundException;
import repository.account.AccountRepository;
import repository.account.bill.BillRepository;
import repository.account.type.TypeRepository;
import service.action.ActionService;
import service.user.AuthenticationService;

import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.function.BooleanSupplier;

import static database.Constants.Rights.*;

/**
 * Created by Paul on 01/04/2018.
 */
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AuthenticationService authService;
    private ActionService actionService;
    private TypeRepository typeRepository;
    private BillRepository billRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AuthenticationService authService,
                              ActionService actionService,
                              TypeRepository typeRepository,
                                BillRepository billRepository) {
        this.accountRepository = accountRepository;
        this.authService = authService;
        this.actionService = actionService;
        this.typeRepository = typeRepository;
        this.billRepository = billRepository;
    }

    private Action getAction(String action){
        return new ActionBuilder()
                .setUserId(authService.currentUser().getId())
                .setAction(action)
                .setDate(new Date())
                .createAction();

    }


    @Override
    public Notification<Account> findByClient(Client client) {
        Notification<Account> findNotification = new Notification<>();
        List<Account> accounts = accountRepository.findAll();
        for(Account account: accounts){
            if(account.getClientId() == client.getId()){
                findNotification.setResult(account);
                return findNotification;
            }
        }
        findNotification.addError("Client has no accounts.");
        return findNotification;
    }

    @Override
    public Notification<Boolean> transferMoney(Account src, Account dst, Double ammount) {
        Notification<Boolean> transferNotification  = new Notification<>();
        actionService.addAction(getAction(TRANSFER));
        try {
            Type type = typeRepository.findById(src.getTypeId());
            if(type.getName().equals(Constants.AccountTypes.CREDIT)){
                src.setAmmount(src.getAmmount() - ammount);
                accountRepository.edit(src);
                dst.setAmmount(dst.getAmmount()+ ammount);
                accountRepository.edit(dst);
                transferNotification.setResult(true);
            }else{
                if(src.getAmmount()>=ammount){
                    src.setAmmount(src.getAmmount() - ammount);
                    accountRepository.edit(src);
                    dst.setAmmount(dst.getAmmount()+ ammount);
                    accountRepository.edit(dst);
                    transferNotification.setResult(true);
                }else{
                    transferNotification.addError("Insufficient funds.");
                }
            }
        }catch (EntityNotFoundException e){
            transferNotification.addError("Account type not found or invalid.");
            return transferNotification;
        }
        return transferNotification;
    }

    @Override
    public Notification<Boolean> payBills(Account account) {
        actionService.addAction(getAction(PROCESS_BILLS));
        Notification<Boolean> payBillsNotification = new Notification<>();
        payBillsNotification.setResult(false);
        List<Bill> bills = null;
        try {
            bills = billRepository.findByAccountId(account.getId());
        }catch (EntityNotFoundException e){
            payBillsNotification.addError("Could not get bills from database.");
            return payBillsNotification;
        }
        if (bills.size() == 0){
            payBillsNotification.addError("No bills to pay for this account.");
            return payBillsNotification;
        }
        Double ammount = 0.0;
        for(Bill bill: bills){
            ammount+=bill.getAmmount();
        }

        if(ammount>account.getAmmount()){
            payBillsNotification.addError("Insuficient funds to pay bills.");
            return payBillsNotification;
        }

        account.setAmmount(account.getAmmount()- ammount);
        accountRepository.edit(account);
        payBillsNotification.setResult(true);
        return payBillsNotification;
    }

    @Override
    public Notification<Boolean> createAccount(Account account, Client client) {
        actionService.addAction(getAction(CREATE_ACCOUNT));
        // TODO: 08/04/2018 validarea

        Notification<Boolean> createAccountNotification = new Notification<>();
        createAccountNotification.setResult(false);

        AccountValidator accountValidator = new AccountValidator(account);
        if(accountValidator.validate() == false){
            createAccountNotification.addError("Account data invalid.");
            return createAccountNotification;
        }

        accountRepository.save(account);
        createAccountNotification.setResult(true);

        return createAccountNotification;
    }

    @Override
    public Notification<Boolean> removeAccount(Account account) {
        actionService.addAction(getAction(DELETE_ACCOUNT));

        Notification<Boolean> removeAccountNotification = new Notification<>();

        removeAccountNotification.setResult(accountRepository.delete(account));

        return removeAccountNotification;
    }

    @Override
    public Notification<List<Bill>> getBills(Account account) {
        Notification<List<Bill>> getBillNotification = new Notification<>();
        try {
            List<Bill> bills = billRepository.findByAccountId(account.getId());
            getBillNotification.setResult(bills);
        }catch(EntityNotFoundException e){
            getBillNotification.addError("Could not find bills for this account.");
        }
        return getBillNotification;
    }
}
