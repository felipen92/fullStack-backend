package com.udemy.curso.domain;

import java.io.Serializable;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.udemy.curso.domain.enums.EstadoPagamento;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "@type")
public abstract class Pagamento implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	private Integer id;
	
	private Integer estado;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name="pedido_id")
	@MapsId
	private Pedido pedido;
	
	public Pagamento (Integer id, EstadoPagamento estado, Pedido pedido) {
		this.id = id;
		this.estado = estado == null ? null : estado.getCod();
		this.pedido = pedido;
	}
	

	public void setEstadoPagamento(EstadoPagamento estado) {
		this.estado = estado.getCod();
	}
	
	public EstadoPagamento getEstadoPagamento() {
		return EstadoPagamento.toEnum(estado);
	}
}
