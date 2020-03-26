package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sd.practica1.model.Autor;
import sd.practica1.repositories.AutorRepository;

import javax.annotation.PostConstruct;

@Controller
public class AutoresController {

    @Autowired
    private AutorRepository autorRepository;

    @PostConstruct
    public void init(){

        Autor a1 = new Autor("Fran","aaa","aaa",1111,"aaa","aaa","aaa","aaa");
        Autor a2 = new Autor("Margarita","bbb","bbb",2222,"bbb","bbb","bbb","bbb");
        Autor a3 = new Autor("Lidia","ccc","ccc",3333,"ccc","ccc","ccc","ccc");
        Autor a4 = new Autor("Sr. Croqueta","ddd","ddd",4444,"ddd","ddd","ddd","ddd");
        autorRepository.save(a1);
        autorRepository.save(a2);
        autorRepository.save(a3);
        autorRepository.save(a4);


    }

    @RequestMapping("/autores")
    public String inicio(Model model){
        model.addAttribute("autores", autorRepository.findAll());
        return "autores_template";
    }

    @RequestMapping("/guardarautor")
    public String guardarAutor(Autor autor, Model model){
        autorRepository.save(autor);
        return "exito";
    }
}
