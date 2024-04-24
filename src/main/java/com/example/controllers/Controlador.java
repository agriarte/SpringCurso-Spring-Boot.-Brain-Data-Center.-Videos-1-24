package com.example.controllers;

import com.example.domain.Individuo;
import java.util.Arrays;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@Slf4j // anotación de Lombok para crear logs
public class Controlador {

    @Value("${indice.hola}")
    private String txtPrope;
    
    

    // mapeo a índice
    @GetMapping("/")
    public String mostrarInicio() {
        return "inicio";
    }

     @GetMapping("/video06")
    public String mostrarLista(Model model) {
        log.info("Estoy ejecutando el controlador MVC. Mensaje creado con log.info");
        log.debug("Log DEBUG Más info sacada por log.info");

        String hola = "Esto es un String en el controlador";
        model.addAttribute("hola", hola);
        model.addAttribute("txtPrope", txtPrope);

        // Crear una instancia de Individuo usando Lombok
        Individuo miIndividuo = new Individuo();
        miIndividuo.setNombre("Pedro"); // Establecer el nombre usando el setter generado por Lombok
        miIndividuo.setApellido("Martín");
        miIndividuo.setEdad("9 años");
        miIndividuo.setCorreo("pmarin@example.com");
        miIndividuo.setTelefono("933755433");

        model.addAttribute("individuo", miIndividuo);

        return "indice_video06";
    }
    @GetMapping("/video01")
    public String mostrarVariables(Model model) {
        log.info("Estoy ejecutando el controlador MVC. Mensaje creado con log.info");
        log.debug("Log DEBUG Más info sacada por log.info");

        String hola = "Esto es un String en el controlador";
        model.addAttribute("hola", hola);
        model.addAttribute("txtPrope", txtPrope);
        return "indice_video01";
    }

    @GetMapping("/video08")
    public String mostrarLista2(Model model) {
        // Crear una instancia de Individuo usando Lombok
        Individuo miIndividuo = new Individuo();
        miIndividuo.setNombre("Pedro"); // Establecer el nombre usando el setter generado por Lombok
        miIndividuo.setApellido("Martín");
        miIndividuo.setEdad("9 años");
        miIndividuo.setCorreo("pmarin@example.com");
        miIndividuo.setTelefono("933755433");

        // Crear una instancia de Individuo usando Lombok
        Individuo miIndividuo2 = new Individuo();
        miIndividuo2.setNombre("Juan"); // Establecer el nombre usando el setter generado por Lombok
        miIndividuo2.setApellido("Díaz");
        miIndividuo2.setEdad("19 años");
        miIndividuo2.setCorreo("jdiaz@example.com");
        miIndividuo2.setTelefono("93544232");

        //Crear Objeto lista de individuos
        List misIndividuos = Arrays.asList(miIndividuo, miIndividuo2);
        //List misIndividuos = Arrays.asList(); array vacío para probar en la vista el if si no hay datos 
        model.addAttribute("individuos", misIndividuos);

        return "indice_video08";
    }
   
    

}
