package com.ramon.catchup.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

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



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class FilialRepositoryTest {

	private static final String FILIAL_TEST = "filial 123";

	@Autowired
	private FilialRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveSalvarUmaFilial() {
		Filial filial = criarFilial();

		filial = repository.save(filial);

		assertThat(filial.getId()).isNotNull();

	}

	@Test
	public void deveBuscarFiliais() {
		Filial filial = criarFilial();

		entityManager.persist(filial);
		
		Optional<Filial> filialopt = repository.findByNome(FILIAL_TEST);

		assertThat(filialopt).isPresent();
		assertThat(filialopt.get().getNome()).contains(FILIAL_TEST);
			

	}

	@Test
	public void naoBuscarFiliais() {
		Filial filial = criarFilial();

		entityManager.persist(filial);
		
		Optional<Filial> filialopt = repository.findByNome("filial 112");

		assertThat(filialopt).isEmpty();	

	}
	

	@Test
	public void deveBuscarFiliaisComFiltro() {
		Filial filial = criarFilial();

		entityManager.persist(filial);
		
		Optional<Filial> filialopt = repository.findByNome(FILIAL_TEST);

		assertThat(filialopt).isPresent();
		assertThat(filialopt.get().getNome()).contains(FILIAL_TEST);
			

	}

	
	public static Filial criarFilial() {
		
		return Filial.builder().nome(FILIAL_TEST).build();
	
	}

}
