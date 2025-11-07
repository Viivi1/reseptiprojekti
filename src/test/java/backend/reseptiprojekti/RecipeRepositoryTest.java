package backend.reseptiprojekti;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import backend.reseptiprojekti.domain.Category;
import backend.reseptiprojekti.domain.CategoryRepository;
import backend.reseptiprojekti.domain.Recipe;
import backend.reseptiprojekti.domain.RecipeRepository;

@DataJpaTest
public class RecipeRepositoryTest {

    @Autowired
    private RecipeRepository recipeRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void testSaveRecipe() {
        Recipe recipe = new Recipe();
        recipe.setName("Testiresepti");
        recipe.setDescription("Testikuvaus");
        recipeRepository.save(recipe);

        assertThat(recipe.getId()).isNotNull();
    }

    @Test
    public void testFindByName() {
        Recipe recipe = new Recipe();
        recipe.setName("Testiresepti");
        recipe.setDescription("Testikuvaus");
        recipeRepository.save(recipe);

        List<Recipe> found = recipeRepository.findByName("Testiresepti");
        assertThat(found).isNotEmpty();
        assertEquals("Testikuvaus", found.get(0).getDescription());
    }

    @Test
    public void testFindByCategory() {
        Category category = new Category();
        category.setName("Testikategoria");
        categoryRepository.save(category);

        Recipe recipe = new Recipe();
        recipe.setName("Testiresepti");
        recipe.setDescription("Testikuvaus");
        recipe.setCategory(category);
        recipeRepository.save(recipe);

        List<Recipe> found = recipeRepository.findByCategory(category);

        assertThat(found).isNotEmpty();
        assertEquals("Testiresepti", found.get(0).getName());
    }
}

