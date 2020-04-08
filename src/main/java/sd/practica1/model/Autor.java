package sd.practica1.model;

import javax.persistence.*;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nombre;
    private String apellidos;
    private String NIF;
    private Integer anyoNacimiento;
    private String pais;
    private String direccion;
    private String email;
    private String telefono;
    @OneToMany(mappedBy = "autor")
    private Collection<Cuadro> cuadros;

    public Autor(){

    }

    public Autor(String nombre,String apellidos,String NIF, Integer anyoNacimiento, String pais, String direccion, String email, String tlf){
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.NIF=NIF;
        this.anyoNacimiento=anyoNacimiento;
        this.pais=pais;
        this.direccion=direccion;
        this.email=email;
        this.telefono=tlf;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id_autor) {
        this.id = id_autor;
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

    public Integer getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(Integer anyoNacimiento) {
        this.anyoNacimiento = anyoNacimiento;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
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

    public String getTlf() {
        return telefono;
    }

    public void setTlf(String tlf) {
        this.telefono = tlf;
    }

    public Collection<Cuadro> getCuadros() {
        return cuadros;
    }

    public void agregarCuadro(Cuadro cuadro){
        cuadros.add(cuadro);
    }

    public static void ordenarAutoresNombresAsc(List<Autor> lista){
        Collections.sort(lista, new Comparator<Autor>() {
            @Override
            public int compare(Autor a1, Autor a2) {
                return a1.getNombre().compareTo(a2.getNombre());
            }
        });
    }

    public static void ordenarAutoresNombresDesc(List<Autor> lista){
        Collections.sort(lista, new Comparator<Autor>() {
            @Override
            public int compare(Autor a1, Autor a2) {
                return - a1.getNombre().compareTo(a2.getNombre());
            }
        });
    }

    public static void ordenarAutoresApellidosAsc(List<Autor> lista){
        Collections.sort(lista, new Comparator<Autor>() {
            @Override
            public int compare(Autor a1, Autor a2) {
                return a1.getApellidos().compareTo(a2.getApellidos());
            }
        });
    }

    public static void ordenarAutoresApellidosDesc(List<Autor> lista){
        Collections.sort(lista, new Comparator<Autor>() {
            @Override
            public int compare(Autor a1, Autor a2) {
                return a2.getApellidos().compareTo(a1.getApellidos());
            }
        });
    }


    @Override
    public String toString() { return id.toString(); }

    @Override
    public int hashCode() { return 0; }
}
