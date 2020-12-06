package com.udemy.curso.domain.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.udemy.curso.services.validation.ClienteInsert;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@ClienteInsert
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//CLIENTE
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	@Length(min = 5, max = 80, message = "O TAMANHO DEVE SER ENTRE 5 E 80 CARACTERES" )
	private String nome;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	@Email(message = "Email inválido!!")
	private String email;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	private String cpfOrCnpf;
	private Integer tipo;
	
	//ENDERECO
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	private String logradouro;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	private String numero;
	private String complemento;
	private String bairro;
	
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	private String cep;
	
	//TELEFONE
	@NotEmpty(message = "CAMPO OBRIGATORIO!!!")
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	
	//CIDADE
	private Integer cidadeId;

}
