package br.com.rocha.API_Pedido.domain.repository;

import br.com.rocha.API_Pedido.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

}
