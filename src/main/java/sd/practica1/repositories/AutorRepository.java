package sd.practica1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.practica1.model.Autor;

public interface AutorRepository extends JpaRepository<Autor, Integer> {
}
