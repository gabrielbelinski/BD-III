package meusgastos.demo.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import meusgastos.demo.domain.model.CentroDeCusto;
import meusgastos.demo.domain.model.Usuario;
import java.util.List;

public interface CentroDeCustoRepository extends JpaRepository<CentroDeCusto, Long>{

    List<CentroDeCusto> findByUsuario(Usuario usuario);
    
} 
