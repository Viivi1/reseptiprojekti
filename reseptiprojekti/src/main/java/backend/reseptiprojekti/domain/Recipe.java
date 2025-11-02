package backend.reseptiprojekti.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Recipe {

    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private Long id;

    private String name;
    private String description;

    @ManyToOne
    private Category category;

    @ManyToOne
    private Ingredient mainIngredient;

    public Recipe() {
        this.id = null;
        this.name = null;
        this.description = null;
        this.category = null;
        this.mainIngredient = null;
    }

    public Recipe(Long id, String name, String description, Category category, Ingredient mainIngredient) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.category = category;
        this.mainIngredient = mainIngredient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Ingredient getMainIngredient() {
        return mainIngredient;
    }

    public void setMainIngredient(Ingredient mainIngredient) {
        this.mainIngredient = mainIngredient;
    }

    @Override
    public String toString() {
        return "Recipe [id=" + id + ", name=" + name + ", description=" + description + ", category=" + category
                + ", mainIngredient=" + mainIngredient + "]";
    }


}
