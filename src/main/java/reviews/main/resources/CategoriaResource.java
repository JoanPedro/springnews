package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reviews.main.domain.Categoria;
import reviews.main.services.CategoriaService;

import java.net.URI;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {

  @Autowired
  private CategoriaService service;

  @GetMapping(value = "/{id}")
  public ResponseEntity<Categoria> find(@PathVariable Integer id) {
    Categoria result = this.service.find(id);
    return ResponseEntity.ok(result);
  }

  @PostMapping()
  public ResponseEntity<Void> insert(@RequestBody Categoria obj) {
    Categoria resultObj = service.insert(obj);
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
