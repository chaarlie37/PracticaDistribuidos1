package sd.practica1.controllers;

import org.dom4j.rule.Mode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sd.practica1.model.Cliente;
import sd.practica1.repositories.ClienteRepository;

import javax.annotation.PostConstruct;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
public class ClientesController {

    @Autowired
    private ClienteRepository clienteRepository;

    @PostConstruct
    public void init(){
        Cliente c1 = new Cliente("Carlos","Sanchez Muñoz","987654321A","Calle Aaaaaaa, 327","direccion@gmail.com","123456789");
        Cliente c2 = new Cliente("Francisco","Pedraja Alonso","789456123B","Calle Kkkkkkkk, 27, Aranjuez","tonto@gmail.com","254987321");
        Cliente c3 = new Cliente("Lidia","Hernandez Calvo","123456789N","Calle Cccccccccc, 72, Valdemoro","rubia@gmail.com","456987123");
        Cliente c4 = new Cliente("Willyrex","Señor Youtuber","951753654P","Calle lo que Sea, 77, Andorra","manco@machinima.com","777777777");
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
    public String mostrarCliente(@RequestParam Integer id, Model model){
        Cliente cliente = clienteRepository.getOne(id);
        model.addAttribute("cliente", cliente);
        return "mostrar_cliente";
    }

    @RequestMapping("/modificarclienteform")
    public String modificarClienteForm(@RequestParam Integer id, Model model){
        Cliente cliente = clienteRepository.getOne(id);
        model.addAttribute("cliente", cliente);
        return "modificarclienteform";
    }

    @RequestMapping("/modificarcliente")
    public String modificarCliente(@RequestParam Integer id, Cliente nuevo, Model model){
        nuevo.setId(id);
        clienteRepository.save(nuevo);
        return "exito";
    }

    @RequestMapping("/buscarcliente")
    public String buscarCliente(String busqueda, String criterio, Model model){
        List<Cliente> lista = null;

        switch (criterio){
            case "nombre" : lista = clienteRepository.findByNombreContainsIgnoreCase(busqueda); break;
            case "apellidos" : lista = clienteRepository.findByApellidosContainsIgnoreCase(busqueda); break;
            case "nif" : lista = clienteRepository.findByNIFContainsIgnoreCase(busqueda); break;
            case "direccion" : lista = clienteRepository.findByDireccionContainsIgnoreCase(busqueda); break;
            case "email" : lista = clienteRepository.findByEmailContainsIgnoreCase(busqueda); break;
            case "telefono" : lista = clienteRepository.findByTelefonoContainsIgnoreCase(busqueda); break;
        }
        if(lista.isEmpty()){
            String mensaje;
            if(criterio.equals("todo"))
                mensaje = "No se ha encontrado ningun cliente que coincida con " +  "\"" + busqueda + "\"";
            else
                mensaje = "No se ha encontrado ningun cliente con " + criterio + " " + "\"" + busqueda + "\"";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }
        else if(busqueda.equals("")){
            String mensaje = "No se ha insertado nada en la barra de busqueda.";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }
        else{
            model.addAttribute("clientes", lista);
            return "clientes_template";
        }
    }

    @RequestMapping("/mostrarclientesorden")
    public String mostrarclientesorden(@RequestParam(value = "lista") List<Cliente> lista, @RequestParam String orden, Model model){

        switch (orden){
            case "nombreAsc" : Cliente.ordenarClientesNombreAsc(lista); break;
            case "nombreDesc" : Cliente.ordenarClientesNombreDesc(lista); break;
            case "apellidosAsc" : Cliente.ordenarClientesApellidoAsc(lista); break;
            case "apellidosDesc" : Cliente.ordenarClientesApellidoDesc(lista); break;
        }

        model.addAttribute("clientes", lista);
        return "clientes_template";
    }


}
