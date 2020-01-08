package com.ramon.catchup.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import javax.persistence.EntityManager;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ramon.catchup.domain.Filial;
import com.ramon.catchup.domain.Perfil;
import com.ramon.catchup.domain.Usuario;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class UsuarioRepositoryTest {

	@Autowired
	private UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveSalvarUmaUsuario() {
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario.getFilial());
		usuario = repository.save(usuario);

		assertThat(usuario.getId()).isNotNull();

	}

	@Test
	public void deveBuscarUmaUsuarioPorCpf() {
		Usuario usuario = criarUsuario();
		criarCenarioFilialUsuario(usuario);
		
		Optional<Usuario> usuarioopt = repository.findByCpf("12312312325");

		assertThat(usuarioopt).isPresent();
		assertThat(usuarioopt.get().getNome()).contains("Ramon teste");
			

	}

	@Test
	public void naoBuscarUmaUsuarioPorCpf() {
		Usuario usuario = criarUsuario();
		criarCenarioFilialUsuario(usuario);
		
		Optional<Usuario> usuarioopt = repository.findByCpf("1211312325");

		assertThat(usuarioopt).isEmpty();	

	}
	@Test
	public void naoDeveRetornarPorEmail() {
		Usuario usuario = criarUsuario();
		criarCenarioFilialUsuario(usuario);
	
		assertThat(repository.findByEmail("ramong@gmail.com")).isNull();

	
		
	}
	
	@Test
	public void deveRetornarPorEmail() {
		Usuario usuario = criarUsuario();
		criarCenarioFilialUsuario(usuario);

		Usuario usuarioBuscar = repository.findByEmail("ramoncgusmao@gmail.com");
		assertThat(usuarioBuscar).isNotNull();
		assertThat(usuarioBuscar.getCpf()).contains("12312312325");
		

	
		
	}

	private void criarCenarioFilialUsuario(Usuario usuario) {
		entityManager.persist(usuario.getFilial());
		entityManager.persist(usuario);
	}
	
	public static Usuario criarUsuario() {
		
		Usuario usuario = new Usuario();
	
		usuario.setNome("Ramon teste");
		usuario.setSenha("senha 123");
		usuario.setCpf("12312312325");
		usuario.setEmail("ramoncgusmao@gmail.com");
		usuario.setFilial(FilialRepositoryTest.criarFilial());	
		usuario.addPerfil(Perfil.ADMIN);
		
		
		return usuario;
		
	}

}
