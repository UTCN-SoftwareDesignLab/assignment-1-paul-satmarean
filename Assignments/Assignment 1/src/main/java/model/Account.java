package model;

import java.util.Date;

/**
 * Created by Paul on 25/03/2018.
 */
public class Account {

    private Long id;

    private Long typeId;

    private double ammount;

    private Date creation_date;

    private Long clientId;

    public Account() {
    }


    public Account(Long id, Long typeId, double ammount, Date creation_date, Long clientId) {
        this.id = id;
        this.typeId = typeId;
        this.ammount = ammount;
        this.creation_date = creation_date;
        this.clientId = clientId;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeId) {
        this.typeId = typeId;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }

    public Date getCreation_date() {
        return creation_date;
    }

    public void setCreation_date(Date creation_date) {
        this.creation_date = creation_date;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
