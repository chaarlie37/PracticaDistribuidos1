package sd.practica1.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id_autor;

    private String nombre;
    private String apellidos;
    private String NIF;
    private int anyoNacimiento;
    private String pais;
    private String direccion;
    private String email;
    private String telefono;
    @OneToMany(mappedBy = "autor")
    private Collection<Cuadro> cuadros;

    public Autor(){

    }

    public Autor(String nombre,String apellidos,String NIF, int anyoNacimiento, String pais, String direccion, String email, String tlf){
        this.nombre = nombre;
        this.apellidos=apellidos;
        this.NIF=NIF;
        this.anyoNacimiento=anyoNacimiento;
        this.pais=pais;
        this.direccion=direccion;
        this.email=email;
        this.telefono=tlf;
    }

    public Integer getId_autor() {
        return id_autor;
    }

    public void setId_autor(Integer id_autor) {
        this.id_autor = id_autor;
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

    public int getAnyoNacimiento() {
        return anyoNacimiento;
    }

    public void setAnyoNacimiento(int anyoNacimiento) {
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

    public void agregarCuadro(Cuadro cuadro){
        cuadros.add(cuadro);
    }

    @Override
    public String toString() {
        return nombre + " " + apellidos;
    }
}
