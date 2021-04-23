package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import reviews.main.domain.Categoria;
import reviews.main.dto.CategoriaDTO;
import reviews.main.services.CategoriaService;

import javax.websocket.server.PathParam;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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
    Categoria resultObj = this.service.insert(obj);
    /* Created Status Code: 201. Implies that:
      Pattern that returns an URI that point to the new object. */
    URI uri = ServletUriComponentsBuilder
        .fromCurrentRequest()
        .path("/{id}")
        .buildAndExpand(resultObj.getId())
        .toUri();
    return ResponseEntity.created(uri).build();
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@PathVariable Integer id, @RequestBody Categoria obj) {
    // Ensure update obj by id. Case id is null, its perform an create!
    obj.setId(id);
    this.service.update(obj);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    this.service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping()
  public ResponseEntity<List<CategoriaDTO>> findAll() {
    List<Categoria> result = this.service.findAll();
    List<CategoriaDTO> listDto = result.stream().map(CategoriaDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok(listDto);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<CategoriaDTO>> findPage(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    Page<Categoria> result = this.service.findPage(page, linesPerPage, orderBy, direction);
    Page<CategoriaDTO> listDto = result.map(CategoriaDTO::new);
    return ResponseEntity.ok(listDto);
  }
}
