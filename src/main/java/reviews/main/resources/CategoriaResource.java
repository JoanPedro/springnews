package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reviews.main.domain.Categoria;
import reviews.main.services.CategoriaService;

import java.util.Optional;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Categoria> find(@PathVariable Integer id) {
    Optional<Categoria> result = this.service.buscar(id);
    return ResponseEntity.of(result);
  }
}
