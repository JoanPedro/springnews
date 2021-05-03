package reviews.main.domain;

import com.fasterxml.jackson.annotation.JsonTypeName;
import reviews.main.domain.enums.EstadoPagamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@JsonTypeName("pagamentoComCartao")
public class PagamentoComCartao extends Pagamento {
  private static final long serialVersionUID = 1L;

  @Column
  private Integer numeroDeParcelas;

  public PagamentoComCartao() {
  }

  public PagamentoComCartao(Integer id, EstadoPagamento estado, Pedido pedido, Integer numeroDeParcelas) {
    super(id, estado, pedido);
    this.numeroDeParcelas = numeroDeParcelas;
  }

  public Integer getNumeroDeParcelas() {
    return numeroDeParcelas;
  }

  public void setNumeroDeParcelas(Integer numeroDeParcelas) {
    this.numeroDeParcelas = numeroDeParcelas;
  }
}
