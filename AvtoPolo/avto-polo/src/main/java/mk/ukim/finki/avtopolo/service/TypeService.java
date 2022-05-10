package mk.ukim.finki.avtopolo.service;

import mk.ukim.finki.avtopolo.model.Type;

import java.util.List;
import java.util.Optional;

public interface TypeService{

    List<Type> findAll();

    Type create(String name, String description, String imageUrl);

    Type update(String name, String description, String imageUrl);

    Optional<Type> findById(Long id);

    Optional<Type> save(String name, String description, String imageUrl);
}
