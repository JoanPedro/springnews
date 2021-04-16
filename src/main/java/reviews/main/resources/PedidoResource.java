package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.main.domain.Cliente;
import reviews.main.domain.Pedido;
import reviews.main.services.ClienteService;
import reviews.main.services.PedidoService;

@RestController
@RequestMapping(value = "/pedidos")
public class PedidoResource {

  @Autowired
  private PedidoService service;

  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping(value = "/{id}")
  public ResponseEntity<Pedido> find(@PathVariable Integer id) {
    Pedido result = this.service.find(id);
    return ResponseEntity.ok(result);
  }
}
