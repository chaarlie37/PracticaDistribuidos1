package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sd.practica1.model.Cuadro;
import sd.practica1.repositories.AutorRepository;
import sd.practica1.repositories.CuadroRepository;


import javax.annotation.PostConstruct;

@Controller
public class CuadrosController {

    @Autowired
    private CuadroRepository cuadroRepository;
    @Autowired
    private AutorRepository autorRepository;

    @PostConstruct
    public void init(){

        Cuadro c1 = new Cuadro("Titulo1","aaa","aaa",30,30,100);
        Cuadro c2 = new Cuadro("Titulo2","bbb","bbb",40,40,200);
        Cuadro c3 = new Cuadro("Titulo3","ccc","ccc",50,50,300);
        Cuadro c4 = new Cuadro("Titulo4","ddd","ddd",60,60,400);
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
        return "modificarcuadroform";
    }

    @RequestMapping("/guardarcuadro")
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

    @RequestMapping("/mostrarcuadro")
    public String mostrarCuadro(@RequestParam String titulo, Model model){
        Cuadro cuadro = cuadroRepository.findByTitulo(titulo);
        model.addAttribute("cuadro", cuadro);
        return "mostrarcuadro";
    }

}
