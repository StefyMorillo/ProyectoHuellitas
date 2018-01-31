package refugio.controller;

import java.io.IOException;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import refugio.model.entities.Usuario;
import refugio.model.manager.ManagerRefugio;
import refugio.model.util.ModelUtil;
import refugio.view.util.JSFUtil;

@ManagedBean
@SessionScoped
public class ControllerLogin {
	private String cedulaUsuario;
	private String claveUsuario;
	private boolean comprobado;
	private Usuario u;

	@EJB
	private ManagerRefugio managerRefugio;

	public void actionLogin() {

		try {

			FacesContext contex = FacesContext.getCurrentInstance();
			u = managerRefugio.findUsuario(cedulaUsuario);

			comprobado = managerRefugio.comprobarUsuario(cedulaUsuario, claveUsuario);

			// verificamos si el acceso es con admin:
			if (u.getTipoUsuario().equals("A")) {
				JSFUtil.crearMensajeInfo("Login correcto");
				contex.getExternalContext().redirect("Administrador/ListaUsuario.xhtml");
			} else if (u.getTipoUsuario().equals("O")) {
				contex.getExternalContext().redirect("Operador/ListaOperador.xhtml");
			} 			

		} catch (Exception e) {
			JSFUtil.crearMensajeError(e.getMessage());
			e.printStackTrace();
		}

	}

	public String actionSalir() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		JSFUtil.crearMensajeError("Cédula Incorrecta");
		return "/login?faces-redirect=true";
	}

	public void actionComprobarLogin() {
		ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
		try {
			String path = ec.getRequestPathInfo();
			System.out.println("getRequestContextPath(): " + ec.getRequestContextPath());
			System.out.println("getRequestPathInfo(): " + path);
			System.out.println("Id usuario: " + cedulaUsuario);
			if (path.equals("/login.xhtml"))
				return;
			if (ModelUtil.isEmpty(cedulaUsuario))
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			if (!comprobado) {
				ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
			} else {
				// si hizo login, verificamos que acceda a paginas permitidas:
				if (u.getTipoUsuario().equals("A")) {
					if (!path.contains("/Administrador/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
				if (u.getTipoUsuario().equals("O")) {
					if (!path.contains("/Operador/"))
						ec.redirect(ec.getRequestContextPath() + "/faces/login.xhtml");
					else
						return;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String getCedulaUsuario() {
		return cedulaUsuario;
	}

	public void setCedulaUsuario(String cedulaUsuario) {
		this.cedulaUsuario = cedulaUsuario;
	}

	public String getClaveUsuario() {
		return claveUsuario;
	}

	public void setClaveUsuario(String claveUsuario) {
		this.claveUsuario = claveUsuario;
	}

	public boolean isComprobado() {
		return comprobado;
	}

	public void setComprobado(boolean comprobado) {
		this.comprobado = comprobado;
	}

	public Usuario getU() {
		return u;
	}

	public void setU(Usuario u) {
		this.u = u;
	}
	
	
	

}
