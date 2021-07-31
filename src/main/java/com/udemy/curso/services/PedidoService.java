package com.udemy.curso.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udemy.curso.domain.ItemPedido;
import com.udemy.curso.domain.PagamentoComBoleto;
import com.udemy.curso.domain.Pedido;
import com.udemy.curso.domain.enums.EstadoPagamento;
import com.udemy.curso.repositories.ItemPedidoRepository;
import com.udemy.curso.repositories.PagamentoRepository;
import com.udemy.curso.repositories.PedidoRepository;
import com.udemy.curso.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private PedidoRepository repo;
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoRepository PagRepo;
	
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService cliService;
	
	@Autowired
	private EmailService emailService;
	
	
	public Pedido findById(Integer id) {
		
		Optional<Pedido> obj = repo.findById(id);
		
		return obj.orElseThrow(() -> 
		new ObjectNotFoundException("Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));
	}

	@Transactional
	public Pedido insert (Pedido ped) {
		ped.setId(null);
		ped.setDataPedido(new Date());
		ped.setCliente(cliService.findById(ped.getCliente().getId()));
		ped.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		ped.getPagamento().setPedido(ped);
		
		if(ped.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto pagt = (PagamentoComBoleto) ped.getPagamento();
			boletoService.preencherPagamentoComBoleto(pagt, ped.getDataPedido());
		}
		
		ped = repo.save(ped);
		
		PagRepo.save(ped.getPagamento());
		
		for(ItemPedido ip : ped.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.findById(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(ped);
		}
		
		itemPedidoRepository.saveAll(ped.getItens());
		emailService.sendOrderConfirmationEmail(ped);
		
		return ped;
	}
}
