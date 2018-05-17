package bookstore.repository;

import bookstore.entity.Author;
import bookstore.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 23/04/2018.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer> {

    Author findByName(String name);

}
