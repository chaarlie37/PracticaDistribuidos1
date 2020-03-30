package sd.practica1.model;

import javax.persistence.*;

@Entity
public class Cuadro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private boolean vendido;
    private String titulo;
    private String descripcion;
    private int anyoFinalizacion;
    private float anchura;
    private float altura;
    private int precio;
    @ManyToOne
    @JoinColumn(name = "id_autor")
    private Autor autor;

    public Cuadro(){

    }

    public Cuadro(String titulo,String descripcion,int anyoFinalizacion, float anchura, float altura, int precio){
        this.titulo =  titulo;
        this.descripcion= descripcion;
        this.anyoFinalizacion=anyoFinalizacion;
        this.anchura=anchura;
        this.altura=altura;
        this.precio=precio;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public boolean isVendido() {
        return vendido;
    }

    public void setVendido(boolean vendido) {
        this.vendido = vendido;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getAnyoFinalizacion() {
        return anyoFinalizacion;
    }

    public void setAnyoFinalizacion(int anyoFinalizacion) {
        this.anyoFinalizacion = anyoFinalizacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public float getAnchura() {
        return anchura;
    }

    public void setAnchura(float anchura) {
        this.anchura = anchura;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

}
