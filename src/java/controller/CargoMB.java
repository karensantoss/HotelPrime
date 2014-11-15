package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import modelo.Cargo;
import modelo.persistencia.CargoDAOJPA;
import modelo.persistencia.dao.CargoDao;

/**
 *
 * @author otacilio_gomes
 */
@ManagedBean
public class CargoMB {

    private Cargo cargo;
    private List<Cargo> cargos;

    public CargoMB() {
        cargo = new Cargo();
    }

    public String insere() {
        EntityManager manager = this.getManager();
        CargoDao dao = new CargoDAOJPA(manager);
        dao.save(cargo);
        cargo = new Cargo();
        cargos = null;
        return "/paginas/listacargos.xhtml";
    }

    public String preparaAlteracao() {
          EntityManager manager = this.getManager();
        CargoDao dao = new CargoDAOJPA(manager);
        dao.getById(Cargo.class,this.cargo.getCodigo());
       
        return "/paginas/cargo.xhtml";
        
        
    }

    public void remove() {
        EntityManager manager = this.getManager();
        CargoDao dao = new CargoDAOJPA(manager);
        dao.remove(Cargo.class,this.cargo.getCodigo());
        this.cargos = null;
    }
    
    
    private EntityManager getManager(){
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest)ec.getRequest();
        return (EntityManager)request.getAttribute("EntityManager");
    }
            

    public void setCargo(Cargo cargo) {
        this.cargo = cargo;
    }

    public List<Cargo> getcargos() {
        return cargos;
    }

    public List<Cargo> getCargos() {
        if(cargos == null){
            EntityManager manager  = this.getManager();
            CargoDao dao = new CargoDAOJPA(manager);
            cargos = dao.getAll(Cargo.class);
        }
        return cargos;
    }

    public void setCargos(List<Cargo> cargos) {
        this.cargos = cargos;
    }

    public Cargo getCargo() {
        return cargo;
    }
    
}
