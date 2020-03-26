package sd.practica1.model;

import java.util.Date;

public class CuadroVendido extends Cuadro{
    private String comprador;
    private Date fechaDeCompra;

    public String getComprador() {
        return comprador;
    }

    public void setComprador(String comprador) {
        this.comprador = comprador;
    }

    public Date getFechaDeCompra() {
        return fechaDeCompra;
    }

    public void setFechaDeCompra(Date fechaDeCompra) {
        this.fechaDeCompra = fechaDeCompra;
    }
}
