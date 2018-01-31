package refugio.controller;

import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import refugio.model.entities.CondicionesMascota;
import refugio.model.entities.EspecieRaza;
import refugio.model.entities.Mascota;

import refugio.model.manager.ManagerRefugio;
import refugio.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerMascota {

	private String caracteristicas_mascota;
	private int id_mascota;
	private int edad_mascota;
	private Date fecha_ingreso_mascota;
	private String observaciones_mascota;
	private String sexo_mascota;
	private int id_condiciones_mascota;
	private int id_especie_raza;
	private List<Mascota> listaMascotas;
	private List<CondicionesMascota> listaCondicones;
	private List<EspecieRaza> listaEspecieRaza;
	@EJB
	private ManagerRefugio managerRefugio;

	@PostConstruct
	public void iniciar() {
		listaMascotas = managerRefugio.findAllMascota();
		listaCondicones = managerRefugio.findAllCondicionesMascota();
		listaEspecieRaza =  managerRefugio.findAllEspecieRaza();
	}
	public void actionRegistrar() {
    try {
    	managerRefugio.registrarMascota(caracteristicas_mascota, edad_mascota, 
    			observaciones_mascota, sexo_mascota, id_condiciones_mascota, id_especie_raza);
    	JSFUtil.crearMensajeInfo("Registrado Exitosamente");
    	listaMascotas = managerRefugio.findAllMascota();
    	listaCondicones = managerRefugio.findAllCondicionesMascota();
		listaEspecieRaza =  managerRefugio.findAllEspecieRaza();

		
	} catch (Exception e) {
		e.printStackTrace();
		JSFUtil.crearMensajeError(e.getMessage());
	}
	}
	
	public String ActualizarMascota() {
		String direccion = null;
		try {

			managerRefugio.ActualizarMascota(id_mascota, caracteristicas_mascota, edad_mascota, 
	    			observaciones_mascota, sexo_mascota, id_condiciones_mascota, id_especie_raza);
    			
			JSFUtil.crearMensajeInfo("Sus datos han sido actualizados");
			listaMascotas = managerRefugio.findAllMascota();
			listaCondicones = managerRefugio.findAllCondicionesMascota();
			listaEspecieRaza =  managerRefugio.findAllEspecieRaza();
			direccion= "ListaMascota";

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return direccion;

	}

	public String cargarMascota(Mascota m) {
		id_mascota=m.getIdMascota();
		caracteristicas_mascota = m.getCaracteristicasMascota();
		edad_mascota = m.getEdadMascota();
		fecha_ingreso_mascota = m.getFechaIngresoMascota();
		observaciones_mascota = m.getObservacionesMascota();
		sexo_mascota = m.getSexoMascota();
		id_condiciones_mascota = m.getCondicionesMascota().getIdCondicionesMascota();
		id_especie_raza = m.getEspecieRaza().getIdEspecieRaza();
		return "editarMascota";
	}
	public void actionListenerReset(){
		 listaMascotas = managerRefugio.findAllMascota();
		}
	public String getCaracteristicas_mascota() {
		return caracteristicas_mascota;
	}
	public void setCaracteristicas_mascota(String caracteristicas_mascota) {
		this.caracteristicas_mascota = caracteristicas_mascota;
	}
	public int getEdad_mascota() {
		return edad_mascota;
	}
	public void setEdad_mascota(int edad_mascota) {
		this.edad_mascota = edad_mascota;
	}
	public Date getFecha_ingreso_mascota() {
		return fecha_ingreso_mascota;
	}
	public void setFecha_ingreso_mascota(Date fecha_ingreso_mascota) {
		this.fecha_ingreso_mascota = fecha_ingreso_mascota;
	}
	public String getObservaciones_mascota() {
		return observaciones_mascota;
	}
	public void setObservaciones_mascota(String observaciones_mascota) {
		this.observaciones_mascota = observaciones_mascota;
	}
	public String getSexo_mascota() {
		return sexo_mascota;
	}
	public void setSexo_mascota(String sexo_mascota) {
		this.sexo_mascota = sexo_mascota;
	}
	public int getId_condiciones_mascota() {
		return id_condiciones_mascota;
	}
	public void setId_condiciones_mascota(int id_condiciones_mascota) {
		this.id_condiciones_mascota = id_condiciones_mascota;
	}
	public int getId_especie_raza() {
		return id_especie_raza;
	}
	public void setId_especie_raza(int id_especie_raza) {
		this.id_especie_raza = id_especie_raza;
	}
	public List<Mascota> getListaMascotas() {
		return listaMascotas;
	}
	public void setListaMascotas(List<Mascota> listaMascotas) {
		this.listaMascotas = listaMascotas;
	}
	public List<CondicionesMascota> getListaCondicones() {
		return listaCondicones;
	}
	public void setListaCondicones(List<CondicionesMascota> listaCondicones) {
		this.listaCondicones = listaCondicones;
	}
	public List<EspecieRaza> getListaEspecieRaza() {
		return listaEspecieRaza;
	}
	public void setListaEspecieRaza(List<EspecieRaza> listaEspecieRaza) {
		this.listaEspecieRaza = listaEspecieRaza;
	}
	public ManagerRefugio getManagerRefugio() {
		return managerRefugio;
	}
	public void setManagerRefugio(ManagerRefugio managerRefugio) {
		this.managerRefugio = managerRefugio;
	}
	
	

		
}
