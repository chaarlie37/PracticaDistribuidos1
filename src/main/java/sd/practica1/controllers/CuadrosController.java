package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sd.practica1.model.Autor;
import org.springframework.web.bind.annotation.RequestParam;
import sd.practica1.model.Cliente;
import sd.practica1.model.Cuadro;
import sd.practica1.repositories.AutorRepository;
import sd.practica1.repositories.ClienteRepository;
import sd.practica1.repositories.CuadroRepository;


import javax.annotation.PostConstruct;
import java.sql.Date;
import java.util.List;

@Controller
public class CuadrosController {

    @Autowired
    private CuadroRepository cuadroRepository;
    @Autowired
    private AutorRepository autorRepository;
    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    public void init(){

        Cuadro c1 = new Cuadro("Titulo1","aaa",1111,30,30,100);
        Cuadro c2 = new Cuadro("Titulo2","bbb",1112,40,40,200);
        Cuadro c3 = new Cuadro("Titulo3","ccc",1113,50,50,300);
        Cuadro c4 = new Cuadro("Titulo4","ddd",1114,60,60,400);
        cuadroRepository.save(c1);
        cuadroRepository.save(c2);
        cuadroRepository.save(c3);
        cuadroRepository.save(c4);
    }

    @RequestMapping("/cuadros")
    public String inicio(Model model){
        model.addAttribute("cuadros", cuadroRepository.findAll());
        return "cuadros_template";
    }

    @RequestMapping("/cuadrosvendidos")
    public String inicioVendidos(Model model){
        model.addAttribute("cuadros", cuadroRepository.findByVendido(true));
        return "cuadrosvendidos_template";
    }

    @RequestMapping("/modificarcuadroform")
    public String modificarCuadroForm(@RequestParam Integer id, Model model){
        Cuadro cuadro = cuadroRepository.getOne(id);
        model.addAttribute("cuadro", cuadro);
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "modificarcuadroform";
    }

    @RequestMapping("/guardarcuadro")
    public String guardarCuadro(Cuadro nuevo, String anyo, String nifautor, String nifcliente, String fecha, Model model) {
        return construirCuadro(nuevo, anyo, nifautor, nifcliente, fecha, model);
    }

    @RequestMapping("/modificarcuadro")
    public String modificarCuadro(Integer id, Cuadro nuevo, String anyo, String nifautor, String nifcliente, String fecha, Model model) {
        nuevo.setId(id);
        return construirCuadro(nuevo, anyo, nifautor, nifcliente, fecha, model);
    }

    @RequestMapping("/nuevocuadro")
    public String nuevoCuadro(Model model){
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "nuevo_cuadro";
    }

    @RequestMapping("/mostrarcuadro")
    public String mostrarCuadro(@RequestParam Integer id, Model model){
        Cuadro cuadro = cuadroRepository.getOne(id);
        model.addAttribute("cuadro", cuadro);
        return "mostrarcuadro";
    }

    @RequestMapping("/buscarcuadro")
    public String buscarCuadro(String busqueda, String criterio, Model model){
        List<Cuadro> lista = null;

        switch (criterio){
            case "titulo":
                lista= cuadroRepository.findByTituloContainsIgnoreCase(busqueda);
                break;
            case "descripcion":
                lista=cuadroRepository.findByDescripcionIgnoreCase(busqueda);
                break;
            case "anyo":
                lista=cuadroRepository.findByAnyoFinalizacion(Integer.parseInt(busqueda));
                break;
            case "anchura":
                lista=cuadroRepository.findByAnchura(Float.parseFloat(busqueda));
                break;
            case "altura":
                lista=cuadroRepository.findByAltura(Float.parseFloat(busqueda));
                break;
            case "precio":
                lista=cuadroRepository.findByPrecio(Integer.parseInt(busqueda));
                break;
        }
        if (lista == null || lista.isEmpty()){
            String men = "No se ha encontrado ningún cuadro con " + criterio + " " + "\"" + busqueda + "\"";
            model.addAttribute("mensaje", men);
            return "error_msg";
        }
        else if(busqueda.equals("")){
            String mens = "No se ha insertado nada en la barra de búsqueda.";
            model.addAttribute("mensaje", mens);
            return "error_msg";
        }
        else {
            model.addAttribute("cuadros", lista);
            return "cuadros_template";
        }
    }

    @RequestMapping("/mostrarcuadrosorden")
    public String mostrarclientesorden(@RequestParam(value = "lista") List<Cuadro> lista, @RequestParam String orden, Model model){

        switch (orden){
            case "tituloAsc" : Cuadro.ordenarCuadrosTituloAsc(lista); break;
            case "tituloDesc" : Cuadro.ordenarCuadrosTituloDesc(lista); break;
            case "anyoAsc" : Cuadro.ordenarCuadrosAnyoAsc(lista); break;
            case "anyoDesc" : Cuadro.ordenarCuadrosAnyoDesc(lista); break;
            case "precioAsc" : Cuadro.ordenarCuadrosPrecioAsc(lista); break;
            case "precioDesc" : Cuadro.ordenarCuadrosPrecioDesc(lista); break;

        }

        model.addAttribute("cuadros", lista);
        return "cuadros_template";
    }

