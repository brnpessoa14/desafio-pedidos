package br.com.anbima.gateway.service;

public final class PositionalParser {

  public record Campos(String tipoLanche, String proteina, String acompanhamento,
                       int quantidade, String bebida) {}

  public static Campos parse(String linha) {
    if (linha == null) throw new IllegalArgumentException("linha nula");
    if (linha.length() != 40) throw new IllegalArgumentException("comprimento deve ser 40");

    String tipo = rtrim(linha.substring(0, 10));
    String prot = rtrim(linha.substring(10, 20));
    String acomp = rtrim(linha.substring(20, 30));
    String qtdStr = linha.substring(30, 32);
    String beb = rtrim(linha.substring(32, 40));

    if (tipo.isBlank() || prot.isBlank() || acomp.isBlank() || beb.isBlank())
      throw new IllegalArgumentException("campos alfanuméricos não podem ficar em branco");

    if (!qtdStr.matches("\\d{2}"))
      throw new IllegalArgumentException("quantidade deve ser NN entre 01 e 99");

    int qtd = Integer.parseInt(qtdStr);
    if (qtd < 1 || qtd > 99)
      throw new IllegalArgumentException("quantidade fora do intervalo");

    return new Campos(tipo, prot, acomp, qtd, beb);
  }

  private static String rtrim(String s) {
    int i = s.length() - 1;
    while (i >= 0 && s.charAt(i) == ' ') i--;
    return s.substring(0, i + 1);
  }
}
