package br.com.rocha.API_Pedido.service;

import br.com.rocha.API_Pedido.domain.entity.Cliente;
import br.com.rocha.API_Pedido.domain.entity.PagamentoComBoleto;
import br.com.rocha.API_Pedido.domain.entity.Pedido;
import br.com.rocha.API_Pedido.domain.enums.EstadoPagamento;
import br.com.rocha.API_Pedido.domain.repository.ItemPedidoRepository;
import br.com.rocha.API_Pedido.domain.repository.PedidoRepository;
import br.com.rocha.API_Pedido.exception.AuthorizationException;
import br.com.rocha.API_Pedido.exception.ObjectNotFoundException;
import br.com.rocha.API_Pedido.security.UserSS;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private BoletoService boletoService;

    @Autowired
    private ClienteService clienteService;

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private PagamentoRepository pagamentoRepository;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private EmailService emailService;

    public Pedido findById(Integer id) {
        Optional<Pedido> pedido = pedidoRepository.findById(id);
        return pedido.orElseThrow(() -> new ObjectNotFoundException("Objeto Não Encontrado! id: "
                + id + " tipo: " + Pedido.class.getName()));
    }

    @Transactional
    public Pedido insert(Pedido pedido) {
        pedido.setId(null);
        pedido.setInstante(new Date());
        pedido.setCliente(clienteService.find(pedido.getCliente().getId()));
        pedido.getPagamento().setEstado(EstadoPagamento.PENDENTE);
        pedido.getPagamento().setPedido(pedido);

        if (pedido.getPagamento() instanceof PagamentoComBoleto) {
            PagamentoComBoleto pagto = (PagamentoComBoleto) pedido.getPagamento();
            boletoService.preencherPagamentoComBoleto(pagto, pedido.getInstante());
        }

        pedido = pedidoRepository.save(pedido);
        pagamentoRepository.save(pedido.getPagamento());

        Pedido finalPedido = pedido;
        pedido.getItens().forEach(ip -> {
            ip.setDesconto(0.00);
            ip.setProduto(produtoService.find(ip.getProduto().getId()));
            ip.setPreco(ip.getProduto().getPreco());
            ip.setPedido(finalPedido);
        });

        itemPedidoRepository.saveAll(pedido.getItens());

        emailService.sendOrderConfirmationEmail(pedido);

        // emailService.sendOrderconfirmationHtmlEmail(pedido);

        return pedido;
    }

    public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
        UserSS user = UserService.authenticated();

        if (user == null) {
            throw new AuthorizationException("Acesso Negado");
        }

        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Sort.Direction.valueOf(direction), orderBy);
        Cliente cliente = clienteService.find(user.getId());

        return pedidoRepository.findByCliente(cliente, pageRequest);
    }

}