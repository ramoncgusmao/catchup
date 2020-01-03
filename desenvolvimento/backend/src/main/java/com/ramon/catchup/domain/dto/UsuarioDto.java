package com.ramon.catchup.domain.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;

import com.ramon.catchup.domain.Filial;
import com.ramon.catchup.domain.Perfil;
import com.ramon.catchup.domain.Usuario;

import lombok.Data;

@Data
public class UsuarioDto {


	
	 @NotEmpty(message = "O nome é necessario")
	private String nome;
	 @NotEmpty(message = "A senha é necessaria")
	private String senha;
	 @NotNull(message = "O perfil é necessario")
	private Integer perfil;
	
	 @NotEmpty(message = "A filial é necessaria")
	private String filial;
	 
	 @NotEmpty(message = "O Cpf é necessario")
	private String cpf;
	 public Usuario convertToEntity() {
		 
		 return Usuario.builder()
				 .nome(nome)
				 .senha(senha)
				 .perfil(Perfil.toEnum(perfil))
				 .cpf(cpf)
				 .filial(Filial.builder().nome(filial).build()).build();	
		 }
}
