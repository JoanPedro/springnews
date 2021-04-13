package reviews.main.services;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviews.main.domain.Categoria;
import reviews.main.repositories.CategoriaRepository;

import java.util.Optional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  public Categoria find(Integer id) {
    Optional<Categoria> result = this.repository.findById(id);
    return result.orElseThrow(() -> new ObjectNotFoundException(
            Categoria.class.getName(), "Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()
        )
    );
  }
}
