package backend.reseptiprojekti.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import backend.reseptiprojekti.domain.CategoryRepository;
import backend.reseptiprojekti.domain.IngredientRepository;
import backend.reseptiprojekti.domain.Recipe;
import backend.reseptiprojekti.domain.RecipeRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class RecipeController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository catRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    //KAIKKI RESEPTIT LISTATTUNA
    @GetMapping("/recipes")
    public String recipeList(Model model) {
        model.addAttribute("recipes", recipeRepository.findAll());
        return "recipes"; //recipes.html
    }
    
    // KIRJAN LISÄYS
    @GetMapping("/add")
    public String showAddRecipe(Model model){
        model.addAttribute("recipe", new Recipe());
        model.addAttribute("categories", catRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "addrecipe"; //addrecipe.html
    }
    @PostMapping("/addrecipe")
    public String addBook(@ModelAttribute Recipe recipe) {
        recipeRepository.save(recipe);
        return "redirect:/recipes";
    }

    // KIRJAN MUOKKAUS
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Recipe recipe = recipeRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Virheellinen resepti-id: " + id));
        model.addAttribute("recipe", recipe);
        model.addAttribute("categories", catRepository.findAll());
        model.addAttribute("ingredients", ingredientRepository.findAll());
        return "editrecipe"; //editrecipe.html
    }

    // MUOKKAUKSEN TALLENNUS
    @PostMapping("/edit/{id}")
    public String updateRecipe(@PathVariable("id") Long id, @ModelAttribute Recipe recipe) {
        recipe.setId(id);
        recipeRepository.save(recipe);
        return "redirect:/recipes";
    }

    // POISTO
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/delete/{id}")
    public String deleteRecipe(@PathVariable("id") Long id) {
        recipeRepository.deleteById(id);
        return "redirect:/recipes";
    }

}
