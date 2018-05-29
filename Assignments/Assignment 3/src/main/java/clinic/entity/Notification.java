package clinic.entity;

import javax.persistence.*;

/**
 * Created by Paul on 21/05/2018.
 */
@Entity
@Table(name="notification")
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="user_id")
    private User user;

    @Column(name="message")
    private String message;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="consult_id")
    private Consult consult;

    @Column(name="seen")
    private Boolean seen;

    public Notification() {
    }

    public Notification(long id, User user, String message, Consult consult, Boolean seen) {
        this.id = id;
        this.user = user;
        this.message = message;
        this.consult = consult;
        this.seen = seen;
    }

    public Boolean getSeen() {
        return seen;
    }

    public void setSeen(Boolean seen) {
        this.seen = seen;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Consult getConsult() {
        return consult;
    }

    public void setConsult(Consult consult) {
        this.consult = consult;
    }
}
