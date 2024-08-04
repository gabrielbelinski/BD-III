package com.meusgastosta.meusgastosta.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meusgastosta.meusgastosta.domain.model.Usuario;

public interface UsuarioRepository extends 
    JpaRepository<Usuario, Long> {
       
        Optional<Usuario> findByEmail(String email);
    
}
