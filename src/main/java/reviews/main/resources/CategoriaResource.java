package reviews.main.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reviews.main.domain.Categoria;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/categories")
public class CategoriaResource {

  @GetMapping()
  public List<Categoria> listar() {
    Categoria cat = new Categoria(1, "Informática");
    Categoria cat2 = new Categoria(2, "Escritório");

    List<Categoria> catList = new ArrayList<>();
    catList.add(cat);
    catList.add(cat2);

    return catList;
  }
}
