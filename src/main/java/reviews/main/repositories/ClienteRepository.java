package reviews.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import reviews.main.domain.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

  // Automatic SQL Generation by name convention: find + by + field
  @Transactional(readOnly = true)
  Cliente findByEmail(String email);
}
