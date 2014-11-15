package controller;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modelo.persistencia.FuncionarioDAOJPA;
import modelo.persistencia.dao.FuncionarioDao;

/**
 *
 * @author otacilio_gomes
 */
@ManagedBean
@SessionScoped
public class LoginMB {

   private String login;
   private String senha;
   
   public String autentica(){
       FacesContext fc = FacesContext.getCurrentInstance();
       EntityManager manager = this.getManager();
       FuncionarioDao dao = new FuncionarioDAOJPA(manager);
       if(dao.login(login, senha)){
           ExternalContext ec = fc.getExternalContext();
           HttpSession session = (HttpSession)ec.getSession(false);
           session.setAttribute("usuarioLogado", true);
           return "/paginas/cargo.xhtml";
           
       }else{
           FacesMessage fm = new FacesMessage("Usuário e/ou senha "
                   + "inválidos");
           fm.setSeverity(FacesMessage.SEVERITY_ERROR);
           fc.addMessage(null, fm);
           return "/login";
       }
   }
   
   public String registraSaida(){
       FacesContext fc = FacesContext.getCurrentInstance();
       ExternalContext ec = fc.getExternalContext();
       HttpSession session= (HttpSession)ec.getSession(false);
       session.removeAttribute("usuario");
       return "/login";
   }

    private EntityManager getManager() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        HttpServletRequest request = (HttpServletRequest) ec.getRequest();
        return (EntityManager) request.getAttribute("EntityManager");
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    

 }
