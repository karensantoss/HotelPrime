package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import modelo.Cargo;
import modelo.Funcionario;
import modelo.persistencia.CargoDAOJPA;
import modelo.persistencia.FuncionarioDAOJPA;
import modelo.persistencia.dao.CargoDao;
import modelo.persistencia.dao.FuncionarioDao;

/**
 *
 * @author otacilio_gomes
 */
@ManagedBean
public class FuncionarioMB {

    private Funcionario funcionario;
    private List<Funcionario> funcionarios;
    private int cargoId;

    public FuncionarioMB() {
        funcionario = new Funcionario();

    }

    public String insere() {
        EntityManager manager = this.getManager();
        FuncionarioDao dao = new FuncionarioDAOJPA(manager);
        if (this.cargoId !=0){
        CargoDao cDao = new CargoDAOJPA(manager);
        Cargo cargo = cDao.getById(Cargo.class, cargoId);
        this.funcionario.setCargo(cargo);
    }
        dao.save(funcionario);
        funcionario = new Funcionario();
        funcionarios = null;
        return "/paginas/listafuncionarios.xhtml";
    }

    public String preparaAlteracao() {
        EntityManager manager = this.getManager();
        FuncionarioDao dao = new FuncionarioDAOJPA(manager);
        dao.getById(Funcionario.class, this.funcionario.getCodigo());

        return "/paginas/funcionario.xhtml";


    }

    public void remove() {
        EntityManager manager = this.getManager();
        FuncionarioDao dao = new FuncionarioDAOJPA(manager);
        dao.remove(Funcionario.class, this.funcionario.getCodigo());
        this.funcionarios = null;
    }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public void setFuncionario(Funcionario funcionario) {
        this.funcionario = funcionario;
    }

    public List<Funcionario> getfuncionarios() {
        return funcionarios;
    }

    public List<Funcionario> getFuncionarios() {
        if (funcionarios == null) {
            EntityManager manager = this.getManager();
            FuncionarioDao dao = new FuncionarioDAOJPA(manager);
            funcionarios = dao.getAll(Funcionario.class);
        }
        for (Funcionario funcionario1 : funcionarios) {
            System.out.println("Funcionarios" + funcionario1);
        }
        
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public Funcionario getFuncionario() {
        return funcionario;
    }

    public int getCargoId() {
        return cargoId;
    }

    public void setCargoId(int cargoId) {
        this.cargoId = cargoId;
    }
}
