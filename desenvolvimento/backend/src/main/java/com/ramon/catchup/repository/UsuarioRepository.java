package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ramon.catchup.domain.Usuario;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByEmailAndSenha(String email, String senha);

	Optional<Usuario> findByCpf(String cpf);
	
	Usuario findByEmail(String email);

}
