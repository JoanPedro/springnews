package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviews.main.domain.Categoria;
import reviews.main.repositories.CategoriaRepository;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class CategoriaService {

  @Autowired
  private CategoriaRepository repository;

  public Categoria find(Integer id) {
    Optional<Categoria> result = this.repository.findById(id);
    return result.orElseThrow(
        () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getSimpleName())
    );
  }

  public Categoria insert(Categoria obj) {
    // Ensure new obj by id. Case id not null, its perform an update!
    obj.setId(null);
    return this.repository.save(obj);
  }

  public void update(Categoria obj) {
    this.find(obj.getId());
    this.repository.save(obj);
  }

  public void delete(Integer id) {
    this.find(id);
    this.repository.deleteById(id);
  }
}
