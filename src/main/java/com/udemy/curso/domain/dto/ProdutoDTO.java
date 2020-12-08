package com.udemy.curso.domain.dto;

import com.fasterxml.jackson.databind.ser.std.SerializableSerializer;
import com.udemy.curso.domain.Produto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProdutoDTO extends SerializableSerializer {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	private String nome;
	
	private Double preco;
	
	public ProdutoDTO(Produto obj) {
		id = obj.getId();
		nome = obj.getNome();
		preco = obj.getPreco();
	}

}
