package com.udemy.curso.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.udemy.curso.domain.enums.EstadoPagamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@Entity
public class PagamentoComBoleto extends Pagamento {
	private static final long serialVersionUID = 1L;
	
	private Date dataPagamento;
	private Date dataVencimento;
	
	
	public PagamentoComBoleto(Integer id, EstadoPagamento estado, Pedido pedido, Date dataVencimento, Date dataPagamento) {
		super(id, estado, pedido);
		
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
	
	

}
