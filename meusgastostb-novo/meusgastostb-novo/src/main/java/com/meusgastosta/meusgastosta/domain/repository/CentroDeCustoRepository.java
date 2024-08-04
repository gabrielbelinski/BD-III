package com.meusgastosta.meusgastosta.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meusgastosta.meusgastosta.domain.model.CentroDeCusto;
import com.meusgastosta.meusgastosta.domain.model.Usuario;

public interface CentroDeCustoRepository 
    extends JpaRepository<CentroDeCusto, Long> {
    
    List<CentroDeCusto> findByUsuario(Usuario usuario);
}
