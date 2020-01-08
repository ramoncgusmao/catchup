package com.ramon.catchup.resource;

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

import com.ramon.catchup.domain.Aviso;
import com.ramon.catchup.domain.dto.AvisoDto;
import com.ramon.catchup.exception.ErroAoSalvar;
import com.ramon.catchup.service.AvisoService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.Api;


@RestController
@RequestMapping(value = "/aviso")
public class AvisoResource {
	
	@Autowired
	private AvisoService avisoService;
	
	@ApiOperation(value = "Cria um novo Aviso")
	@ApiResponses(value = {
	    @ApiResponse(code = 201, message = "Retorna o aviso criado com id"),
	    @ApiResponse(code = 400, message = "Você não tem permissão para criar um aviso"),
	    @ApiResponse(code = 403, message = "Você enviou algum dado errado"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping( produces="application/json", consumes="application/json")
	public ResponseEntity criarAviso(@RequestBody @Valid AvisoDto dto) {
		
		
		try {
			Aviso aviso = dto.convertToEntity();
			aviso = avisoService.save(aviso);
			return new ResponseEntity(aviso, HttpStatus.CREATED);
		} catch (ErroAoSalvar e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = "Retorna o ultimo aviso criado")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o ultimo aviso criado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para visualizar esse recurso"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@GetMapping(produces="application/json")
	public ResponseEntity buscarUltimo() {
		
		try {
			
			Aviso aviso = avisoService.findByLastDate();
			return ResponseEntity.ok(aviso);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@ApiOperation(value = "Edita um aviso pelo seu id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna o aviso modificado"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para editar um aviso"),
	    @ApiResponse(code = 400, message = "Você enviou algum dado errado"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}", produces="application/json", consumes="application/json")
	public ResponseEntity editar(@PathVariable("id") Integer id, @RequestBody @Valid AvisoDto dto) {
		
		try {
			Aviso aviso = dto.convertToEntity();
			aviso = avisoService.editar(id, aviso);
			return ResponseEntity.ok(aviso);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@ApiOperation(value = "Deleta um aviso pelo seu id")
	@ApiResponses(value = {
	    @ApiResponse(code = 200, message = "Retorna uma mensagem informando que a deleção foi bem sucedida"),
	    @ApiResponse(code = 403, message = "Você não tem permissão para deletar um aviso"),
	    @ApiResponse(code = 400, message = "Você enviou algum dado errado"),
	    @ApiResponse(code = 500, message = "Foi gerada uma exceção"),
	})
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity deletar(@PathVariable("id") Integer id) {
		
		try {
		
			avisoService.delete(id);
			return ResponseEntity.ok("deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
}
