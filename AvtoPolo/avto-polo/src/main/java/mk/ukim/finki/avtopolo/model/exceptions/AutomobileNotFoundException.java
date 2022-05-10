package mk.ukim.finki.avtopolo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AutomobileNotFoundException extends RuntimeException {
    public AutomobileNotFoundException(Long id) {
        super(String.format("Automobile with id %d was not found", id));
    }

}
