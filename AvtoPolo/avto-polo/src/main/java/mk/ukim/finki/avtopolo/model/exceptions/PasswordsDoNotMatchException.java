package mk.ukim.finki.avtopolo.model.exceptions;

public class PasswordsDoNotMatchException extends RuntimeException {
    public PasswordsDoNotMatchException() {
        super("Passwords do not match");
    }
}
