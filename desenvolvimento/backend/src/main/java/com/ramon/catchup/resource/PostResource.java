package com.ramon.catchup.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ramon.catchup.domain.Post;
import com.ramon.catchup.domain.dto.PostDto;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.ErroAoSalvar;
import com.ramon.catchup.service.PostService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/post")
public class PostResource {
	
	@Autowired
	private PostService postService;
	
	@ApiOperation(value = "Cria um novo Post")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity criarPost(@RequestBody @Valid PostDto dto) {
		
		
		try {
			Post post = dto.convertToEntity();
			post = postService.save(post);
			return new ResponseEntity(post, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = "Busca os Post paginado")
	@GetMapping(produces="application/json")
	public ResponseEntity buscarPosts(@RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "10") Integer linePerPage) {
		
		try {
			
			Page<Post> posts = postService.findAll(page, linePerPage);
			return ResponseEntity.ok(posts);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@ApiOperation(value = "Curte ou deixa de curtir um Post")
	@GetMapping(value = "/{id}")
	public ResponseEntity curtir(@PathVariable("id") Integer idPost) {
		
		try {
			
			postService.curtir(idPost);
			return ResponseEntity.ok("imagem curtida");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	
	 public ResponseEntity uploadFile(
	            @RequestParam("file") MultipartFile uploadfile) {

	        if (uploadfile.isEmpty()) {
	            return new ResponseEntity("Você não informou o arquivo", HttpStatus.BAD_REQUEST);
	        }

	        try {

	            saveUploadedFiles(Arrays.asList(uploadfile));

	        } catch (IOException e) {
	            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	        }

	        return new ResponseEntity("Successfully uploaded - " +
	                uploadfile.getOriginalFilename(), new HttpHeaders(), HttpStatus.OK);

	    }

}
