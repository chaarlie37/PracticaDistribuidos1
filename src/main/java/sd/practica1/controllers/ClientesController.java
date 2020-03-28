package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sd.practica1.model.Cliente;
import sd.practica1.repositories.ClienteRepository;

import javax.annotation.PostConstruct;
import java.util.Optional;

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
        if(clienteRepository.findByNIF(cliente.getNIF()) == null){
            clienteRepository.save(cliente);
            return "exito";
        }
        else{
            String mensaje = "Error: El cliente con NIF " + cliente.getNIF() + " ya existe.";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }

    }

    @RequestMapping("/mostrarcliente")
    public String mostrarCliente(@RequestParam String nif, Model model){
        Cliente cliente = clienteRepository.findByNIF(nif);
        model.addAttribute("cliente", cliente);
        return "mostrar_cliente";
    }

    @RequestMapping("/modificarclienteform")
    public String modificarClienteForm(@RequestParam String nif, Model model){
        Cliente cliente = clienteRepository.findByNIF(nif);
        model.addAttribute("cliente", cliente);
        return "modificarclienteform";
    }

    @RequestMapping("/modificarcliente")
    public String modificarCliente(@RequestParam Integer id, Cliente nuevo, Model model){
        nuevo.setId(id);
        clienteRepository.save(nuevo);
        return "exito";
    }
}
