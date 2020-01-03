package com.ramon.catchup.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.ramon.catchup.domain.Post;
import com.ramon.catchup.repository.PostRepository;

@Service
public class PostService {

	@Autowired
	private PostRepository repository;

	@Autowired
	private UsuarioService usuarioService;
	public Post save(Post post) {
		
	
		post.setUsuario(usuarioService.findById(1));
		
		return repository.save(post);
	}
	
	
	public Page<Post> findAll( Integer page, Integer linePerPage) {
        PageRequest pageRequest = PageRequest.of(page, linePerPage,  Sort.by("dataCadastro").descending());

		
		return repository.findAll(pageRequest);
	}


	public void curtir(Integer idPost) {
		
		post.setUsuario(usuarioService.findById(1));
	}
	
	
	

}
