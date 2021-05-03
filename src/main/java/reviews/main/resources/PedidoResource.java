package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reviews.main.domain.Pedido;
import reviews.main.services.PedidoService;

import javax.validation.Valid;
import java.net.URI;

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

  @PostMapping()
  public ResponseEntity<Void> insert(@RequestBody Pedido obj) {
    Pedido resultObj = this.service.insert(obj);
    /* Created Status Code: 201. Implies that:
      Pattern that returns an URI that point to the new object. */
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(resultObj.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }
}
