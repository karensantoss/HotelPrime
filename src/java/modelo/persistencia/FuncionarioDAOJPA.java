/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import modelo.Funcionario;
import modelo.persistencia.dao.FuncionarioDao;

/**
 *
 * @author otacilio_gomes
 */
public class FuncionarioDAOJPA extends DAOJPA<Funcionario,Integer> implements FuncionarioDao{
    private EntityManager maneger;
    
    public FuncionarioDAOJPA(EntityManager maneger) {
        super(maneger);
        this.maneger = maneger;
    }

    @Override
    public boolean login(String login, String senha) {
        Query q = this.maneger.createQuery
                ("select f from Funcionario f where "
                + "f.login = :login and "
                + "f.senha = :senha", Funcionario.class);
        q.setParameter("login", login);
        q.setParameter("senha", senha);
        if(q.getResultList().size()> 0){
            return true;
        }else{
            return false;
        }
    }
    
}
