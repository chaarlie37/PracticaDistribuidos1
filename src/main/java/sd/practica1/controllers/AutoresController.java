package sd.practica1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import sd.practica1.model.Autor;

import sd.practica1.repositories.AutorRepository;



import javax.annotation.PostConstruct;
import java.util.List;

import static org.hibernate.query.criteria.internal.ValueHandlerFactory.isNumeric;


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
    public String guardarAutor(Autor autor, String anyo, Model model){
        if(autorRepository.findByNIF(autor.getNIF())== null) {
            return construirAutor(autor, anyo, model);
        }
        else{
            String mensaje ="Error el autor con NIF " + autor.getNIF() + "ya existe.";
            model.addAttribute("mensaje", mensaje);
            return "error_msg";
        }
    }

    @RequestMapping("/modificarautor")
    public String modificarAutor(@RequestParam Integer id,Autor nuevo, String anyo, Model model){
        nuevo.setId(id);
        return construirAutor(nuevo, anyo, model);
    }

    private String construirAutor(Autor nuevo, String anyo, Model model) {
        if (nuevo.getTlf().equals("")){
            try {
                Integer.parseInt(nuevo.getTlf());
            }catch (Exception e){
                String mensaje = "Error el telefono ha de ser un numero";
                model.addAttribute("mensaje", mensaje);
                return "error_msg";
            }
        }
        if(anyo.isBlank()){
            nuevo.setAnyoNacimiento(null);
        }else{
            try{
                nuevo.setAnyoNacimiento(Integer.parseInt(anyo));
            }catch (Exception e){
                model.addAttribute("mensaje", "El año de finalizacion debe ser un dato numerico.");
                return "error_msg";
            }
        }
        autorRepository.save(nuevo);
        return "exito";
    }

    @RequestMapping("/modificarautorform")
    public String modificarAutorForm(@RequestParam Integer id, Model model){
        Autor autor = autorRepository.getOne(id);
        model.addAttribute("autor", autor);
        return "modificarautorform";
    }



    @RequestMapping("/mostrarautor")
    public String mostrarAutor(@RequestParam Integer id, Model model){
        Autor autor = autorRepository.getOne(id);
        model.addAttribute("autor", autor);
        return "mostrarautor";
    }
    @RequestMapping("/buscarautor")
    public String buscarAutor(String busqueda, String criterio, Model model){
        List<Autor> lista = null;

        switch (criterio){
            case "nombre":
                lista= autorRepository.findByNombreContainsIgnoreCase(busqueda);
                break;
            case "apellidos":
                lista= autorRepository.findByApellidosContainsIgnoreCase(busqueda);
                break;
            case "nif":
                lista= autorRepository.findByNIFContainsIgnoreCase(busqueda);
                break;
            case "anyoNacimiento":
                int m;
                try{
                    m = Integer.parseInt(busqueda);
                }catch (Exception e){
                    m = -1;
                }
                if (m>0) {
                    lista = autorRepository.findByAnyoNacimiento(m);
                    break;
                }
                else{
                    String mensaje = "Error. El año de nacimiento debe ser mayor que 0.";
                    model.addAttribute( "mensaje", mensaje);
                    return "error_msg";
                }
            case "pais":
                lista= autorRepository.findByPaisContainsIgnoreCase(busqueda);
                break;
            case "direccion":
                lista= autorRepository.findByDireccionContainsIgnoreCase(busqueda);
                break;
            case "email":
                lista= autorRepository.findByEmailContainsIgnoreCase(busqueda);
                break;
            case "telefono":
                lista= autorRepository.findByTelefonoContainsIgnoreCase(busqueda);
                break;
        }
        if (lista.isEmpty()){
            String men;
            men = "No se ha encontrado ningun autor con " + criterio + " " + "\"" + busqueda + "\"";
            model.addAttribute("mensaje", men);
            return "error_msg";
        }
        else if(busqueda.equals("")){
            String mens = "No se ha insertado nada en la barra de busqueda.";
            model.addAttribute("mensaje", mens);
            return "error_msg";
        }
        else{
            model.addAttribute("autores", lista);
            return "autores_template";
        }
    }

    @RequestMapping("/mostrarautoresorden")
    public String mostrarautoresorden(@RequestParam(value = "lista")List<Autor> lista, @RequestParam String orden, Model model){
        switch (orden){
            case "nombreAsc" : Autor.ordenarAutoresNombresAsc(lista); break;
            case "nombreDesc" : Autor.ordenarAutoresNombresDesc(lista); break;
            case "apellidosAsc" : Autor.ordenarAutoresApellidosAsc(lista); break;
            case "apellidosDesc" : Autor.ordenarAutoresApellidosDesc(lista); break;
        }
        model.addAttribute("autores" , lista);
        return "autores_template";
    }

}

