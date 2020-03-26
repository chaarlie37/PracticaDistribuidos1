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
        Cliente c1 = new Cliente("Cliente1","aaa","aaa","aaa","aaa","aaa");
        Cliente c2 = new Cliente("Cliente2","bbb","bbb","bbb","bbb","bbb");
        Cliente c3 = new Cliente("Cliente3","ccc","ccc","ccc","ccc","ccc");
        Cliente c4 = new Cliente("Cliente4","ddd","ddd","ddd","ddd","ddd");
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

    @RequestMapping("/guardarcliente")
    public String guardarCliente(Cliente cliente,Model model){
        clienteRepository.save(cliente);
        return "exito";
    }
}
