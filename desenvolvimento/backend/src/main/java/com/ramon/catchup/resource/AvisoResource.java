package com.ramon.catchup.resource;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

@RestController
@RequestMapping(value = "/aviso")
public class AvisoResource {
	
	@Autowired
	private AvisoService avisoService;
	
	@PostMapping
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
	
	
	@GetMapping()
	public ResponseEntity buscarUltimo() {
		
		try {
			
			Aviso aviso = avisoService.findByLastDate();
			return ResponseEntity.ok(aviso);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity editar(@PathVariable("id") Integer id, @RequestBody @Valid AvisoDto dto) {
		
		try {
			Aviso aviso = dto.convertToEntity();
			aviso = avisoService.editar(id, aviso);
			return ResponseEntity.ok(aviso);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity editar(@PathVariable("id") Integer id) {
		
		try {
		
			avisoService.delete(id);
			return ResponseEntity.ok("deletado com sucesso");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
}
