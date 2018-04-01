package model;

/**
 * Created by Paul on 25/03/2018.
 */
public class Bill {

    public Long id;
    public Long account_number;
    public String name;
    public double ammount;


    public Bill() {
    }


    public Bill(Long id, Long account_number, String name, double ammount) {
        this.id = id;
        this.account_number = account_number;
        this.name = name;
        this.ammount = ammount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccount_number() {
        return account_number;
    }

    public void setAccount_number(Long account_number) {
        this.account_number = account_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmmount() {
        return ammount;
    }

    public void setAmmount(double ammount) {
        this.ammount = ammount;
    }
}
