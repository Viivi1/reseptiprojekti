package backend.reseptiprojekti.domain;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id; 
    
    @NotBlank(message = "Lisää kategorialle nimi")
    private String name;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "category")
    private List<Recipe> recipes;

    public Category() {
        this.id = null;
        this.name = null;
        this.recipes = null;
    }

    public Category(Long id, String name, List<Recipe> recipes) {
        this.id = id;
        this.name = name;
        this.recipes = recipes;
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

    public List<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(List<Recipe> recipes) {
        this.recipes = recipes;
    }

    @Override
    public String toString() {
        return "Category [id=" + id + ", name=" + name + ", recipes=" + recipes + "]";
    }


}
