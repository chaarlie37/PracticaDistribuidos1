package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sd.practica1.repositories.AutorRepository;

@Controller
public class AutoresController {

    @Autowired
    private AutorRepository autorRepository;

    @RequestMapping("/autores")
    public String inicio(Model model){
        model.addAttribute("autores", autorRepository);
        return "autores_template";
    }

}
