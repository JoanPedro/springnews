package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reviews.main.domain.Pedido;
import reviews.main.repositories.PedidoRepository;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.Optional;

@Service
public class PedidoService {

  @Autowired
  private PedidoRepository repository;

  public Pedido find(Integer id) {
    Optional<Pedido> result = this.repository.findById(id);
    return result.orElseThrow(
        () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getSimpleName())
    );
  }
}
