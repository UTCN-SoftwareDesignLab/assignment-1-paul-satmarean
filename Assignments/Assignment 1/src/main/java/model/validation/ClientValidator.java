package model.validation;

import model.Client;
import model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created by Paul on 25/03/2018.
 */
public class ClientValidator {
    private static final String EMAIL_VALIDATION_REGEX = "^[a-zA-Z0-9_!#$%&'*+/=?`{|}~^.-]+@[a-zA-Z0-9.-]+$";


    private final List<String> errors;

    private final Client client;

    public ClientValidator(Client client){
        this.client = client;
        errors = new ArrayList<String>();
    }

    public boolean validate(){
        validateName(client.getName());
        validateAddress(client.getAddress());
        validateEmail(client.getEmail());
        validateIdCardNumber(client.getId_card_number());
        validatePnc(client.getPnc());
        return errors.isEmpty();
    }

    public List<String> getErrors(){
        return errors;
    }

    private void validateName(String name){
        if(name.equals("")){
            errors.add("Name cannot be empty!");
        }
    }

    private void validateIdCardNumber(Long number){
        if(number==null || number==0){
            errors.add("Card number cannot be empty!");
        }
    }

    private void validatePnc(Long pnc){
        if(pnc==null || pnc==0){
            errors.add("Personal numeric code cannot be empty!");
        }
    }

    private void validateAddress(String address){
        if(address.equals("")){
            errors.add("Address cannot be empty!");
        }
    }

    private void validateEmail(String email){
        if (!Pattern.compile(EMAIL_VALIDATION_REGEX).matcher(email).matches()) {
            errors.add("Invalid email!");
        }
    }



}
