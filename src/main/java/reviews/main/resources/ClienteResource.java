package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reviews.main.domain.Cliente;
import reviews.main.services.ClienteService;

@RestController
@RequestMapping(value = "/clients")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> find(@PathVariable Integer id) {
    Cliente result = this.service.find(id);
    return ResponseEntity.ok(result);
  }
}
