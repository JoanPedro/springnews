package reviews.main.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reviews.main.domain.Cliente;
import reviews.main.dto.ClienteDTO;
import reviews.main.services.ClienteService;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/clients")
public class ClienteResource {

  @Autowired
  private ClienteService service;

  @CrossOrigin(origins = "http://localhost:8080")
  @GetMapping(value = "/{id}")
  public ResponseEntity<Cliente> find(@PathVariable Integer id) {
    Cliente result = this.service.find(id);
    return ResponseEntity.ok(result);
  }

  @PutMapping(value = "/{id}")
  public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO objDTO, @PathVariable Integer id) {
    Cliente objMapper = this.service.fromDTO(objDTO);
    // Ensure update obj by id. Case id is null, its perform an create!
    objMapper.setId(id);
    this.service.update(objMapper);
    return ResponseEntity.noContent().build();
  }

  @DeleteMapping(value = "/{id}")
  public ResponseEntity<Void> delete(@PathVariable Integer id) {
    this.service.delete(id);
    return ResponseEntity.noContent().build();
  }

  @GetMapping()
  public ResponseEntity<List<ClienteDTO>> findAll() {
    List<Cliente> result = this.service.findAll();
    List<ClienteDTO> listDto = result.stream().map(ClienteDTO::new).collect(Collectors.toList());
    return ResponseEntity.ok(listDto);
  }

  @GetMapping(value = "/page")
  public ResponseEntity<Page<ClienteDTO>> findPage(
      @RequestParam(value = "page", defaultValue = "0") Integer page,
      @RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
      @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
      @RequestParam(value = "direction", defaultValue = "ASC") String direction
  ) {
    Page<Cliente> result = this.service.findPage(page, linesPerPage, orderBy, direction);
    Page<ClienteDTO> listDto = result.map(ClienteDTO::new);
    return ResponseEntity.ok(listDto);
  }
}
