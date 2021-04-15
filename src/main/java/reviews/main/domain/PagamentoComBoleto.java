package reviews.main.domain;

import reviews.main.domain.enums.EstadoPagamento;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;

@Entity
@Table
public class PagamentoComBoleto extends Pagamento {
  private static final long serialVersionUID = 1L;

  @Column
  private Date dataVencimento;

  @Column
  private Date dataPagamento;

  public PagamentoComBoleto() {}

  public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
    super(id, estado, pedido);
    this.dataPagamento = dataPagamento;
    this.dataVencimento = dataVencimento;
  }

  public Date getDataVencimento() {
    return dataVencimento;
  }

  public void setDataVencimento(Date dataVencimento) {
    this.dataVencimento = dataVencimento;
  }

  public Date getDataPagamento() {
    return dataPagamento;
  }

  public void setDataPagamento(Date dataPagamento) {
    this.dataPagamento = dataPagamento;
  }
}
