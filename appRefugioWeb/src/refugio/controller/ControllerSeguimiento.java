package refugio.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import refugio.model.entities.Adoptante;
import refugio.model.entities.CondicionesEspacio;
import refugio.model.entities.Mascota;
import refugio.model.entities.Seguimiento;
import refugio.model.manager.ManagerSeguimiento;
import refugio.view.util.JSFUtil;

@SessionScoped
@ManagedBean
public class ControllerSeguimiento {
	private int id_seguimiento;
	private Date fecha_seguimiento;
	private Adoptante cedula_adoptante;
	private Mascota id_mascota;
	private CondicionesEspacio id_condiciones_espacio;
	private String observaciones;
	private List<Seguimiento>lista;

	@EJB
	private ManagerSeguimiento managerSeguimiento;
	
	@PostConstruct
	public void iniciar() {
		lista=managerSeguimiento.findAllSeguimientos();
		fecha_seguimiento=new Date();
		id_seguimiento=managerSeguimiento.numeroSeguimiento(id_seguimiento);
		
	}
	
	public void actionListenerAgregarSeguimiento() {
		try {
			managerSeguimiento.agregarSeguimiento(id_seguimiento, fecha_seguimiento, cedula_adoptante, 
												id_mascota, id_condiciones_espacio, observaciones);
			lista=managerSeguimiento.findAllSeguimientos();			
			JSFUtil.crearMensajeInfo("Seguimiento Registrado.");
			} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
			}
	}

	public int getId_seguimiento() {
		return id_seguimiento;
	}

	public void setId_seguimiento(int id_seguimiento) {
		this.id_seguimiento = id_seguimiento;
	}

	public Date getFecha_seguimiento() {
		return fecha_seguimiento;
	}

	public void setFecha_seguimiento(Date fecha_seguimiento) {
		this.fecha_seguimiento = fecha_seguimiento;
	}

	public Adoptante getCedula_adoptante() {
		return cedula_adoptante;
	}

	public void setCedula_adoptante(Adoptante cedula_adoptante) {
		this.cedula_adoptante = cedula_adoptante;
	}

	public Mascota getId_mascota() {
		return id_mascota;
	}

	public void setId_mascota(Mascota id_mascota) {
		this.id_mascota = id_mascota;
	}

	public CondicionesEspacio getId_condiciones_espacio() {
		return id_condiciones_espacio;
	}

	public void setId_condiciones_espacio(CondicionesEspacio id_condiciones_espacio) {
		this.id_condiciones_espacio = id_condiciones_espacio;
	}

	public String getObservaciones() {
		return observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public List<Seguimiento> getLista() {
		return lista;
	}

	public void setLista(List<Seguimiento> lista) {
		this.lista = lista;
	}

	

}
