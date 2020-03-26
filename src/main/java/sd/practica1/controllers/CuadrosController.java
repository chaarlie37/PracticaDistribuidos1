package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
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

    @RequestMapping("/guardarcuadro")
    public String guardarCuadro(Cuadro cuadro, Model model){
        cuadroRepository.save(cuadro);
        return "exito";
    }


}
