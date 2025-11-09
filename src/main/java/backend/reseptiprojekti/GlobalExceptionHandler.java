package backend.reseptiprojekti;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) {
        ex.printStackTrace(); // Tulostaa virheen Rahti-lokiin
        model.addAttribute("errorMessage", ex.getMessage());
        return "error"; // Luo myös error.html näkymä
    }
}

// RAHTI ERROR SELVITTÄMISTÄ VARTEN 

