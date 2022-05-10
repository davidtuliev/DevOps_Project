package mk.ukim.finki.avtopolo.service.Impl;

import mk.ukim.finki.avtopolo.model.Automobile;
import mk.ukim.finki.avtopolo.model.ShoppingCart;
import mk.ukim.finki.avtopolo.model.User;
import mk.ukim.finki.avtopolo.model.enums.CartStatus;
import mk.ukim.finki.avtopolo.model.exceptions.AutomobileAlreadyInShoppingCartException;
import mk.ukim.finki.avtopolo.model.exceptions.AutomobileNotFoundException;
import mk.ukim.finki.avtopolo.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.avtopolo.model.exceptions.UserNotFoundException;
import mk.ukim.finki.avtopolo.repository.ShoppingCartRepository;
import mk.ukim.finki.avtopolo.repository.UserRepository;
import mk.ukim.finki.avtopolo.service.AutomobileService;
import mk.ukim.finki.avtopolo.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final AutomobileService automobileService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository, UserRepository userRepository,
                                   AutomobileService automobileService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.automobileService = automobileService;
    }

    @Override
    public List<Automobile> listAllAutomobilesInShoppingCart(Long id) {
        if (this.shoppingCartRepository.findById(id).isEmpty()) {
            throw new ShoppingCartNotFoundException(id);
        }
        return this.shoppingCartRepository.findById(id).get().getAutomobiles();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));

        return  this.shoppingCartRepository.findByUserAndCartStatus(user, CartStatus.CREATED)
                .orElseGet(() -> {
                    ShoppingCart cart  = new ShoppingCart(user);
                    return this.shoppingCartRepository.save(cart);
                });
    }

    @Override
    public ShoppingCart addAutomobileToShoppingCart(String username, Long automobileId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Automobile automobile =  this.automobileService.findById(automobileId)
                .orElseThrow(()-> new AutomobileNotFoundException(automobileId));
        if (shoppingCart.getAutomobiles().stream().anyMatch(p -> p.getId().equals(automobileId))) {
            throw new AutomobileAlreadyInShoppingCartException(automobileId, username);
        }
        shoppingCart.getAutomobiles().add(automobile);
        return this.shoppingCartRepository.save(shoppingCart);
    }

    @Override
    public void deleteAutomobile(String username, Long automobileId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Automobile automobile = this.automobileService.findById(automobileId)
                .orElseThrow(() -> new AutomobileNotFoundException(automobileId));
        shoppingCart.getAutomobiles().remove(automobile);
        this.shoppingCartRepository.save(shoppingCart);

    }

}
