package model;

import java.util.Date;

/**
 * Created by Paul on 25/03/2018.
 */
public class Account {

    private Long id;

    private String type;

    private double ammount;

    private Date creation_date;

    public Account() {
    }

    public Account(Long id, String type, double ammount, Date creation_date) {
        this.id = id;
        this.type = type;
        this.ammount = ammount;
        this.creation_date = creation_date;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
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
}
