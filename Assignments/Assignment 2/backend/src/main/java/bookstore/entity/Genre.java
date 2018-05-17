package bookstore.entity;

import javax.persistence.*;

/**
 * Created by Paul on 30/04/2018.
 */
@Entity
@Table(name="genre")
public class Genre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idgenre;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;


    public Genre() {
    }

    public Genre(Integer idgenre, String name, String description) {
        this.idgenre = idgenre;
        this.name = name;
        this.description = description;
    }

    public Integer getIdgenre() {
        return idgenre;
    }

    public void setIdgenre(Integer idgenre) {
        this.idgenre = idgenre;
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
