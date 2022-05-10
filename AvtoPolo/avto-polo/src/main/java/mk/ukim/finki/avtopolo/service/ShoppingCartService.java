package mk.ukim.finki.avtopolo.service;

import mk.ukim.finki.avtopolo.model.Automobile;
import mk.ukim.finki.avtopolo.model.ShoppingCart;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ShoppingCartService {

    List<Automobile> listAllAutomobilesInShoppingCart(Long id);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addAutomobileToShoppingCart(String username, Long automobileId);

    void deleteAutomobile(String username, Long id);
}
