package br.com.anbima.gateway.messaging;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.util.Map;

@Component
public class PedidoPublisher {

  private final RabbitTemplate rabbit;
  private final String fila;

  public PedidoPublisher(RabbitTemplate rabbit,
                         @Value("${app.queue.pedidosRecebidos}") String fila) {
    this.rabbit = rabbit;
    this.fila = fila;
  }

  public void publicar(Long pedidoId) {
    rabbit.convertAndSend(fila, Map.of("pedidoId", pedidoId));
  }
}
