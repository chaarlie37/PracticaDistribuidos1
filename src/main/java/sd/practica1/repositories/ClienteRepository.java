package sd.practica1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.practica1.model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
}
