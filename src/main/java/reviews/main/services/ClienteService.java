package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviews.main.domain.Cliente;
import reviews.main.repositories.ClienteRepository;
import reviews.main.services.exceptions.ObjectNotFoundException;

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
}
