package mk.ukim.finki.avtopolo.repository;

import mk.ukim.finki.avtopolo.model.Automobile;
import mk.ukim.finki.avtopolo.model.Brand;
import mk.ukim.finki.avtopolo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AutomobileRepository extends JpaRepository<Automobile, Long> {

    void deleteByName(String name);

    List<Automobile> findAllByBrand(Brand brand);

    List<Automobile> findAllByType(Type type);

}
