package com.ramon.catchup.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

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

import com.ramon.catchup.domain.Empresa;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class EmpresaRepositoryTest {

	@Autowired
	private EmpresaRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveSalvarUmaEmpresa() {
		Empresa empresa = criarEmpresa();

		empresa = repository.save(empresa);

		assertThat(empresa.getId()).isNotNull();

	}

	@Test
	public void deveBuscarUmaEmpresa() {
		Empresa empresa = criarEmpresa();

		entityManager.persist(empresa);
		
		Optional<Empresa> empresaopt = repository.findByCnpj("111121/1029-12");

		assertThat(empresaopt).isPresent();
		assertThat(empresaopt.get().getEndereco()).contains("Avenida sul");
			

	}

	@Test
	public void naoBuscarUmaEmpresa() {
		Empresa empresa = criarEmpresa();

		entityManager.persist(empresa);
		
		Optional<Empresa> empresaopt = repository.findByCnpj("111121/1011-12");

		assertThat(empresaopt).isEmpty();	

	}
	@Test
	public void naoDeveRetornarCnpj() {
		Empresa empresa = criarEmpresa();

		entityManager.persist(empresa);
	
		Assertions.assertThat(repository.existsByCnpj("11121/1221-12")).isFalse();

	
		
	}
	
	@Test
	public void deveRetornarCnpj() {
		Empresa empresa = criarEmpresa();

		entityManager.persist(empresa);
	
		Assertions.assertThat(repository.existsByCnpj("111121/1029-12")).isTrue();

	
		
	}
	
	public static Empresa criarEmpresa() {
		
		return Empresa.builder()
				.cnpj("111121/1029-12")
				.endereco("Avenida sul")
				.nome("EmpresaFantasia")
				.telefone("989889-0192")
				.build();
	}

}
