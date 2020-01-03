package com.ramon.catchup.resource;

import javax.validation.Valid;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.catchup.domain.Usuario;
import com.ramon.catchup.domain.dto.UsuarioDto;
import com.ramon.catchup.service.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;
	
	@PostMapping
	public ResponseEntity criarUsuario(@RequestBody @Valid UsuarioDto dto) {
		
		try {
			Usuario usuario = dto.convertToEntity();
			usuario = usuarioService.save(usuario);
			return new ResponseEntity(usuario, HttpStatus.CREATED);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity buscarUsuario(@PathVariable("id") Integer id) {
		
		try {
			
			Usuario usuario = usuarioService.findById(id);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity editarUsuario(@PathVariable("id") Integer id, @RequestBody @Valid UsuarioDto dto) {
		
		try {
			Usuario usuario = dto.convertToEntity();
			usuario = usuarioService.editar(id, usuario);
			return ResponseEntity.ok(usuario);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
}
