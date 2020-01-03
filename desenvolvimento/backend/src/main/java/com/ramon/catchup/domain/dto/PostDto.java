package com.ramon.catchup.domain.dto;

import java.io.IOException;
import java.time.LocalDate;

import javax.validation.constraints.NotEmpty;

import com.ramon.catchup.domain.Post;
import com.ramon.catchup.exception.FileException;
import com.ramon.catchup.util.ConvertDate;
import com.ramon.catchup.util.Imagem;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PostDto {

	@NotEmpty(message = "a foto precisa de uma descrição")
	private String descricao;
	
	@NotEmpty(message = "a foto precisa de uma imagem")
	private String imagem;


	public Post convertToEntity() throws FileException {
		try {
			return Post.builder()
					.dataCadastro(ConvertDate.localDateToDate(LocalDate.now()))
					.imagem(Imagem.converterFotoBase64ToArrayByte(imagem))
					.descricao(descricao)
					.build();
		} catch (IOException e) {
			throw new FileException("problema ao converter a imagem");
		}
	}
}
