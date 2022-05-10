package mk.ukim.finki.avtopolo.service;

import mk.ukim.finki.avtopolo.model.User;
import mk.ukim.finki.avtopolo.model.enums.Role;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User register(String username, String password, String repeatPass, String name, String surname, Role role);

}
