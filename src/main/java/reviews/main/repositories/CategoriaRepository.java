package reviews.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reviews.main.domain.Categoria;

@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Integer> { }
