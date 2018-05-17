package bookstore.repository;

import bookstore.entity.Genre;
import org.springframework.boot.autoconfigure.integration.IntegrationAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Paul on 01/05/2018.
 */
@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer>{

    Genre findByName(String name);

}
