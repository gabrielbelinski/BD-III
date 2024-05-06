package meusgastos.demo.domain.repository;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import meusgastos.demo.domain.model.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    
}  
