package com.udemy.curso.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.udemy.curso.domain.Categoria;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CategoriaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	@Length(min = 5, max = 80, message = "O TAMANHO DEVE SER ENTRE 5 E 80 CARACTERES" )
	private String nome;
	
	public CategoriaDTO(Categoria cat) {
		this.id = cat.getId();
		this.nome = cat.getNome();
	}

}
