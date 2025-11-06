package backend.reseptiprojekti.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
    List<Recipe> findByCategoryName(String name);
    List<Recipe> findByMainIngredientName(String name);
    List<Recipe> findByCategory(Category category);
}
