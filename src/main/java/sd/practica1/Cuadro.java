package sd.practica1;

import javax.persistence.*;

@Entity
public class Cuadro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private boolean vendido;
    private String titulo;
    private String descripcion;
    private String anyoFinalizacion;
    private float anchura;
    private float altura;
    private int precio;
    @ManyToOne
    private Autor autor;

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

    public String getAnyoFinalizacion() {
        return anyoFinalizacion;
    }

    public void setAnyoFinalizacion(String anyoFinalizacion) {
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
