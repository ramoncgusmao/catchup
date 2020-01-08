package com.ramon.catchup.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.ramon.catchup.domain.Usuario;
import com.ramon.catchup.exception.DataIntegrityException;
import com.ramon.catchup.repository.UsuarioRepository;
import com.ramon.catchup.repository.UsuarioRepositoryTest;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
public class UsuarioServiceTest {

	@MockBean
	private BCryptPasswordEncoder pe;
	
	@SpyBean
	UsuarioService service;
	
	@MockBean
	UsuarioRepository repository;
	
	@MockBean
	FilialService filialService;
	@Test
	public void deveSalvarUmUsuario(){
		Usuario usuarioASalvar = UsuarioRepositoryTest.criarUsuario();
	
		Usuario usuarioSalvo = UsuarioRepositoryTest.criarUsuario();
		usuarioSalvo.setId(1);
		Mockito.when(repository.save(usuarioASalvar)).thenReturn(usuarioSalvo);
		Usuario Usuario = service.save(usuarioASalvar);
		
		Assertions.assertThat(Usuario.getId()).isEqualTo(usuarioSalvo.getId());
		
	}
	
	@Test
	public void deveBuscarUmUsuario(){

		Optional<Usuario> usuarioNaBase = Optional.of(UsuarioRepositoryTest.criarUsuario());
		
		usuarioNaBase.get().setId(1);
		Mockito.when(repository.findById(usuarioNaBase.get().getId())).thenReturn(usuarioNaBase);
		Usuario Usuario = service.findById(usuarioNaBase.get().getId());
		
		
		assertThat(Usuario.getId()).isEqualTo(usuarioNaBase.get().getId());
		
	}
	
	@Test
	public void naoDeveBuscarUmUsuario(){

		Mockito.when(repository.findById(1)).thenReturn(Optional.empty());
		Assertions.catchThrowableOfType(() ->service.findById(1), DataIntegrityException.class);
		
	}
	
	@Test
	public void deveCriarOUsuarioInicial(){
		String email = "admin@admin.com.br";
	
		Mockito.when(repository.findByEmail(email)).thenReturn(null);
		
		String resposta = service.primeiroLogin();
		
		
		assertThat(resposta).isEqualTo("Usuario master cadastrado - email : admin@admin.com.br   senha: 123admin123");
	}
	
	@Test
	public void naoDeveCriarOUsuarioInicialPoisJaExiste(){
		
		
		String email = "admin@admin.com.br";
	
		Mockito.when(repository.findByEmail(email)).thenReturn(null);
		
		String resposta = service.primeiroLogin();
		
		
		assertThat(resposta).isEqualTo("Usuario master cadastrado - email : admin@admin.com.br   senha: 123admin123");
	}
}
