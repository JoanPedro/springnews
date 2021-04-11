package reviews.main;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import reviews.main.domain.Categoria;
import reviews.main.repositories.CategoriaRepository;

import java.util.Arrays;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class App implements CommandLineRunner {

  @Autowired
  CategoriaRepository categoriaRepository;

  public static void main(String[] args) {
    SpringApplication.run(App.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
    Categoria informatica = new Categoria(null, "Informática");
    Categoria escritorio = new Categoria(null, "Escritorio");

    this.categoriaRepository.saveAll(Arrays.asList(informatica, escritorio));
  }
}
