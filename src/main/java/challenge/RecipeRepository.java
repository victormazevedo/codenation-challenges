package challenge;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends MongoRepository<Recipe, String> {
    List<Recipe> findByIngredientsOrderByTitle(String ingredient);

    List<Recipe> findByTitleContainingIgnoreCase(String title);

    List<Recipe> findByDescriptionContainingIgnoreCase(String description);
}
