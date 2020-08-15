package com.udemy.curso.domain.dto;

import java.io.Serializable;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteNewDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	//CLIENTE
	private String nome;
	private String email;
	private String cpfOrCnpf;
	private Integer tipo;
	
	//ENDERECO
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;
	
	//TELEFONE
	private String telefone1;
	private String telefone2;
	private String telefone3;
	
	
	//CIDADE
	private Integer cidadeID;

}
