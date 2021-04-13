package reviews.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import reviews.main.domain.Categoria;
import reviews.main.domain.Cidade;
import reviews.main.domain.Estado;
import reviews.main.domain.Produto;
import reviews.main.repositories.CategoriaRepository;
import reviews.main.repositories.CidadeRepository;
import reviews.main.repositories.EstadoRepository;
import reviews.main.repositories.ProdutoRepository;

import java.util.Arrays;
import java.util.Collections;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class App implements CommandLineRunner {

  @Autowired
  CategoriaRepository categoriaRepository;

  @Autowired
  ProdutoRepository produtoRepository;

  @Autowired
  EstadoRepository estadoRepository;

  @Autowired
  CidadeRepository cidadeRepository;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Categoria cat1 = new Categoria(null, "Informática");
    Categoria cat2 = new Categoria(null, "Escritorio");

    Produto p1 = new Produto(null, "Computador", 2000.00);
    Produto p2 = new Produto(null, "Impressora", 800.00);
    Produto p3 = new Produto(null, "Mouse", 80.00);

    cat1.getProdutos().addAll(Arrays.asList(p1, p2, p3));
    cat2.getProdutos().addAll(Collections.singletonList(p2));

    p1.getCategorias().addAll(Collections.singletonList(cat1));
    p2.getCategorias().addAll(Arrays.asList(cat1, cat2));
    p3.getCategorias().addAll(Collections.singletonList(cat1));

    this.categoriaRepository.saveAll(Arrays.asList(cat1, cat2));
    this.produtoRepository.saveAll(Arrays.asList(p1, p2, p3));


    Estado est1 = new Estado(null, "Minas Gerais");
    Estado est2 = new Estado(null, "São Paulo");

    Cidade c1 = new Cidade(null, "Uberlândia", est1);
    Cidade c2 = new Cidade(null, "São Paulo", est2);
    Cidade c3 = new Cidade(null, "Campinas", est2);

    est1.getCidades().addAll(Collections.singletonList(c1));
    est2.getCidades().addAll(Arrays.asList(c2, c3));

    this.estadoRepository.saveAll(Arrays.asList(est1, est2));
    this.cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
  }
}
