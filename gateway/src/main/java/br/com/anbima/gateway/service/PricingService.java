package br.com.anbima.gateway.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class PricingService {

  public BigDecimal calcular(String tipo, String prot, String acomp, int qtd, String bebida) {
    BigDecimal base = base(tipo, prot, acomp, bebida);
    BigDecimal total = base.multiply(BigDecimal.valueOf(qtd));

    boolean descontoCombo = tipo.equalsIgnoreCase("HAMBURGUER")
      && prot.equalsIgnoreCase("CARNE")
      && acomp.equalsIgnoreCase("SALADA");

    if (descontoCombo) {
      total = total.multiply(BigDecimal.valueOf(0.90));
    }
    return total.setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal base(String tipo, String prot, String acomp, String bebida) {
    BigDecimal valor = BigDecimal.valueOf(20.00);
    if (bebida.equalsIgnoreCase("COCA")) valor = valor.add(BigDecimal.valueOf(5.00));
    if (bebida.equalsIgnoreCase("SUCO")) valor = valor.add(BigDecimal.valueOf(4.00));
    return valor;
  }
}
