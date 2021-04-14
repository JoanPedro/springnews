package reviews.main.domain.enums;

import java.util.Arrays;

public enum EstadoPagamento {
  PENDENTE(1, "Pendente"),
  QUITADO(2, "Quitado"),
  CANCELADO(3, "Cancelado");

  private final int cod;
  private final String descricao;

  EstadoPagamento(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public static EstadoPagamento toEnum(Integer cod) {
    if (cod == null) return null;

    return Arrays.stream(EstadoPagamento.values())
        .filter(estadoPagamento -> cod.equals(estadoPagamento.getCod()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));

  }

  public int getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }
}
