package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import sd.practica1.model.Cliente;
import sd.practica1.repositories.ClienteRepository;

import javax.annotation.PostConstruct;

@Controller
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    public void init(){
        Cliente c1 = new Cliente("Cliente1");
        Cliente c2 = new Cliente("Cliente2");
        Cliente c3 = new Cliente("Cliente3");
        Cliente c4 = new Cliente("Cliente4");
        clienteRepository.save(c1);
        clienteRepository.save(c2);
        clienteRepository.save(c3);
        clienteRepository.save(c4);
    }

    @RequestMapping("/clientes")
    public String inicio(Model model){
        model.addAttribute("clientes", clienteRepository.findAll());
        return "clientes_template";
    }

}
