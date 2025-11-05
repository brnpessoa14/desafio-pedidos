package br.com.anbima.processor.messaging;

import br.com.anbima.processor.domain.Pedido;
import br.com.anbima.processor.domain.PedidoRepository;
import br.com.anbima.processor.domain.PedidoStatus;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class PedidoListener {

  private final PedidoRepository repo;

  public PedidoListener(PedidoRepository repo) {
    this.repo = repo;
  }

  @RabbitListener(queues = "#{pedidosRecebidos.name}")
  public void consumir(@Payload Map<String, Object> msg) {
    Object idObj = msg.get("pedidoId");
    if (idObj == null) return;

    Long id = Long.valueOf(idObj.toString());
    repo.findById(id).ifPresent(p -> {
      p.setStatus(PedidoStatus.ENTREGUE);
      repo.save(p);
    });
  }
}
