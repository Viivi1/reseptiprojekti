package backend.reseptiprojekti.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import backend.reseptiprojekti.domain.Category;
import backend.reseptiprojekti.domain.CategoryRepository;
import backend.reseptiprojekti.domain.IngredientRepository;
import backend.reseptiprojekti.domain.Recipe;
import backend.reseptiprojekti.domain.RecipeRepository;
import jakarta.validation.Valid;

@Controller
public class CategoryController {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private CategoryRepository catRepository;
    @Autowired
    private IngredientRepository ingredientRepository;

    // KATEGORIAT LISTATTUNA
    @GetMapping("/categories")
    public String showCategoryList(Model model) {
        model.addAttribute("categories", catRepository.findAll());
        return "categories";
    }

    // RESEPTIN ETSIMINEN KATEGORIAN MUKAAN
    @GetMapping("/recipes/category/{id}")
    public String showRecipesByCategory(@PathVariable("id") Long id, Model model) {
        Category category = catRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Virheellinen kategoria-id: " + id));
        List<Recipe> recipes = recipeRepository.findByCategory(category);
        model.addAttribute("recipes", recipes);
        model.addAttribute("selectedCategory", category.getName());
        return "recipesbycategory";
    }

    // KATEGORIAN LISÄYS
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/addcategory")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "addcategory";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/addcategory")
    public String saveCategory(@Valid @ModelAttribute Category category, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "addcategory"; // takaisin lomakkeeseen addcategory.html
        }
        catRepository.save(category);
        return "redirect:/categories";
    }

    // KATEGORIAN MUOKKAUS
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/editcategory/{id}")
    public String showEditCategoryForm(@PathVariable("id") Long id, Model model) {
        Category category = catRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Virheellinen kategoria-id: " + id));
        model.addAttribute("category", category);
        return "editcategory";
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/editcategory/{id}")
    public String updateCategory(@PathVariable("id") Long id, @ModelAttribute Category category) {
        category.setId(id);
        catRepository.save(category);
        return "redirect:/categories";
    }

    // KATEGORIAN POISTO
    // menee pieleen jos yrittää poistaa kategorian, joka on reseptissä käytössä
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/deletecategory/{id}")
    public String deleteCategory(@PathVariable("id") Long id) {
        catRepository.deleteById(id);
        return "redirect:/categories";
    }

}
