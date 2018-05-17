package bookstore.repository;

import bookstore.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 30/04/2018.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer> {

    Book findByTitle(String title);

}
