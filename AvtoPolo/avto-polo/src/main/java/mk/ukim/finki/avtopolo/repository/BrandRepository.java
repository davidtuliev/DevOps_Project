package mk.ukim.finki.avtopolo.repository;

import mk.ukim.finki.avtopolo.model.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
}
