package com.ramon.catchup.domain.dto;

import java.io.IOException;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.ramon.catchup.domain.Categoria;
import com.ramon.catchup.domain.Empresa;
import com.ramon.catchup.domain.Parceria;
import com.ramon.catchup.exception.FileException;
import com.ramon.catchup.util.Imagem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParceriaDto {

 
	@NotEmpty(message = "precisa da descrição da parceria")
	private String descricao;

	
	@NotNull(message = "precisa de uma categoria")
	private Integer categoria;

	@NotEmpty(message = "precisa do cnpj da parceria")
	private String cnpj;
	

	@NotEmpty(message = "precisa de uma imagem parceria")
	private String imagem;
	
	
	public Parceria convertToEntity() throws FileException {
		try {
			return Parceria.builder()
					.descricao(descricao)
					.categoria(Categoria.builder().id(categoria).build())
					.imagem(Imagem.converterFotoBase64ToArrayByte(imagem))
					.empresa(Empresa.builder().cnpj(cnpj).build())
					.build();
		
		} catch (IOException e) {
			
			throw new FileException("erro ao converter imagem"); 
		}
	}
	

}
