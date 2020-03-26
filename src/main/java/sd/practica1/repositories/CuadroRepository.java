package sd.practica1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.practica1.model.Cuadro;

public interface CuadroRepository extends JpaRepository<Cuadro, Integer> {
}
