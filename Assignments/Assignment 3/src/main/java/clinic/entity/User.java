package clinic.entity;

import javax.annotation.Generated;
import javax.persistence.*;
import java.util.List;

/**
 * Created by Paul on 17/05/2018.
 */

@Entity
@Table(name="user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;

    @Column(name="username")
    private String username;

    @Column(name="password")
    private String password;

    @Column(name="email")
    private String email;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="role_id")
    private Role role;

//    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy="user")
////    @OneToMany(fetch= FetchType.EAGER, mappedBy = "user")
//    //@JoinColumn(name="user_id")
//    private List<Notification> notifications;

    public User(Long id, String name, String username, String password, String email, Role role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }

    public User(){

    }

//    public List<Notification> getNotifications() {
//        return notifications;
//    }
//
//    public void setNotifications(List<Notification> notifications) {
//        this.notifications = notifications;
//    }

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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
