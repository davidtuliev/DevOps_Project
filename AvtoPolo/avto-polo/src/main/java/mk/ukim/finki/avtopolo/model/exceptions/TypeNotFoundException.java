package mk.ukim.finki.avtopolo.model.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class TypeNotFoundException extends RuntimeException {
    public TypeNotFoundException(Long id) {
        super(String.format("Type with id %d was not found", id));
    }

}
