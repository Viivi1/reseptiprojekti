package backend.reseptiprojekti;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import backend.reseptiprojekti.domain.AppUser;
import backend.reseptiprojekti.domain.AppUserRepository;
import backend.reseptiprojekti.domain.Category;
import backend.reseptiprojekti.domain.CategoryRepository;
import backend.reseptiprojekti.domain.Ingredient;
import backend.reseptiprojekti.domain.IngredientRepository;
import backend.reseptiprojekti.domain.Recipe;
import backend.reseptiprojekti.domain.RecipeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReseptiprojektiApplication {

    private static final Logger log = LoggerFactory.getLogger(ReseptiprojektiApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(ReseptiprojektiApplication.class, args);
    }

    @Bean
    public CommandLineRunner demo(RecipeRepository recipeRepository,
                                  CategoryRepository categoryRepository,
                                  IngredientRepository ingredientRepository,
                                  AppUserRepository appUserRepository) {
        return (args) -> {
            log.info("Lisätään kategorioita");
            Category lunch = categoryRepository.save(new Category(null, "Lounas", null));
            Category dinner = categoryRepository.save(new Category(null, "Päivällinen", null));
            Category dessert = categoryRepository.save(new Category(null, "Jälkiruoka", null));

            log.info("Lisätään raaka-aineita");
            Ingredient tofu = ingredientRepository.save(new Ingredient(null, "Tofu", null));
            Ingredient salmon = ingredientRepository.save(new Ingredient(null, "Lohi", null));
            Ingredient potato = ingredientRepository.save(new Ingredient(null, "Peruna", null));

            log.info("Lisätään reseptejä");
            recipeRepository.save(new Recipe(null, "Tofu stir fry", "Nopea kasviswokki", lunch, tofu));
            recipeRepository.save(new Recipe(null, "Lohipasta", "Kermaista lohipastaa",dinner, salmon));
            recipeRepository.save(new Recipe(null, "Perunamuffinit", "Suolaiset muffinit perunasta",lunch, potato));
        
            // USERS
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
            appUserRepository.save(user1);
            appUserRepository.save(user2);
        };
    }
}

