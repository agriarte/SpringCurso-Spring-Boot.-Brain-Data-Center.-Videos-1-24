package com.example.servicio;

import com.example.domain.Individuo;
import java.util.List;

public interface IIndividuoServicio {

    public List<Individuo> listaIndividuos();

    public void salvar(Individuo individuo);

    public void borrar(Individuo individuo);

    public Individuo buscarIndividuo(Individuo individuo);

}
