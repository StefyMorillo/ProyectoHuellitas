package refugio.model.manager;

import java.util.Date;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import refugio.model.entities.Adoptante;
import refugio.model.entities.CondicionesEspacio;
import refugio.model.entities.Mascota;
import refugio.model.entities.Seguimiento;

/**
 * Session Bean implementation class BeanSeguimiento
 */
@Stateless
@LocalBean

public class ManagerSeguimiento {
	@PersistenceContext(unitName = "RefugioDS")
	private EntityManager em;

	public ManagerSeguimiento() {
        // TODO Auto-generated constructor stub
    }
    
    public void agregarSeguimiento(int id_seguimiento,Date fecha_seguimiento,Adoptante cedula_adoptante, 
    		Mascota id_mascota,CondicionesEspacio id_condiciones_espacio, String observaciones) throws Exception{
    		Seguimiento s=new Seguimiento();
    		if(em.find(Seguimiento.class, id_seguimiento) != null)
    			throw new Exception("El número de seguimiento ya existe.");
    		s.setIdSeguimiento(id_seguimiento);
    		s.setFechaSeguimiento(fecha_seguimiento);
    		s.setAdoptante(cedula_adoptante);
    		s.setMascota(id_mascota);
    		s.setCondicionesEspacio(id_condiciones_espacio);
    		s.setObservaciones(observaciones);
    		em.persist(s);
    		}
    
    public List<Seguimiento> findAllSeguimientos() {	
		Query t;
		List<Seguimiento> lista;
		String sentenciaSQL;
		sentenciaSQL = "SELECT s FROM Seguimiento s ORDER BY s.idSeguimiento";
		t = em.createQuery(sentenciaSQL);
		lista = t.getResultList();
		return lista;
	}
    
    public int numeroSeguimiento(int id_seguimiento) {
    	Query t;
		t = em.createQuery("SELECT max(idSeguimiento) FROM Seguimiento");
		id_seguimiento = t.getFirstResult();
    	return id_seguimiento;
    }
}
