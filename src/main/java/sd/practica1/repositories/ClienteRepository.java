package sd.practica1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.practica1.model.Cliente;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
    Cliente findByNIF(String nif);
    Optional<Cliente> findById(Integer id);
}
