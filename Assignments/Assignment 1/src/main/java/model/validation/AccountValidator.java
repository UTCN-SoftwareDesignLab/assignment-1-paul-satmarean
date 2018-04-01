package model.validation;

import database.Constants;
import model.Account;
import model.Client;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Paul on 25/03/2018.
 */
public class AccountValidator {

    private final List<String> errors;

    private final Account account;

    public AccountValidator(Account account){
        this.account = account;
        errors = new ArrayList<String>();
    }


    private void validateType(String type){
        boolean found = false;
        for(String e: Constants.AccountTypes.TYPES){
            if(e.equals(type)){
                found = true;
            }
        }
        if(!found){
            errors.add("Account type invalid!");
        }
    }

    public boolean validate(){
        validateType(this.account.getType());
        return errors.isEmpty();
    }



}
