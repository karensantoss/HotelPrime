package modelo.persistencia.dao;

import java.util.List;


/**
 *
 * @author otacilio_gomes
 */

public interface DAO <T,I>  {
    
    void save(T entity);
    void remove(Class<T> classe, I pk);
    T getById(Class<T> classe , I pk); //Pesquisa por id
    List<T> getAll (Class<T> classe);

    
}
