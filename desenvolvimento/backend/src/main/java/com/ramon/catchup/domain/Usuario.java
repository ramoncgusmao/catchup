package com.ramon.catchup.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import ch.qos.logback.core.subst.Token.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "usuarios")
@Data
public class Usuario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(length = 100, name = "nome")
	private String nome;
	
	@Column(length = 15, name = "cpf")
	private String cpf;
	
	@JsonIgnore
	@Column(length = 100, name = "senha")
	private String senha;
	
	@Column(length = 50, name = "email")
	private String email;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="PERFIS")
	private Set<Integer> perfis = new HashSet<Integer>();

	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "id_filial", nullable = false)
	private  Filial filial;
	
	@JsonIgnore
	@ManyToMany(mappedBy = "curtidas")
	private Set<Post> curtidos = new HashSet<Post>();
	
	public void addPerfil(Perfil perfil) {
		
		perfis.add(perfil.getCod());
	}
	
	public Set<Perfil> getPerfis(){
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}
	

}
