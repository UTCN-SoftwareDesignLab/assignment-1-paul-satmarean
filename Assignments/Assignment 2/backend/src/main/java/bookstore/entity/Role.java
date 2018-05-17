package bookstore.entity;

import javax.persistence.*;

/**
 * Created by Paul on 23/04/2018.
 */
@Entity
@Table(name="role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idrole;

    @Column(name="name")
    private String name;

    public Role(int idrole, String name) {
        this.idrole = idrole;
        this.name = name;
    }

    public Role() {
    }

    public int getIdrole() {
        return idrole;
    }

    public void setIdrole(int idrole) {
        this.idrole = idrole;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
