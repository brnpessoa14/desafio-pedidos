package br.com.anbima.gateway.service;

import br.com.anbima.gateway.domain.*;
import br.com.anbima.gateway.messaging.PedidoPublisher;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

@Service
public class PedidoService {

  private final PedidoRepository repo;
  private final PedidoPublisher publisher;
  private final PricingService pricing = new PricingService();

  public PedidoService(PedidoRepository repo, PedidoPublisher publisher) {
    this.repo = repo;
    this.publisher = publisher;
  }

  public Pedido criarDeLinha(String linha) {
    var c = PositionalParser.parse(linha);

    BigDecimal valor = pricing.calcular(c.tipoLanche(), c.proteina(), c.acompanhamento(), c.quantidade(), c.bebida());

    Pedido p = new Pedido();
    p.setTipoLanche(c.tipoLanche());
    p.setProteina(c.proteina());
    p.setAcompanhamento(c.acompanhamento());
    p.setQuantidade(c.quantidade());
    p.setBebida(c.bebida());
    p.setValor(valor);
    p.setStatus(PedidoStatus.RECEBIDO);
    p.setCriadoEm(OffsetDateTime.now());

    Pedido salvo = repo.save(p);
    publisher.publicar(salvo.getId());
    return salvo;
  }
}
