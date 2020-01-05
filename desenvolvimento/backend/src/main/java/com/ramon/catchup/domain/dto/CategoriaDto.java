package com.ramon.catchup.domain.dto;

import javax.validation.constraints.NotEmpty;

import com.ramon.catchup.domain.Categoria;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class CategoriaDto {

	@NotEmpty(message = "necessario nome categoria")
	private String nome;
	
	public Categoria convertToEntity() {
		return Categoria.builder().nome(nome).build();
	}
}
