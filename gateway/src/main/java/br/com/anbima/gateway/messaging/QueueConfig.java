package br.com.anbima.gateway.messaging;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QueueConfig {
  @Bean
  public Queue pedidosRecebidos(@Value("${app.queue.pedidosRecebidos}") String nome) {
    return new Queue(nome, true);
  }
}
