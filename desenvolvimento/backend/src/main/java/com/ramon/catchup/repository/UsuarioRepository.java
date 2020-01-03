package com.ramon.catchup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ramon.catchup.domain.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

	Optional<Usuario> findByCpfAndSenha(String cpf, String senha);

}
