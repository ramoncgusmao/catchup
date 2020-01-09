package com.ramon.catchup.domain.dto;

import java.io.IOException;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.ramon.catchup.domain.Aviso;
import com.ramon.catchup.exception.ErroAoSalvar;
import com.ramon.catchup.util.ConvertDate;
import com.ramon.catchup.util.Imagem;

import lombok.Data;
@Data
public class AvisoDto {

	private Integer id;
	
	@NotEmpty(message = "O titulo do aviso é obrigatorio")
	private String titulo;

	@NotEmpty(message = "O titulo da url é obrigatoria")
	private String descricao;

	
	 @NotEmpty(message = "O arquivo da imagem é necessaria")
	private String imagem;
	 @NotEmpty(message = "A extensao do arquivo é necessaria")
	private String extencao;
	 
	public Aviso convertToEntity() throws ErroAoSalvar {
		  
		try {
			return Aviso.builder()
					.titulo(titulo)
					.descricao(descricao)
					.imagem(Imagem.converterFotoBase64ToArrayByte(imagem))
					.dataCadastro(ConvertDate.localDateToDate(LocalDate.now()))
					.build();
		} catch (IOException e) {
			
			throw new ErroAoSalvar("ocorreu um erro ao converter o Aviso " + e.getMessage() );
		}
	}

}
