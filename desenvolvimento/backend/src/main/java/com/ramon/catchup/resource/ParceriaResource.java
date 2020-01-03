package com.ramon.catchup.resource;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ramon.catchup.domain.Categoria;
import com.ramon.catchup.domain.Parceria;
import com.ramon.catchup.domain.dto.ParceriaDto;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.ErroAoSalvar;
import com.ramon.catchup.service.ParceriaService;

@RestController
@RequestMapping(value = "/parceria")
public class ParceriaResource {
	
	@Autowired
	private ParceriaService parceriaService;
	
	
	@PostMapping
	public ResponseEntity criarParceria(@RequestBody @Valid ParceriaDto dto) {
		
		
		try {
			Parceria parceria = dto.convertToEntity();
			parceria = parceriaService.save(parceria);
			return new ResponseEntity(parceria, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	@GetMapping()
	public ResponseEntity buscarParcerias(@RequestParam(value = "status", required = false) boolean status, 
			 @RequestParam(value = "categoria", required = false) Integer categoria) {
		
		try {
			Parceria parceriaFiltro = new Parceria();
			parceriaFiltro.setStatus(status);
			if(categoria != null) {
				parceriaFiltro.setCategoria(Categoria.builder().id(categoria).build());
			}
			List<Parceria> parcerias = parceriaService.buscarParcerias(parceriaFiltro);
			return ResponseEntity.ok(parcerias);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@PutMapping("/{id}")
	public ResponseEntity editar(@PathVariable("id") Integer id, @RequestBody @Valid ParceriaDto dto) {
		
		try {
			Parceria parceria = dto.convertToEntity();
			parceria = parceriaService.update(id, parceria);
			return ResponseEntity.ok(parceria);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	
	@GetMapping("/{id}/modificarStatus")
	public ResponseEntity modificarStatus(@PathVariable("id") Integer id) {
		
		try {
		
			Parceria parceria = parceriaService.modificarStatus(id);
			return ResponseEntity.ok(parceria);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
}
