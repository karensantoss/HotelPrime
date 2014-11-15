package modelo.persistencia;


import java.util.List;
import javax.persistence.*;
import javax.swing.JOptionPane;
import modelo.persistencia.dao.DAO;

/**
 *
 * @author otacilio_gomes
 */
//Códigos Genéricos
public class DAOJPA<T, I> implements DAO<T, I> {

    private EntityManager maneger;

    public DAOJPA(EntityManager maneger) {
        this.maneger = maneger;
    }

    @Override
    public void save(T entity) {
  this.maneger.merge(entity);
    }

    @Override
    public void remove(Class<T> classe, I pk) {
       this.maneger.remove(this.maneger.getReference(classe, pk));
        
    }
       

    @Override
    public T getById(Class<T> classe, I pk) {
        try {
            return this.maneger.find(classe, pk);
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<T> getAll(Class<T> classe) {
        Query q = this.maneger.createQuery("select x from "+classe.getSimpleName()+" x ");
        return q.getResultList();
}

}
