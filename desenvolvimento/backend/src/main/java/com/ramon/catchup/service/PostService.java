package com.ramon.catchup.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Post;
import com.ramon.catchup.domain.Usuario;
import com.ramon.catchup.exception.RegraNegocioException;
import com.ramon.catchup.repository.PostRepository;
import com.ramon.catchup.util.ConvertDate;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	@Autowired
	private UsuarioService usuarioService;
	public Post save(Post post) {
		
	
		post.setUsuario(usuarioService.findById(UsuarioService.authenticated().getId()));
		
		return repository.save(post);
	}
	
	
	public Page<Post> findAll( Integer page, Integer linePerPage) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage,  Sort.by("dataCadastro").descending());

		
		return repository.findAll(pageRequest);
	}


	public void curtir(Integer idPost) {
		Post post = findPost(idPost);
		Usuario usuario = usuarioService.findById(1);
		
		if(post.getCurtidas().contains(usuario)) {
			post.getCurtidas().remove(usuario);
		}else {
			post.getCurtidas().add(usuario);
		}
	
		repository.save(post);
	}
	
	public Post findPost(Integer idPost) {
		if(idPost == null) {
			throw new RegraNegocioException("não existe id");
		}
		
		Optional<Post> post = repository.findById(idPost);
		
		if(post.isPresent()) {
			return post.get();
		}
		
		throw new RegraNegocioException("não existe post com esse id");
	}


	public Post converterImagem(byte[] imagem, String descricao) {
	
		return Post.builder()
				.dataCadastro(ConvertDate.localDateNow())
				.imagem(imagem)
				.descricao(descricao)
				.build();
		
	}
	

}
