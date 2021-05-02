package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reviews.main.domain.Cliente;
import reviews.main.dto.ClienteDTO;
import reviews.main.repositories.ClienteRepository;
import reviews.main.services.exceptions.DataIntegrityException;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

  @Autowired
  private ClienteRepository repository;

  public Cliente find(Integer id) {
    Optional<Cliente> result = this.repository.findById(id);
    return result.orElseThrow(
        () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getSimpleName())
    );
  }

  public void update(Cliente obj) {
    Cliente newObj = this.find(obj.getId());
    this.updateData(newObj, obj);
    this.repository.save(newObj);
  }

  public void delete(Integer id) {
    this.find(id);
    try {
      this.repository.deleteById(id);
    } catch (DataIntegrityViolationException exception) {
      throw new DataIntegrityException("Não é possível excluir porque há entidades relacionadas!");
    }
  }

  public List<Cliente> findAll() {
    return this.repository.findAll();
  }

  public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    return repository.findAll(pageRequest);
  }

  public Cliente fromDTO(ClienteDTO objDTO) {
    return new Cliente(objDTO.getId(), objDTO.getNome(), objDTO.getEmail(), null, null);
  }

  private void updateData(Cliente newObj, Cliente obj) {
    newObj.setNome(obj.getNome());
    newObj.setEmail(obj.getEmail());
  }
}
