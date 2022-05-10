package mk.ukim.finki.avtopolo.service;

import mk.ukim.finki.avtopolo.model.Brand;

import java.util.List;
import java.util.Optional;

public interface BrandService {

    List<Brand> findAll();

    Optional<Brand> findById(Long id);

    Optional<Brand> save(String name, String country, String imageUrl);

    void deleteById(Long id);

}
