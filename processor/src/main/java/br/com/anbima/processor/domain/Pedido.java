package br.com.anbima.processor.domain;

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
  public String getProteina() { return proteina; }
  public String getAcompanhamento() { return acompanhamento; }
  public Integer getQuantidade() { return quantidade; }
  public String getBebida() { return bebida; }
  public BigDecimal getValor() { return valor; }
  public PedidoStatus getStatus() { return status; }
  public OffsetDateTime getCriadoEm() { return criadoEm; }
  public void setStatus(PedidoStatus s) { this.status = s; }
}
