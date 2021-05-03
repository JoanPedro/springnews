package reviews.main.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import reviews.main.domain.Categoria;
import reviews.main.domain.Pedido;
import reviews.main.domain.Produto;
import reviews.main.repositories.CategoriaRepository;
import reviews.main.repositories.ProdutoRepository;
import reviews.main.services.exceptions.ObjectNotFoundException;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoService {

  @Autowired
  private ProdutoRepository repository;

  @Autowired
  private CategoriaRepository categoriaRepository;

  public Produto find(Integer id) {
    Optional<Produto> result = this.repository.findById(id);
    return result.orElseThrow(
        () -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getSimpleName())
    );
  }

  public Page<Produto> search(String nome, List<Integer> ids, Integer page, Integer linesPerPage, String orderBy, String direction) {
    PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
    List<Categoria> categorias = this.categoriaRepository.findAllById(ids);
    return this.repository.search(nome, categorias, pageRequest);
  }
}
