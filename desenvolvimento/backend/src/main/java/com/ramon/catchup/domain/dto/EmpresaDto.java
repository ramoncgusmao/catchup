package com.ramon.catchup.domain.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.ramon.catchup.domain.Empresa;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class EmpresaDto {

	@Size(min = 5)
	@NotEmpty(message = "a empresa precisa ter um nome")
	private String nome;
	@Size(min = 8)
	@NotEmpty(message = "precisa de telefone")
	private String telefone;
	@Size(min = 10)
	@NotEmpty(message = "precisa de cnpj")
	private String cnpj;
	@Size(min = 10)
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
