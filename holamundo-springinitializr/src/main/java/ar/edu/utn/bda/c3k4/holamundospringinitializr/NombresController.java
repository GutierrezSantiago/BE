package ar.edu.utn.bda.c3k4.holamundospringinitializr;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class NombresController {
    //asi no se hace en la vida real, pero hacemos un atributo
    private List<String> nombres = new ArrayList<>();

    // Retorna todos los nombres cargados
    @GetMapping("/nombres")
    public List<String> consultarTodos(){
        if (nombres.isEmpty()) {nombres.add("Santi"); nombres.add("Hernan");}
        return nombres;
    }

    // Agregar un nombre recibido como parametro (Query param)
    @PostMapping("/nombres")
    public void agregarNombre(@RequestParam String nombre){
        nombres.add(nombre);
    }

    // Retorna una lista con los nombres iguales al buscado indicado como
    // parametro (Path variable)
    @GetMapping("/nombres/{buscado}")
    public List<String> buscarNombre(@PathVariable String buscado){
        return nombres.stream().filter(n -> n.equals(buscado)).toList();

    }

}
