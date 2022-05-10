package mk.ukim.finki.avtopolo.service.Impl;

import mk.ukim.finki.avtopolo.model.User;
import mk.ukim.finki.avtopolo.model.enums.Role;
import mk.ukim.finki.avtopolo.model.exceptions.InvalidUsernameOrPasswordException;
import mk.ukim.finki.avtopolo.model.exceptions.PasswordsDoNotMatchException;
import mk.ukim.finki.avtopolo.model.exceptions.UsernameExistsException;
import mk.ukim.finki.avtopolo.repository.UserRepository;
import mk.ukim.finki.avtopolo.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(String username, String password, String repeatPass, String name, String surname, Role role) {
        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            throw  new InvalidUsernameOrPasswordException();
        }
        if (!password.equals(repeatPass)) {
            throw new PasswordsDoNotMatchException();
        }

        if (this.userRepository.findByUsername(username).isPresent()) {
            throw new UsernameExistsException(username);
        }

        User user = new User(username, passwordEncoder.encode(password), name, surname, role);
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));
    }
}
