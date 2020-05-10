package challenge;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface QuoteRepository extends CrudRepository<Quote, Integer> {

    @Query(value = "SELECT s.id, s.actor, s.detail FROM SCRIPTS S ORDER BY RAND() LIMIT 1", nativeQuery = true)
    Quote findRandomQuote();

    List<Quote> findAllByActor(String actor);

}
