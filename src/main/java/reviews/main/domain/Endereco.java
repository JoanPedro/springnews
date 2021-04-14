package reviews.main.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table
public class Endereco implements Serializable {
  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column
  private String logradouro;

  @Column
  private String numero;

  @Column
  private String complemento;

  @Column
  private String bairro;

  @Column
  private String cep;

  @JsonBackReference
  @ManyToOne
  @JoinColumn(name="client_id")
  private Cliente cliente;

  @ManyToOne
  @JoinColumn(name="cidade_id")
  private Cidade cidade;

  public Endereco() {
  }

  public Endereco(Integer id, String logradouro, String numero, String complemento, String bairro, String cep, Cliente cliente, Cidade cidade) {
    this.id = id;
    this.logradouro = logradouro;
    this.numero = numero;
    this.complemento = complemento;
    this.bairro = bairro;
    this.cep = cep;
    this.cliente = cliente;
    this.cidade = cidade;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getLogradouro() {
    return logradouro;
  }

  public void setLogradouro(String logradouro) {
    this.logradouro = logradouro;
  }

  public String getNumero() {
    return numero;
  }

  public void setNumero(String numero) {
    this.numero = numero;
  }

  public String getComplemento() {
    return complemento;
  }

  public void setComplemento(String complemento) {
    this.complemento = complemento;
  }

  public String getBairro() {
    return bairro;
  }

  public void setBairro(String bairro) {
    this.bairro = bairro;
  }

  public String getCep() {
    return cep;
  }

  public void setCep(String cep) {
    this.cep = cep;
  }

  public Cliente getCliente() {
    return cliente;
  }

  public void setCliente(Cliente cliete) {
    this.cliente = cliete;
  }

  public Cidade getCidade() {
    return cidade;
  }

  public void setCidade(Cidade cidade) {
    this.cidade = cidade;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Endereco endereco = (Endereco) o;
    return id.equals(endereco.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }
}