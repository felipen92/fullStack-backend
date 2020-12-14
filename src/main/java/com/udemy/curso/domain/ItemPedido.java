package com.udemy.curso.domain;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@Getter @Setter
@EqualsAndHashCode
public class ItemPedido implements Serializable{

	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@EmbeddedId
	private ItemPedidoPK id = new ItemPedidoPK();
	
	private double desconto;
	private Integer quantidade;
	private double preco;
	
	public ItemPedido(Pedido pedido, Produto produto, double desconto, Integer quantidade, double preco) {
		id.setPedido(pedido);
		id.setProduto(produto);
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
	}
	
	public double getSubTotal() {
		return (preco - desconto) * quantidade;
	}
	
	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}
	

	public Produto getProduto() {
		return id.getProduto();
	}
	
}

