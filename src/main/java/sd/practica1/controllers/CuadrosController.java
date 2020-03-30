package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sd.practica1.model.Autor;
import org.springframework.web.bind.annotation.RequestParam;
import sd.practica1.model.Cuadro;
import sd.practica1.repositories.AutorRepository;
import sd.practica1.repositories.CuadroRepository;


import javax.annotation.PostConstruct;
import java.util.List;

@Controller
public class CuadrosController {

    @Autowired
    private CuadroRepository cuadroRepository;
    @Autowired
    private AutorRepository autorRepository;

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

    @RequestMapping("/modificarcuadroform")
    public String modificarCuadroForm(@RequestParam String titulo, Model model){
        Cuadro cuadro = cuadroRepository.findByTitulo(titulo);
        model.addAttribute("cuadro", cuadro);
        model.addAttribute("autores", autorRepository.findAll());
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
            case "todo":
                break;
            case "titulo":
                lista= cuadroRepository.findByTituloContainsIgnoreCase(busqueda);
                break;
            case "descripcion":
                lista=cuadroRepository.findByDescripcionIgnoreCase(busqueda);
                break;
            case "anyoFinalizacion":
                break;
            case "anchura":
                break;
            case "altura":
                break;
            case "precio":
                break;
        }
        if (lista.isEmpty()){
            String men;
            if (criterio.equals("todo"))
                men = "No se ha encontrado ningun cuadro que coincida con " +  "\"" + busqueda + "\"";
            else
                men = "No se ha encontrado ningun cuadro con " + criterio + " " + "\"" + busqueda + "\"";
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
