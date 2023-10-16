package ar.edu.utn.bda.c3k4.holamundospringinitializr;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
public class HolaController {

    @GetMapping("/")
    public String holaMundo() {
        return "Hola, mundo. Son las " + new Date().toLocaleString();
    }
}
