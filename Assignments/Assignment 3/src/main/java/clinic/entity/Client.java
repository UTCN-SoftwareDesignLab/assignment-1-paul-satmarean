package clinic.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Paul on 17/05/2018.
 */
@Entity
@Table(name="client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="id_card_number")
    private String id_card_no;

    @Column(name="pnc")
    private String pnc;

    @Column(name="birth_date")
    private Date birth_date;

    @Column(name="address")
    private String address;

    public Client(Long id, String id_card_no, String pnc, Date birth_date, String address) {
        this.id = id;
        this.id_card_no = id_card_no;
        this.pnc = pnc;
        this.birth_date = birth_date;
        this.address = address;
    }

    public Client() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getId_card_no() {
        return id_card_no;
    }

    public void setId_card_no(String id_card_no) {
        this.id_card_no = id_card_no;
    }

    public String getPnc() {
        return pnc;
    }

    public void setPnc(String pnc) {
        this.pnc = pnc;
    }

    public Date getBirth_date() {
        return birth_date;
    }

    public void setBirth_date(Date birth_date) {
        this.birth_date = birth_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
