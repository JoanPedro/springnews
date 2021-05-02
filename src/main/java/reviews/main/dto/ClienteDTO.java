package reviews.main.dto;

import reviews.main.domain.Cliente;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;

public class ClienteDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;

  @NotEmpty(message = "Preenchimento obrigatório!")
  @Size(min = 5, max = 120, message = "O nome inserido deve conter entre 5 a 120 caracteres!")
  private String nome;

  @NotEmpty(message = "Preenchimento obrigatório!")
  @Email(message = "Email inválido!")
  private String email;

  public ClienteDTO(Integer id, String nome, String email) {
    this.id = id;
    this.nome = nome;
    this.email = email;
  }

  public ClienteDTO() {
  }

  public ClienteDTO(Cliente obj) {
    this.id = obj.getId();
    this.nome = obj.getNome();
    this.email = obj.getEmail();
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

  public void setNome(String name) {
    this.nome = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
