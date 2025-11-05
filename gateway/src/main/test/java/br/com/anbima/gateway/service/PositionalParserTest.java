package br.com.anbima.gateway.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PositionalParserTest {

  @Test
  void deveParsearLinhaValida() {
    String linha = "HAMBURGUER"   // 10
                 + "CARNE     "   // 10
                 + "SALADA    "   // 10
                 + "01"           // 2
                 + "COCA    ";    // 8
    var c = PositionalParser.parse(linha);
    assertEquals("HAMBURGUER", c.tipoLanche());
    assertEquals("CARNE", c.proteina());
    assertEquals("SALADA", c.acompanhamento());
    assertEquals(1, c.quantidade());
    assertEquals("COCA", c.bebida());
  }

  @Test
  void deveFalharSeTamanhoDiferenteDe40() {
    String linha = "X".repeat(39);
    IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> PositionalParser.parse(linha));
    assertTrue(ex.getMessage().toLowerCase().contains("40"));
  }

  @Test
  void deveFalharSeQuantidadeNaoForNN() {
    String linha = "PASTEL    "   // 10
                 + "FRANGO    "   // 10
                 + "BACON     "   // 10
                 + "AA"           // 2 inválido
                 + "SUCO    ";    // 8
    assertThrows(IllegalArgumentException.class, () -> PositionalParser.parse(linha));
  }

  @Test
  void deveFalharSeQuantidadeForaDoIntervalo() {
    String linha = "PASTEL    "   // 10
                 + "FRANGO    "   // 10
                 + "BACON     "   // 10
                 + "00"           // 2 fora
                 + "SUCO    ";    // 8
    assertThrows(IllegalArgumentException.class, () -> PositionalParser.parse(linha));
  }
}
