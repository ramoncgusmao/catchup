package com.ramon.catchup.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.catchup.domain.Categoria;
import com.ramon.catchup.domain.dto.CategoriaDto;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.ErroAoSalvar;
import com.ramon.catchup.service.CategoriaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/categoria")
public class CategoriaResource {
	
	@Autowired
	private CategoriaService categoriaService;
	
	@ApiOperation(value = "Cria uma nova Categoria")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity criarCategoria(@RequestBody @Valid CategoriaDto dto) {
		
		
		try {
			Categoria categoria = dto.convertToEntity();
			categoria = categoriaService.save(categoria);
			return new ResponseEntity(categoria, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = "Busca todas as Categorias")
	@GetMapping(produces="application/json")
	public ResponseEntity buscarCategorias() {
		
		try {
			
			List<Categoria> categorias = categoriaService.findAll();
			return ResponseEntity.ok(categorias);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	@ApiOperation(value = "Edita uma categoria e retorna ela modificada")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity editar(@PathVariable("id") Integer id, @RequestBody @Valid CategoriaDto dto) {
		
		try {
			Categoria categoria = dto.convertToEntity();
			categoria = categoriaService.update(id, categoria);
			return ResponseEntity.ok(categoria);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	@ApiOperation(value = "Deleta uma categoria")
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping("/{id}")
	public ResponseEntity deletar(@PathVariable("id") Integer id) {
		
		try {
		
			categoriaService.delete(id);
			return ResponseEntity.ok("deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
}
