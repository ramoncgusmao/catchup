package com.ramon.catchup.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;

import com.ramon.catchup.domain.Empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmpresaDto {

	private Integer id;
	
	@Min(5)
	@Max(100)
	@NotEmpty(message = "a empresa precisa ter um nome")
	private String nome;
	
	@Min(9)
	@NotEmpty(message = "precisa de telefone")
	private String telefone;
	
	@Min(10)
	@NotEmpty(message = "precisa de cnpj")
	private String cnpj;

	@Min(10)
	@NotEmpty(message = "precisa de endereco")
	private String endereco;
	
	public Empresa convertToEntity() {
		
		return Empresa.builder()
				.nome(nome)
				.cnpj(cnpj)
				.telefone(telefone)
				.endereco(endereco)
				.build();
	}
	
}
