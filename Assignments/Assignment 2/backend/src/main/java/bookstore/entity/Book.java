package bookstore.entity;

import javax.persistence.*;

/**
 * Created by Paul on 30/04/2018.
 */
@Entity
@Table(name="book")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idbook;

    @Column(name="title")
    private String title;

    @Column(name="quantity")
    private Integer quantity;


    @Column(name="price")
    private Double price;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="author_id")
    private Author author;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="genre_id")
    private Genre genre;

    public Book() {
    }

    public Book(Integer idbook, String title, Integer quantity, Double price, Author author, Genre genre) {
        this.idbook = idbook;
        this.title = title;
        this.quantity = quantity;
        this.price = price;
        this.author = author;
        this.genre = genre;
    }

    public Integer getIdbook() {
        return idbook;
    }

    public void setIdbook(Integer idbook) {
        this.idbook = idbook;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