    @RequestMapping("/mostrarcuadrosvendidosorden")
    public String mostrarcuadrosvendidosorden(@RequestParam(value = "lista") List<Cuadro> lista, @RequestParam String orden, Model model){

        switch (orden){
            case "tituloAsc" : Cuadro.ordenarCuadrosTituloAsc(lista); break;
            case "tituloDesc" : Cuadro.ordenarCuadrosTituloDesc(lista); break;
            case "fechaAsc" : Cuadro.ordenarCuadrosFechaAsc(lista); break;
            case "fechaDesc" : Cuadro.ordenarCuadrosFechaDesc(lista); break;
            case "precioAsc" : Cuadro.ordenarCuadrosPrecioAsc(lista); break;
            case "precioDesc" : Cuadro.ordenarCuadrosPrecioDesc(lista); break;

        }

        model.addAttribute("cuadros", lista);
        return "cuadrosvendidos_template";
    }

    @RequestMapping("/buscarcuadrovendido")
    public String buscarCuadroVendido(String busqueda, String criterio, Model model){
        List<Cuadro> lista=null;


        switch (criterio){
            case "titulo":
                lista= cuadroRepository.findByTituloContainsIgnoreCase(busqueda);
                break;
            case "comprador":
                lista=cuadroRepository.findByComprador(clienteRepository.findByNombre(busqueda));
                break;
            case "fecha":
                lista=cuadroRepository.findByFechaCompra(Date.valueOf(busqueda));
                break;
            case "precio":
                lista=cuadroRepository.findByPrecio(Integer.parseInt(busqueda));
                break;
        }
        if (lista.isEmpty()){
            String men = "No se ha encontrado ningún cuadro con " + criterio + " " + "\"" + busqueda + "\"";
            model.addAttribute("mensaje", men);
            return "error_msg";
        }
        else if(busqueda.equals("")){
            String mens = "No se ha insertado nada en la barra de búsqueda.";
            model.addAttribute("mensaje", mens);
            return "error_msg";
        }
        else {
            model.addAttribute("cuadros", lista);
            return "cuadrosvendidos_template";
        }
    }

    public String construirCuadro(Cuadro nuevo, String anyo, String nifautor, String nifcliente, String fecha, Model model) {
        if(nuevo.isVendido()){
            if(nifcliente.isBlank()){
                model.addAttribute("mensaje", "No se ha seleccionado ningún comprador.");
                return "error_msg";
            }
            if(fecha.isBlank()){
                model.addAttribute("mensaje", "No se ha seleccionado ninguna fecha válida.");
                return "error_msg";
            }
        }
        System.out.println("nif " + nifautor);
        if(nifautor.isBlank()){
            model.addAttribute("mensaje", "No se ha seleccionado ningún autor.");
            return "error_msg";
        }
        if(nuevo.getDescripcion().length() > 255){
            model.addAttribute("mensaje", "La descripción no puede superar los 255 caracteres.");
            return "error_msg";
        }
        if(anyo.isBlank()){
            nuevo.setAnyoFinalizacion(null);
        }else{
            try{
                nuevo.setAnyoFinalizacion(Integer.parseInt(anyo));
                if (nuevo.getAnyoFinalizacion()<0){
                    String mensaje="Error el año tiene que ser un número positivo";
                    model.addAttribute("mensaje", mensaje);
                    return "error_msg";
                }
            }catch (Exception e){
                model.addAttribute("mensaje", "El año de finalización debe ser un dato numérico.");
                return "error_msg";
            }
        }

        if (nuevo.getAltura()<0){
            String mensaje="Error la altura tiene que ser un número positivo";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }

        if (nuevo.getAnchura()<0){
            String mensaje="Error la anchura tiene que ser un número positivo";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }

        if (nuevo.getPrecio()<0){
            String mensaje="Error el precio tiene que ser un número positivo";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }

        Autor a = autorRepository.findByNIF(nifautor);
        nuevo.setAutor(a);
        Cliente c = new Cliente();
        if(nuevo.isVendido()){
            c = clienteRepository.findByNIF(nifcliente);
            nuevo.setComprador(c);
            nuevo.agregarFecha(fecha);
            c.agregarCuadro(nuevo);
        }
        a.agregarCuadro(nuevo);
        cuadroRepository.save(nuevo);
        autorRepository.save(a);
        if(nuevo.isVendido())
            clienteRepository.save(c);
        return "exito";
    }

}
