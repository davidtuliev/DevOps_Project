package mk.ukim.finki.avtopolo.service.Impl;

import mk.ukim.finki.avtopolo.model.User;
import mk.ukim.finki.avtopolo.model.exceptions.InvalidArgumentException;
import mk.ukim.finki.avtopolo.model.exceptions.InvalidUserCredentialsException;
import mk.ukim.finki.avtopolo.repository.UserRepository;
import mk.ukim.finki.avtopolo.service.AuthService;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User login(String username, String password) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw new InvalidArgumentException();
        }
        return userRepository.findByUsernameAndPassword(username, password)
                .orElseThrow(InvalidUserCredentialsException::new);
    }
}
