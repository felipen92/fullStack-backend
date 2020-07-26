package com.udemy.curso.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.udemy.curso.domain.Cliente;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Integer id;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	@Length(min = 5, max = 80, message = "O TAMANHO DEVE SER ENTRE 5 E 80 CARACTERES" )
	private String nome;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	@Email(message = "Email inválido!!")
	private String email;
	
	public ClienteDTO(Cliente cli) {
		this.id = cli.getId();
		this.nome = cli.getNome();
		this.email = cli.getEmail();
	}

}
