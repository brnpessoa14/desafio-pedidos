package br.com.anbima.gateway.web;

import br.com.anbima.gateway.domain.Pedido;
import br.com.anbima.gateway.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoService service;

  public PedidoController(PedidoService service) {
    this.service = service;
  }

  @PostMapping(path = "/posicional", consumes = MediaType.TEXT_PLAIN_VALUE)
  @ResponseStatus(HttpStatus.CREATED)
  public Pedido criar(@RequestBody String linha) {
    return service.criarDeLinha(linha);
  }
}
