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

/**
 * @author otacilio_gomes
 */

public class JPAFilter implements Filter {

    private EntityManagerFactory factory;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.factory = Persistence.createEntityManagerFactory("ExemploFiltroPU");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        EntityManager manager = this.factory.createEntityManager();
        request.setAttribute("EntityManager", manager);//nome dado para o gerenciador de entidades
        manager.getTransaction().begin();
        chain.doFilter(request, response);//controle dos filtros
        try {
            manager.getTransaction().commit();

        } catch (Exception e) {
            manager.getTransaction().rollback();
        } finally {
            manager.close();
        }

    }

    @Override
    public void destroy() {
        this.factory.close();

    }
}
