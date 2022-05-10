package mk.ukim.finki.avtopolo.service.Impl;

import mk.ukim.finki.avtopolo.model.Brand;
import mk.ukim.finki.avtopolo.repository.BrandRepository;
import mk.ukim.finki.avtopolo.service.BrandService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BrandServiceImpl implements BrandService {

    private final BrandRepository brandRepository;

    public BrandServiceImpl(BrandRepository brandRepository) {
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Brand> findAll() {
        return this.brandRepository.findAll();
    }

    @Override
    public Optional<Brand> findById(Long id) {
        return this.brandRepository.findById(id);
    }

    @Override
    public Optional<Brand> save(String name, String country, String imageUrl) {
        Brand brand = new Brand(name, country, imageUrl);
        return Optional.of(this.brandRepository.save(brand));
    }

    @Override
    public void deleteById(Long id) {
        this.brandRepository.deleteById(id);
    }
}
