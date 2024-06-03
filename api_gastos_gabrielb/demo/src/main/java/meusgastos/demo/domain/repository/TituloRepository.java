package meusgastos.demo.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import meusgastos.demo.domain.model.Titulo;
import meusgastos.demo.domain.model.Usuario;





public interface TituloRepository extends JpaRepository<Titulo, Long>{
    
    List<Titulo> findByUsuario(Usuario usuario);
}

