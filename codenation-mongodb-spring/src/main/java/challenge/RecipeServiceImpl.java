package challenge;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;

    private final RecipeCommentRepository recipeCommentRepository;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeCommentRepository recipeCommentRepository) {
        this.recipeRepository = recipeRepository;
        this.recipeCommentRepository = recipeCommentRepository;
    }

    @Override
    public Recipe save(Recipe recipe) {
        return recipeRepository.save(recipe);
    }

    @Override
    public void update(String id, Recipe recipe) {
        Recipe recipeUpdate = recipeRepository.findById(id).get();

        recipeUpdate.setTitle(recipe.getTitle());
        recipeUpdate.setDescription(recipe.getDescription());
        recipeUpdate.setIngredients(recipe.getIngredients());

        recipeRepository.save(recipeUpdate);
    }

    @Override
    public void delete(String id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        recipeRepository.delete(recipe.get());
    }

    @Override
    public Recipe get(String id) {
        return this.recipeRepository.findById(id).orElse(null);
    }

    @Override
    public List<Recipe> listByIngredient(String ingredient) {
//        return recipeRepository.findAllByIngredients(ingredient)
//                .stream()
//                .sorted(Comparator.comparing(Recipe::getTitle))
//                .collect(Collectors.toList());
        return recipeRepository.findByIngredientsOrderByTitle(ingredient);
    }

    @Override
    public List<Recipe> search(String search) {
        List<Recipe> recipes = new ArrayList<>();
        recipes.addAll(recipeRepository.findByTitleContainingIgnoreCase(search));
        recipes.addAll(recipeRepository.findByDescriptionContainingIgnoreCase(search));

        return recipes.stream()
                .sorted(Comparator.comparing(Recipe::getTitle))
                .collect(Collectors.toList());
    }

    @Override
    public void like(String id, String userId) {
        this.recipeRepository.findById(id).ifPresent(recipe -> {
            recipe.addLike(userId);
            recipeRepository.save(recipe);
        });
    }

    @Override
    public void unlike(String id, String userId) {
        this.recipeRepository.findById(id).ifPresent(recipe -> {
            recipe.removeLike(userId);
            recipeRepository.save(recipe);
        });
    }

    @Override
    public RecipeComment addComment(String id, RecipeComment comment) {
        Recipe recipe = recipeRepository.findById(id).get();
        List<RecipeComment> comments = recipe.getComments();
        comment.setId(String.valueOf(new ObjectId()));
        comments.add(comment);
        recipe.setComments(comments);
        recipeRepository.save(recipe);

        return comment;
    }

    @Override
    public void updateComment(String id, String commentId, RecipeComment comment) {

    }

    @Override
    public void deleteComment(String id, String commentId) {

    }

}
