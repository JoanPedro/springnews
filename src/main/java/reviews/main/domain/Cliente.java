package reviews.main.domain;

import reviews.main.domain.enums.TipoCliente;

import java.io.Serializable;
import java.util.*;

public class Cliente implements Serializable {
  private static final long serialVersionUID = 1L;

  private Integer id;
  private String email;
  private String cpfOuCnpj;
  private Integer tipo;

  private List<Endereco> enderecos = new ArrayList<>();
  private Set<String> telefones = new HashSet<>();

  public Cliente() {
  }

  public Cliente(Integer id, String email, String cpfOuCnpj, TipoCliente tipo) {
    this.id = id;
    this.email = email;
    this.cpfOuCnpj = cpfOuCnpj;
    this.tipo = tipo.getCod();
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getCpfOuCnpj() {
    return cpfOuCnpj;
  }

  public void setCpfOuCnpj(String cpfOuCnpj) {
    this.cpfOuCnpj = cpfOuCnpj;
  }

  public TipoCliente getTipo() {
    return TipoCliente.toEnum(tipo);
  }

  public void setTipo(TipoCliente tipo) {
    this.tipo = tipo.getCod();
  }

  public List<Endereco> getEnderecos() {
    return enderecos;
  }

  public void setEnderecos(List<Endereco> enderecos) {
    this.enderecos = enderecos;
  }

  public Set<String> getTelefones() {
    return telefones;
  }

  public void setTelefones(Set<String> telefones) {
    this.telefones = telefones;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Cliente cliente = (Cliente) o;
    return id.equals(cliente.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}
