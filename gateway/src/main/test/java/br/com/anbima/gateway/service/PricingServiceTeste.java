package br.com.anbima.gateway.service;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricingServiceTest {

  @Test
  void aplicaDescontoDezPorCentoParaHamburguerCarneSalada() {
    PricingService svc = new PricingService();
    BigDecimal v = svc.calcular("HAMBURGUER", "CARNE", "SALADA", 1, "COCA");
    // base 20 + COCA 5 = 25   com 10% de desconto = 22.50
    assertEquals(new BigDecimal("22.50"), v);
  }

  @Test
  void semDescontoParaOutraCombinacao() {
    PricingService svc = new PricingService();
    BigDecimal v = svc.calcular("PASTEL", "FRANGO", "BACON", 2, "SUCO");
    // base 20 + SUCO 4 = 24   quantidade 2 = 48
    assertEquals(new BigDecimal("48.00"), v);
  }
}
