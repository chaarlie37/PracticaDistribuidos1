package sd.practica1.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
public class Cliente{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String apellidos;
    private String NIF;
    private String direccion;
    private String email;
    private String telefono;
    @OneToMany(mappedBy = "comprador")
    private Collection<Cuadro> cuadros;

    public Cliente(){

    }

    public Cliente(String nombre, String apellidos, String NIF, String direccion, String email, String telefono){
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.NIF= NIF;
        this.direccion=direccion;
        this.email=email;
        this.telefono=telefono;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getNIF() {
        return NIF;
    }

    public void setNIF(String NIF) {
        this.NIF = NIF;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Collection<Cuadro> getCuadros() {
        return cuadros;
    }

    public void setCuadros(Collection<Cuadro> cuadros) {
        this.cuadros = cuadros;
    }

    public static void ordenarClientesNombreAsc(List<Cliente> lista){
        Collections.sort(lista, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return c1.getNombre().compareTo(c2.getNombre());
            }
        });
    }

    public static void ordenarClientesNombreDesc(List<Cliente> lista){
        Collections.sort(lista, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return - c1.getNombre().compareTo(c2.getNombre());
            }
        });
    }

    public static void ordenarClientesApellidoAsc(List<Cliente> lista){
        Collections.sort(lista, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return c1.getApellidos().compareTo(c2.getApellidos());
            }
        });
    }

    public static void ordenarClientesApellidoDesc(List<Cliente> lista){
        Collections.sort(lista, new Comparator<Cliente>() {
            @Override
            public int compare(Cliente c1, Cliente c2) {
                return - c1.getApellidos().compareTo(c2.getApellidos());
            }
        });
    }

    @Override
    public String toString() {
        return id.toString();
    }


    public void agregarCuadro(Cuadro c){
        this.cuadros.add(c);
    }
}
