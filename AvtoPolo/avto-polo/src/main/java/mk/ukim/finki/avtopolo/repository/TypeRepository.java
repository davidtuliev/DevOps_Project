package mk.ukim.finki.avtopolo.repository;

import mk.ukim.finki.avtopolo.model.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeRepository extends JpaRepository<Type, Long> {

    void deleteByName(String name);
}
