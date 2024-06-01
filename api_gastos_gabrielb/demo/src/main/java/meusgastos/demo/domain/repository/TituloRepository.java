package meusgastos.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import meusgastos.demo.domain.model.Titulo;
import meusgastos.demo.domain.model.Usuario;

import java.util.List;


public interface TituloRepository extends JpaRepository<Titulo, Long>{

    List<Titulo> findByUsuario(Usuario usuario);
}  
