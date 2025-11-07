package backend.reseptiprojekti.web;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import backend.reseptiprojekti.domain.Recipe;
import backend.reseptiprojekti.domain.RecipeRepository;

@RestController
public class RecipeRestController {

    @Autowired
    private RecipeRepository recipeRepository;

    @GetMapping("/api/recipes")
    public Iterable<Recipe> getAllRecipes() {
        return recipeRepository.findAll();
    }

    @GetMapping("/api/recipes/{id}")
    public Optional<Recipe> getRecipeById(@PathVariable("id") Long id) {
        return recipeRepository.findById(id);
    }
}
