package refugio.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import refugio.model.entities.Usuario;
import refugio.model.manager.ManagerRefugio;
import refugio.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerUsuario {
	private String cedula_usuario;
	private String apellido_usuario;
	private String clave_usuario;
	private String email_usuario;
	private String nombre_usuario;
	private String telefono_usuario;
	private String tipo_usuario;
	private List<Usuario> listaUsuarios;
	@EJB
	private ManagerRefugio managerRefugio;

	@PostConstruct
	public void iniciar() {
		listaUsuarios = managerRefugio.findAllUsuarios();
	}

	public void actionRegistrar() {
		try {
			managerRefugio.registrarUsuario(cedula_usuario, nombre_usuario, apellido_usuario, telefono_usuario,
					email_usuario, clave_usuario, tipo_usuario);
			JSFUtil.crearMensajeInfo("Registrado Exitosamente");
			listaUsuarios = managerRefugio.findAllUsuarios();
			cedula_usuario = "";
			nombre_usuario = "";
			apellido_usuario = "";
			telefono_usuario = "";
			email_usuario = "";
			clave_usuario = "";
			tipo_usuario = "";
		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}

	}

	public String ActualizarUsuario() {
		String direccion = null;
		try {

			managerRefugio.actualizarUsuario(cedula_usuario, nombre_usuario, apellido_usuario, telefono_usuario,
					email_usuario, clave_usuario, tipo_usuario);
			JSFUtil.crearMensajeInfo("Sus datos han sido actualizados");
			listaUsuarios = managerRefugio.findAllUsuarios();
			direccion= "ListaUsuario";

		} catch (Exception e) {
			e.printStackTrace();
			JSFUtil.crearMensajeError(e.getMessage());
		}
		return direccion;

	}

	public String cargarUsuario(Usuario u) {
		cedula_usuario = u.getCedulaUsuario();
		nombre_usuario = u.getNombreUsuario();
		apellido_usuario = u.getApellidoUsuario();
		telefono_usuario = u.getTelefonoUsuario();
		email_usuario = u.getEmailUsuario();
		clave_usuario = u.getClaveUsuario();
		tipo_usuario = u.getTipoUsuario();
		return "editarUsuario";
	}

	public String getCedula_usuario() {
		return cedula_usuario;
	}

	public void setCedula_usuario(String cedula_usuario) {
		this.cedula_usuario = cedula_usuario;
	}

	public String getApellido_usuario() {
		return apellido_usuario;
	}

	public void setApellido_usuario(String apellido_usuario) {
		this.apellido_usuario = apellido_usuario;
	}

	public String getClave_usuario() {
		return clave_usuario;
	}

	public void setClave_usuario(String clave_usuario) {
		this.clave_usuario = clave_usuario;
	}

	public String getEmail_usuario() {
		return email_usuario;
	}

	public void setEmail_usuario(String email_usuario) {
		this.email_usuario = email_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getTelefono_usuario() {
		return telefono_usuario;
	}

	public void setTelefono_usuario(String telefono_usuario) {
		this.telefono_usuario = telefono_usuario;
	}

	public String getTipo_usuario() {
		return tipo_usuario;
	}

	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}

	public List<Usuario> getListaUsuarios() {
		return listaUsuarios;
	}

	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}

	public ManagerRefugio getManagerRefugio() {
		return managerRefugio;
	}

	public void setManagerRefugio(ManagerRefugio managerRefugio) {
		this.managerRefugio = managerRefugio;
	}

}
