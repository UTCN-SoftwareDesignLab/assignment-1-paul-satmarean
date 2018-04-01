package model.builder;

import model.Account;

import java.util.Date;

public class AccountBuilder {
    private Long id;
    private String type;
    private double ammount;
    private Date creation_date;

    public AccountBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public AccountBuilder setAmmount(double ammount) {
        this.ammount = ammount;
        return this;
    }

    public AccountBuilder setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
        return this;
    }

    public Account createAccount() {
        return new Account(id, type, ammount, creation_date);
    }
}