package com.ramon.catchup.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "empresas")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, name = "nome", nullable = false)
	private String nome;
	
	@Column(length = 13, name = "telefone")
	private String telefone;
	
	@Column(length = 15, name = "cnpj", nullable = false, unique = true)
	private String cnpj;
	
	@Column(length = 100, name = "endereco")
	private String endereco;
}
