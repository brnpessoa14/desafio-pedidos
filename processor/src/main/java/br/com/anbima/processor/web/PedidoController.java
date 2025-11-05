package br.com.anbima.processor.web;

import br.com.anbima.processor.domain.Pedido;
import br.com.anbima.processor.domain.PedidoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

  private final PedidoRepository repo;

  public PedidoController(PedidoRepository repo) {
    this.repo = repo;
  }

  @GetMapping
  public List<Pedido> listar() {
    return repo.findAll();
  }

  @GetMapping("/{id}")
  public Pedido obter(@PathVariable Long id) {
    return repo.findById(id).orElseThrow();
  }
}
