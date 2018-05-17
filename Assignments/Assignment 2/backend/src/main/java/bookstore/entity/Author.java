package bookstore.entity;

import javax.persistence.*;

/**
 * Created by Paul on 22/04/2018.
 */
@Entity
@Table(name="author")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idauthor;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;


    public Author() {
    }

    public Author(int idauthor, String name, String description) {
        this.idauthor = idauthor;
        this.name = name;
        this.description = description;
    }

    public int getIdauthor() {
        return idauthor;
    }

    public void setIdauthor(int idauthor) {
        this.idauthor = idauthor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
