package model;

/**
 * Created by Paul on 25/03/2018.
 */
public class Client {

    private Long id;

    private String name;

    private Long id_card_number;

    private Long pnc;

    private String address;

    private String email;



    public Client() {
    }

    public Client(Long id, String name, Long id_card_number, Long pnc, String address, String email) {
        this.id = id;
        this.name = name;
        this.id_card_number = id_card_number;
        this.pnc = pnc;
        this.address = address;
        this.email = email;
    }



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId_card_number() {
        return id_card_number;
    }

    public void setId_card_number(Long id_card_number) {
        this.id_card_number = id_card_number;
    }

    public Long getPnc() {
        return pnc;
    }

    public void setPnc(Long pnc) {
        this.pnc = pnc;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
