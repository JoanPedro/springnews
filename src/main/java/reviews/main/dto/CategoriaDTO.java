package reviews.main.dto;

import reviews.main.domain.Categoria;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class CategoriaDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty(message = "Preenchimento obrigatório!")
  @Size(min = 5, max = 80, message = "O nome inserido deve conter entre 5 a 80 caracteres!")
  private String nome;

  public CategoriaDTO() {
  }

  public CategoriaDTO(Integer id, String nome) {
    this.id = id;
    this.nome = nome;
  }

  public CategoriaDTO(Categoria categoria) {
    this.id = categoria.getId();
    this.nome = categoria.getNome();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getNome() {
    return nome;
  }

  public void setNome(String nome) {
    this.nome = nome;
  }
}
