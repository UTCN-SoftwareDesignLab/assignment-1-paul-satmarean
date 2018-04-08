package model.builder;

import model.Account;

import java.util.Date;

public class AccountBuilder {
    private Long id;
    private Long typeId;
    private double ammount;
    private Date creation_date;
    private Long clientId;

    public AccountBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public AccountBuilder setTypeId(Long typeId) {
        this.typeId = typeId;
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

    public AccountBuilder setClientId(Long clientId) {
        this.clientId = clientId;
        return this;
    }

    public Account createAccount() {
        return new Account(id, typeId, ammount, creation_date, clientId);
    }
}