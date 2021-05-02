package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import reviews.main.domain.Categoria;
import reviews.main.dto.CategoriaDTO;
import reviews.main.repositories.CategoriaRepository;
import reviews.main.services.exceptions.DataIntegrityException;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.List;
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
    Categoria newObj = this.find(obj.getId());
    this.updateData(newObj, obj);
    this.repository.save(newObj);
  }

  public void delete(Integer id) {
    this.find(id);
    try {
      this.repository.deleteById(id);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos!");
    }
  }

  public List<Categoria> findAll() {
    return this.repository.findAll();
  }

  public Page<Categoria> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
    return repository.findAll(pageRequest);
  }

  public Categoria fromDTO(CategoriaDTO objDTO) {
    return new Categoria(objDTO.getId(), objDTO.getNome());
  }

  private void updateData(Categoria newObj, Categoria obj) {
    newObj.setNome(obj.getNome());
  }
}
