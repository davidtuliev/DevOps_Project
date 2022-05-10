package mk.ukim.finki.avtopolo.repository;

import mk.ukim.finki.avtopolo.model.ShoppingCart;
import mk.ukim.finki.avtopolo.model.User;
import mk.ukim.finki.avtopolo.model.enums.CartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Long> {

    Optional<ShoppingCart> findByUserAndCartStatus(User user, CartStatus status);

}
