package com.example.servicio;

import com.example.dao.IindividuoDao;
import com.example.domain.Individuo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

// La anotación @Service en Spring se utiliza para marcar una clase como un bean de servicio. Un bean de servicio es simplemente una clase 
// que realiza operaciones relacionadas con la lógica de negocio de una aplicación. 
@Service
public class IndividuoServicio implements IIndividuoServicio {
    
    @Autowired
    private IindividuoDao IindividuoDao;
    // @Transactional indica que todas las operaciones dentro de ese método deben ejecutarse dentro de una transacción. Esto significa que si una operación falla, 
    // se revertirán todas las operaciones anteriores dentro de la misma transacción.
    // La opción readOnly = true indica que la transacción se utilizará solo para operaciones de lectura y no se realizarán modificaciones en la base de datos.
    @Override
    @Transactional(readOnly = true)
    public List<Individuo> listaIndividuos() {
        return (List<Individuo>) IindividuoDao.findAll();
        
    }
    
    @Override
    @Transactional
    public void salvar(Individuo individuo) {
        IindividuoDao.save(individuo);
    }
    
    @Override
    @Transactional
    public void borrar(Individuo individuo) {
        IindividuoDao.delete(individuo);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Individuo buscarIndividuo(Individuo individuo) {
        //devolver individuo encontrado y si no existe devolver null
        return IindividuoDao.findById(individuo.getId()).orElse(null);
    }
    
}
