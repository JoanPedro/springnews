package reviews.main.domain.enums;

import java.util.Arrays;

public enum TipoCliente {
  PESSOAFISICA(1, "Pessoa Física"),
  PESSOAJURIDICA(2, "Pessoa Jurídica");

  private final int cod;
  private final String descricao;

  TipoCliente(int cod, String descricao) {
    this.cod = cod;
    this.descricao = descricao;
  }

  public int getCod() {
    return cod;
  }

  public String getDescricao() {
    return descricao;
  }

  public static TipoCliente toEnum(Integer cod) {
    if(cod == null) return null;

    return Arrays.stream(TipoCliente.values())
        .filter(tipoCliente -> cod.equals(tipoCliente.getCod()))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException("Id inválido: " + cod));

  }
}
