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
import org.springframework.web.bind.annotation.RestController;

import com.ramon.catchup.domain.Empresa;
import com.ramon.catchup.domain.dto.EmpresaDto;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.exception.ErroAoSalvar;
import com.ramon.catchup.exception.RegraNegocioException;
import com.ramon.catchup.service.EmpresaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/empresa")
public class EmpresaResource {
	
	@Autowired
	private EmpresaService empresaService;
	
	@ApiOperation(value = "Cria uma nova Empresa")
	@PostMapping(produces="application/json", consumes="application/json")
	public ResponseEntity criarEmpresa(@RequestBody @Valid EmpresaDto dto) {
		
		
		try {
			Empresa empresa = dto.convertToEntity();
			empresa = empresaService.save(empresa);
			return new ResponseEntity(empresa, HttpStatus.CREATED);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	@ApiOperation(value = "Busca uma empresa pelo CNPJ")
	@GetMapping(value = "/{cnjp}", produces="application/json")
	public ResponseEntity buscarEmpresas(@PathVariable("cnpj") String cnpj) {
		
		try {
			
			Empresa empresa = empresaService.findByCnpj(cnpj);
			return ResponseEntity.ok(empresa);
		} catch (RegraNegocioException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
	@ApiOperation(value = "Edita uma empresa pelo seu Id")
	@PutMapping(value = "/{id}", produces="application/json")
	public ResponseEntity editar(@PathVariable("id") Integer id, @RequestBody @Valid EmpresaDto dto) {
		
		try {
			Empresa empresa = dto.convertToEntity();
			empresa = empresaService.update(id, empresa);
			return ResponseEntity.ok(empresa);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	
	}
}
