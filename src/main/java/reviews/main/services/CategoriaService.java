package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviews.main.domain.Categoria;
import reviews.main.repositories.CategoriaRepository;

import java.util.Optional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  public Optional<Categoria> buscar(Integer id) {
    return this.repository.findById(id);
  }
}
