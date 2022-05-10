package mk.ukim.finki.avtopolo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.PRECONDITION_FAILED)
public class AutomobileAlreadyInShoppingCartException extends RuntimeException {
    public AutomobileAlreadyInShoppingCartException(Long id, String username) {
        super(String.format("Product with id %d is already exists in the shopping cart for user %s",
                id,
                username));
    }
}
