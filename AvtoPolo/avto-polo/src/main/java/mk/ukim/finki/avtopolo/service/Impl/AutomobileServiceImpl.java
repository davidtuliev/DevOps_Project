package mk.ukim.finki.avtopolo.service.Impl;

import mk.ukim.finki.avtopolo.model.Automobile;
import mk.ukim.finki.avtopolo.model.Brand;
import mk.ukim.finki.avtopolo.model.Type;
import mk.ukim.finki.avtopolo.model.exceptions.AutomobileNotFoundException;
import mk.ukim.finki.avtopolo.model.exceptions.BrandNotFoundException;
import mk.ukim.finki.avtopolo.model.exceptions.TypeNotFoundException;
import mk.ukim.finki.avtopolo.repository.AutomobileRepository;
import mk.ukim.finki.avtopolo.repository.BrandRepository;
import mk.ukim.finki.avtopolo.repository.TypeRepository;
import mk.ukim.finki.avtopolo.service.AutomobileService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutomobileServiceImpl implements AutomobileService {

    private final AutomobileRepository automobileRepository;
    private final TypeRepository typeRepository;
    private final BrandRepository brandRepository;

    public AutomobileServiceImpl(AutomobileRepository automobileRepository, TypeRepository typeRepository, BrandRepository brandRepository) {
        this.automobileRepository = automobileRepository;
        this.typeRepository = typeRepository;
        this.brandRepository = brandRepository;
    }

    @Override
    public List<Automobile> findAll() {
        return this.automobileRepository.findAll();
    }

    @Override
    public Optional<Automobile> findById(Long id) {
        return this.automobileRepository.findById(id);
    }

    @Override
    public Optional<Automobile> save(String name, Double price, String sellingLocation,
                                     String imageUrl, Long typeId, Long brandId) {
        Type type = this.typeRepository.findById(typeId)
                .orElseThrow(() -> new TypeNotFoundException(typeId));
        Brand brand = this.brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId));

        this.automobileRepository.deleteByName(name);
        Automobile automobile = new Automobile(name, price, sellingLocation, imageUrl, type, brand);
        return Optional.of(this.automobileRepository.save(automobile));
    }

    @Override
    public Optional<Automobile> edit(Long id, String name, Double price, String sellingLocation,
                                     String imageURL, Long typeId, Long brandId) {
        Automobile automobile = this.automobileRepository.findById(id)
                .orElseThrow(() -> new AutomobileNotFoundException(id));

        automobile.setName(name);
        automobile.setPrice(price);
        automobile.setSellingLocation(sellingLocation);
        automobile.setImageUrl(imageURL);

        Type type = this.typeRepository.findById(typeId)
                .orElseThrow(() -> new TypeNotFoundException(typeId));
        automobile.setType(type);

        Brand brand = this.brandRepository.findById(brandId)
                .orElseThrow(() -> new BrandNotFoundException(brandId));
        automobile.setBrand(brand);

        return Optional.of(this.automobileRepository.save(automobile));
    }

    @Override
    public void deleteById(Long id) {
        this.automobileRepository.deleteById(id);
    }

    @Override
    public List<Automobile> listAutomobilesByBrandAndType(Long brandId, Long typeId) {
        Brand brand = brandId != null ? this.brandRepository.findById(brandId).orElse(null) : null;
        Type type = typeId != null ? this.typeRepository.findById(typeId).orElse(null) : null;
        if (brand != null) {
            return this.automobileRepository.findAllByBrand(brand);
        } else if (type != null) {
            return this.automobileRepository.findAllByType(type);
        } else  {
           return this.automobileRepository.findAll();
       }
    }

    @Override
    public Page<Automobile> paginate(int pageNo, int pageSize) {

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return this.automobileRepository.findAll(pageable);
    }

}
