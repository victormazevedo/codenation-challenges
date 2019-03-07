package challenge;

import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RecipeServiceImpl implements RecipeService {

	private final RecipeRepository recipeRepository;

	public RecipeServiceImpl(RecipeRepository recipeRepository) {
		this.recipeRepository = recipeRepository;
	}

	@Override
	public Recipe save(Recipe recipe) {
		return recipeRepository.save(recipe);
	}

	@Override
	public void update(String id, Recipe recipe) {
		recipe.setId(id);
		recipeRepository.save(recipe);
	}

	@Override
	public void delete(String id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		recipeRepository.delete(recipe.get());
	}

	@Override
	public Recipe get(String id) {
		Optional<Recipe> recipe = recipeRepository.findById(id);
		return recipe.get();
	}

	@Override
	public List<Recipe> listByIngredient(String ingredient) {
		return recipeRepository.findAllByIngredients(ingredient)
				.stream()
				.sorted(Comparator.comparing(Recipe::getTitle))
				.collect(Collectors.toList());

	}

	@Override
	public List<Recipe> search(String search) {
		return null;
	}

	@Override
	public void like(String id, String userId) {

	}

	@Override
	public void unlike(String id, String userId) {

	}

	@Override
	public RecipeComment addComment(String id, RecipeComment comment) {
		return null;
	}

	@Override
	public void updateComment(String id, String commentId, RecipeComment comment) {

	}

	@Override
	public void deleteComment(String id, String commentId) {

	}

}
