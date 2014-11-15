/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.persistencia.dao;

import modelo.Funcionario;

/**
 *
 * @author otacilio_gomes
 */
public interface FuncionarioDao extends DAO<Funcionario,Integer>{
    
    boolean login(String login, String senha);
}
