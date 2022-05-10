package mk.ukim.finki.avtopolo.service;

import mk.ukim.finki.avtopolo.model.User;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    User login(String username, String password);
}
