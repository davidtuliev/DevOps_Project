package mk.ukim.finki.avtopolo.web;

import mk.ukim.finki.avtopolo.model.ShoppingCart;
import mk.ukim.finki.avtopolo.model.User;
import mk.ukim.finki.avtopolo.service.ShoppingCartService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    private final ShoppingCartService shoppingCartService;

    public ShoppingCartController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public String getShoppingCartPage(@RequestParam(required = false) String error, HttpServletRequest req, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        String username = req.getRemoteUser();
        ShoppingCart shoppingCart = this.shoppingCartService.getActiveShoppingCart(username);
        model.addAttribute("automobiles", this.shoppingCartService.
                listAllAutomobilesInShoppingCart(shoppingCart.getId()));
        model.addAttribute("bodyContent", "shopping-cart");
        return "master-template";
    }

    @PostMapping("/add-car/{id}")
    public String addProductToShoppingCart(@PathVariable Long id, Authentication authentication) {
        try {
            User user = (User) authentication.getPrincipal();
            this.shoppingCartService.addAutomobileToShoppingCart(user.getUsername(),id);
            return "redirect:/automobiles";
        }catch (RuntimeException exception) {
            return "redirect:/automobiles?errors" + exception.getMessage();
        }
    }

    @DeleteMapping("/delete/{id}")
    public String deleteAutomobile(@PathVariable Long id, Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        String username = user.getUsername();
        this.shoppingCartService.deleteAutomobile(username, id);
        return "redirect:/shopping-cart";
    }
}
