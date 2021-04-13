package reviews.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reviews.main.domain.Categoria;
import reviews.main.domain.Endereco;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer> { }
