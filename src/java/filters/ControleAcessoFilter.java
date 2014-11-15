package filters;

import java.io.IOException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author otacilio_gomes
 */

public class ControleAcessoFilter implements Filter {

    private EntityManagerFactory factory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
       
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        try{
            HttpServletRequest httpReq =
                    (HttpServletRequest)request;
            HttpServletResponse httpResp = 
                    (HttpServletResponse)response;
            HttpSession session = httpReq.getSession(true);
            String url = httpReq.getRequestURL().toString(); 
            //Se o usuário não estiver autenticado
            if(session.getAttribute("usuarioLogado") == null &&
                    precisaAutenticar(url)){
                httpResp.sendRedirect(httpReq.getContextPath() +
                        "/faces/login.xhtml");
            }else{
                chain.doFilter(request, response);
            }
            
            
        }catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void destroy() {
     
    }

    private boolean precisaAutenticar(String url) {
       return !url.contains("login.xhtml") 
               &&(!url.contains("javax.faces.resource"));
    }
}
