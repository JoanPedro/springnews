package reviews.main.dto;

import reviews.main.domain.Produto;

import java.io.Serializable;
import java.util.List;

public class CategoriaDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;

  private String nome;

  private List<Produto> produtos;

  public CategoriaDTO() {
  }

  public CategoriaDTO(Integer id, String nome, List<Produto> produtos) {
    this.id = id;
    this.nome = nome;
    this.produtos = produtos;
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

  public List<Produto> getProdutos() {
    return produtos;
  }

  public void setProdutos(List<Produto> produtos) {
    this.produtos = produtos;
  }
}
