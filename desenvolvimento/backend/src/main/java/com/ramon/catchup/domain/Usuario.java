package com.ramon.catchup.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, name = "nome")
	private String nome;
	
	@Column(length = 15, name = "cpf")
	private String cpf;
	
	@Column(length = 50, name = "senha")
	private String senha;
	
	@Enumerated(EnumType.STRING)
	private Perfil perfil;

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_filial", nullable = false)
	private  Filial filial;
	
	@ManyToMany(mappedBy = "curtidas")
	private Set<Post> curtidos = new HashSet<Post>();
	
	
	
}
