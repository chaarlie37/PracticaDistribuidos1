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
        List<Cuadro> lista= cuadroRepository.findAll();
        for (int i=0; i<lista.size(); i++){
            if (lista.get(i).isVendido())
                lista.remove(i);
        }
        model.addAttribute("cuadrosvendidos",lista );
        return "cuadrosvendidos_template";
    }

    @RequestMapping("/modificarcuadroform")
    public String modificarCuadroForm(@RequestParam String titulo, Model model){
        Cuadro cuadro = cuadroRepository.findByTitulo(titulo);
        model.addAttribute("cuadro", cuadro);
        model.addAttribute("autores", autorRepository.findAll());
        model.addAttribute("clientes", clienteRepository.findAll());
        return "modificarcuadroform";
    }

    @RequestMapping("/guardarcuadro")
    public String guardarCuadro(Cuadro cuadro, String nombreautor, Model model){
        Autor a = autorRepository.findByNIF(nombreautor);
        cuadro.setAutor(a);
        a.agregarCuadro(cuadro);
        cuadroRepository.save(cuadro);
        autorRepository.save(a);
        return "exito";
    }

    @RequestMapping("/nuevocuadro")
    public String nuevoCuadro(Model model){
        model.addAttribute("autores", autorRepository.findAll());
        return "nuevo_cuadro";
    }

    @RequestMapping("/mostrarcuadro")
    public String mostrarCuadro(@RequestParam String titulo, Model model){
        Cuadro cuadro = cuadroRepository.findByTitulo(titulo);
        model.addAttribute("cuadro", cuadro);
        return "mostrarcuadro";
    }


    @RequestMapping("/modificarcuadro")
    public String modificarCuadro(Integer id, Cuadro nuevo, String nifautor, Model model) {
        Autor a = autorRepository.findByNIF(nifautor);
        nuevo.setAutor(a);
        a.agregarCuadro(nuevo);
        cuadroRepository.save(nuevo);
        autorRepository.save(a);
        return "exito";


    }
        /*
    public String guardarCuadro(Cuadro cuadro, Model model){
        if(cuadroRepository.findByTitulo(cuadro.getTitulo()) == null) {
            cuadroRepository.save(cuadro);
            return "exito";
        }
        else{
            String mensaje = "Error. El cuadro con titulo " + cuadro.getTitulo() + "ya existe. ";
            model.addAttribute("mensaje" , mensaje);
            return "error_msg";
        }
    }

    @RequestMapping("/modificarcuadro")
    public String modificarCuadro(@RequestParam Integer id, Cuadro nuevo, Model model){
        nuevo.setId(id);
        cuadroRepository.save(nuevo);
        return "exito";
    }


*/

    @RequestMapping("/buscarcuadro")
    public String buscarCuadro(String busqueda, String criterio, Model model){
        List<Cuadro> lista=null;


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
        if (lista.isEmpty()){
            String men = "No se ha encontrado ningun cuadro con " + criterio + " " + "\"" + busqueda + "\"";
            model.addAttribute("mensaje", men);
            return "error_msg";
        }
        else if(busqueda.equals("")){
            String mens = "No se ha insertado nada en la barra de busqueda.";
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
            String men = "No se ha encontrado ningun cuadro con " + criterio + " " + "\"" + busqueda + "\"";
            model.addAttribute("mensaje", men);
            return "error_msg";
        }
        else if(busqueda.equals("")){
            String mens = "No se ha insertado nada en la barra de busqueda.";
            model.addAttribute("mensaje", mens);
            return "error_msg";
        }
        else {
            model.addAttribute("cuadros", lista);
            return "cuadros_template";
        }
    }

}
