package sd.practica1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.practica1.model.Autor;
import sd.practica1.model.Cliente;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Integer> {

    Autor findByNIF(String nif);
    Autor findByNombreContainsIgnoreCaseOrApellidosContainsIgnoreCase(String nombre, String apellidos);
    List<Autor> findByNombreContainsIgnoreCase(String nombre);
    List<Autor> findByApellidosContainsIgnoreCase(String apellidos);
    List<Autor> findByNIFContainsIgnoreCase(String nif);
    List<Autor> findByAnyoNacimiento(int anyoNacimiento);
    List<Autor> findByPaisContainsIgnoreCase(String pais);
    List<Autor> findByDireccionContainsIgnoreCase(String direccion);
    List<Autor> findByEmailContainsIgnoreCase(String email);
    List<Autor> findByTelefonoContainsIgnoreCase(String telefono);
}
