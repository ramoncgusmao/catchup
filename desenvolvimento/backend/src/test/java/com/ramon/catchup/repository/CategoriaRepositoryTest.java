package com.ramon.catchup.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
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

import com.ramon.catchup.domain.Categoria;



@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@ActiveProfiles("test")
public class CategoriaRepositoryTest {

	private static final String CATEGORIA_TEST = "categoria 123";

	@Autowired
	private CategoriaRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveSalvarUmaCategoria() {
		Categoria categoria = criarCategoria(CATEGORIA_TEST);

		categoria = repository.save(categoria);

		assertThat(categoria.getId()).isNotNull();

	}

	@Test
	public void deveBuscarCategorias() {
		criarListaCategoria();

		
		List<Categoria> categorias = repository.findAll();

		assertThat(categorias.size()).isEqualTo(4);

	}

	@Test
	public void naoBuscarUmaCategoria() {

		
		List<Categoria> categorias = repository.findAll();

		assertThat(categorias).isEmpty();	

	}
	

	public void criarListaCategoria() {
		persistirCategoria("Categoria 001");
		persistirCategoria("Categoria 002");
		persistirCategoria("Categoria 003");
		persistirCategoria("Categoria 004");
	}
	public void persistirCategoria(String categoriaNome) {
		entityManager.persist(criarCategoria(categoriaNome));
	}
	
	public static Categoria criarCategoria(String categoriaNome) {
		
		return Categoria.builder().nome(categoriaNome).build();
	
	}

}
