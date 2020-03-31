package sd.practica1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import sd.practica1.model.Cliente;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository <Cliente, Integer> {
    Cliente findByNIF(String nif);
    List<Cliente> findByNombreContainsOrApellidosContainsOrNIFContainsOrDireccionContainsOrEmailContainsOrTelefonoContainsIgnoreCase(String nombre, String apellidos, String nif, String direccion, String email, String telefono);
    List<Cliente> findByNombreContainsIgnoreCase(String nombre);
    List<Cliente> findByApellidosContainsIgnoreCase(String apellidos);
    List<Cliente> findByNIFContainsIgnoreCase(String nif);
    List<Cliente> findByDireccionContainsIgnoreCase(String direccion);
    List<Cliente> findByEmailContainsIgnoreCase(String email);
    List<Cliente> findByTelefonoContainsIgnoreCase(String telefono);
    List<Cliente> findAllByOrderByNombreAsc();
    List<Cliente> findAllByOrderByNombreDesc();
    List<Cliente> findAllByOrderByApellidosAsc();
    List<Cliente> findAllByOrderByApellidosDesc();
}
