
package modelo.persistencia;

import javax.persistence.EntityManager;
import modelo.Cargo;
import modelo.persistencia.dao.CargoDao;

/**
 *
 * @author otacilio_gomes
 */
public class CargoDAOJPA extends DAOJPA<Cargo,Integer> implements CargoDao{

    public CargoDAOJPA(EntityManager maneger) {
        super(maneger);
    }
    
}
