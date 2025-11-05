package br.com.anbima.gateway.domain;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Entity
@Table(name = "pedidos")
public class Pedido {
  @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(length = 10, nullable = false)
  private String tipoLanche;

  @Column(length = 10, nullable = false)
  private String proteina;

  @Column(length = 10, nullable = false)
  private String acompanhamento;

  @Column(nullable = false)
  private Integer quantidade;

  @Column(length = 8, nullable = false)
  private String bebida;

  @Column(precision = 12, scale = 2, nullable = false)
  private BigDecimal valor;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private PedidoStatus status;

  @Column(nullable = false)
  private OffsetDateTime criadoEm;

  public Long getId() { return id; }
  public String getTipoLanche() { return tipoLanche; }
  public void setTipoLanche(String v) { this.tipoLanche = v; }
  public String getProteina() { return proteina; }
  public void setProteina(String v) { this.proteina = v; }
  public String getAcompanhamento() { return acompanhamento; }
  public void setAcompanhamento(String v) { this.acompanhamento = v; }
  public Integer getQuantidade() { return quantidade; }
  public void setQuantidade(Integer v) { this.quantidade = v; }
  public String getBebida() { return bebida; }
  public void setBebida(String v) { this.bebida = v; }
  public BigDecimal getValor() { return valor; }
  public void setValor(BigDecimal v) { this.valor = v; }
  public PedidoStatus getStatus() { return status; }
  public void setStatus(PedidoStatus v) { this.status = v; }
  public OffsetDateTime getCriadoEm() { return criadoEm; }
  public void setCriadoEm(OffsetDateTime v) { this.criadoEm = v; }
}
