package sd.practica1.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Entity
public class Cuadro {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private boolean vendido;
    private String titulo;
    private String descripcion;
    private Integer anyoFinalizacion;
    private float anchura;
    private float altura;
    private int precio;
    @ManyToOne
    private Cliente comprador;
    private Date fechaCompra;
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

    public Cliente getComprador() {
        return comprador;
    }

    public void setComprador(Cliente comprador) {
        this.comprador = comprador;
    }

    public Date getFechaCompra() {
        return fechaCompra;
    }

    public void setFechaCompra(Date fechaCompra) {
        this.fechaCompra = fechaCompra;
    }



    public String vendido_texto(){
        if(vendido)
            return "Si";
        else
            return "No";
    }

    public String formato_dimensiones(float dimension){
        DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
        decimalFormatSymbols.setDecimalSeparator(',');
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(dimension);
    }

    public String altura_formato(){
        return formato_dimensiones(this.altura);
    }

    public String anchura_formato(){
        return formato_dimensiones(this.anchura);
    }

    public static void ordenarCuadrosTituloAsc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c1.getTitulo().compareTo(c2.getTitulo());
            }
        });
    }

    public static void ordenarCuadrosTituloDesc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c2.getTitulo().compareTo(c1.getTitulo());
            }
        });
    }

    public static void ordenarCuadrosAnyoAsc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c1.getAnyoFinalizacion() - c2.getAnyoFinalizacion();
            }
        });
    }

    public static void ordenarCuadrosAnyoDesc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c2.getAnyoFinalizacion() - c1.getAnyoFinalizacion();
            }
        });
    }

    public static void ordenarCuadrosPrecioAsc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c1.getPrecio() - c2.getPrecio();
            }
        });
    }

    public static void ordenarCuadrosPrecioDesc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c2.getPrecio() - c1.getPrecio();
            }
        });
    }

    public static void ordenarCuadrosFechaDesc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c2.getFechaCompra().compareTo(c1.getFechaCompra());
            }
        });
    }

    public static void ordenarCuadrosFechaAsc(List<Cuadro> lista){
        Collections.sort(lista, new Comparator<Cuadro>() {
            @Override
            public int compare(Cuadro c1, Cuadro c2) {
                return c1.getFechaCompra().compareTo(c2.getFechaCompra());
            }
        });
    }

    public void agregarFecha(String fecha){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date d = new java.util.Date();
        try{
            d = simpleDateFormat.parse(fecha);
            this.fechaCompra = new Date(d.getTime());
        }catch(ParseException e){}

    }

    public String fechaFormateada(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        simpleDateFormat.format(fechaCompra);
        return fechaCompra.toString();
    }

    @Override
    public String toString() {
        return this.id.toString();
    }
}
