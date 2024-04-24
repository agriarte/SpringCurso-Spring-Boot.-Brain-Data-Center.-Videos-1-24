package com.example.controllers;

import com.example.dao.IindividuoDao;
import com.example.domain.Individuo;
import com.example.servicio.IIndividuoServicio;
import jakarta.validation.Valid;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@Slf4j
public class ControladorCrud {

    //inyección de la clase Individuao para hacer uso de ella en toda la app
    @Autowired
    private IindividuoDao individuoDao;

    @GetMapping("/video12")
    public String mostrarBBDD(Model model) {

        // en java 17 se puede poner var individuos = individuoDao.findAll(); porque var adapta el tipo al valor que vas a asignar.
        Iterable<Individuo> individuos = individuoDao.findAll();

        model.addAttribute("individuos", individuos);

        return "indice_video12";
    }

    @Autowired
    private IIndividuoServicio iindividioServicio;

    @GetMapping("/video14")
    public String mostrarBBDDServicio(Model model) {

        // en java 17 se puede poner var individuos = individuoDao.findAll(); porque var adapta el tipo al valor que vas a asignar.
        List<Individuo> individuos = iindividioServicio.listaIndividuos();

        model.addAttribute("individuos", individuos);

        return "indice_video12";
    }

    // ---------- CRUD -----------
    @GetMapping("/video15")
    public String mostrarCRUD(Model model) {

        // en java 17 se puede poner var individuos = individuoDao.findAll(); porque var adapta el tipo al valor que vas a asignar.
        List<Individuo> individuos = iindividioServicio.listaIndividuos();

        model.addAttribute("individuos", individuos);

        return "indice_video15_crud";
    }

    @GetMapping("/anexar")
    public String anexar(Individuo individuo, Model model) {
        model.addAttribute("titulo", "Nuevo individuo");
        return "cambios";
    }

    @PostMapping("/salvar")
    public String salvar(@Valid Individuo individuo, Errors error, Model model) {
        if (error.hasErrors()) {
        // Si hay errores, establece el título como "Modificar individuo" o "Nuevo individuo" dependiendo de si el individuo tiene un ID o no
        String titulo = (individuo.getId() != null) ? "Modificar individuo" : "Nuevo individuo";
        model.addAttribute("titulo", titulo); // Agregar el título al modelo
        return "cambios"; // Volver a la vista de cambios
    }
        iindividioServicio.salvar(individuo);
        return "redirect:/video15";
    }

    @GetMapping("/modificar/{id}")
    public String modificar(Individuo individuo, Model model) {
        individuo = iindividioServicio.buscarIndividuo(individuo);
        model.addAttribute("titulo", "Modificar individuo");
        model.addAttribute("individuo", individuo);
        return "cambios";
    }

    @GetMapping("/borrar/{id}")
    public String borrar(Individuo individuo, Model model) {
        iindividioServicio.borrar(individuo);
        return "redirect:/video15";
    }

    @GetMapping("/borrar_queryparameter")
    public String borrar(Individuo individuo) {
        iindividioServicio.borrar(individuo);
        return "redirect:/video15";
    }
    
//    @GetMapping("/accesodenegado403")
//    public String mostrarError403()
//    {
//        return "error403";
//    }
    
}
