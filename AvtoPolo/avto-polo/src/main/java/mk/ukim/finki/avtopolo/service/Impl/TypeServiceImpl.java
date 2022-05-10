package mk.ukim.finki.avtopolo.service.Impl;

import mk.ukim.finki.avtopolo.model.Type;
import mk.ukim.finki.avtopolo.repository.TypeRepository;
import mk.ukim.finki.avtopolo.service.TypeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeServiceImpl implements TypeService {

    private final TypeRepository typeRepository;

    public TypeServiceImpl(TypeRepository typeRepository) {
        this.typeRepository = typeRepository;
    }

    @Override
    public List<Type> findAll() {
        return this.typeRepository.findAll();
    }

    @Override
    public Type create(String name, String description, String imageUrl) {
        if (name == null || name.isEmpty()) {
            throw  new IllegalArgumentException();
        }
        Type type = new Type(name, description, imageUrl);
        typeRepository.save(type);
        return type;
    }

    @Override
    public Type update(String name, String description, String imageUrl) {
        if (name == null || name.isEmpty()) {
            throw  new IllegalArgumentException();
        }
        Type type = new Type(name, description, imageUrl);
        typeRepository.save(type);
        return type;
    }

    @Override
    public Optional<Type> findById(Long id) {
        return this.typeRepository.findById(id);
    }

    @Override
    public Optional<Type> save(String name, String description, String imageUrl) {
        Type type = new Type(name, description, imageUrl);
        return Optional.of(this.typeRepository.save(type));
    }

}
