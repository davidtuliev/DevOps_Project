package mk.ukim.finki.avtopolo.service;

import mk.ukim.finki.avtopolo.model.Automobile;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface AutomobileService {

    List<Automobile> findAll();

    Optional<Automobile> findById(Long id);

    Optional<Automobile> save(String name, Double price, String sellingLocation,
                              String imageURL, Long type, Long brand);

    Optional<Automobile> edit(Long id, String name, Double price, String sellingLocation,
                              String imageURL, Long type, Long brand);

    void deleteById(Long id);

    List<Automobile> listAutomobilesByBrandAndType(Long brandId, Long typeId);

    Page<Automobile> paginate(int pageNo, int pageSize);



}
