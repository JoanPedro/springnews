package reviews.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reviews.main.domain.Categoria;
import reviews.main.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> { }
