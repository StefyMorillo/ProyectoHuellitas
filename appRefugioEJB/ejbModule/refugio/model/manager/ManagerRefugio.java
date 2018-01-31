package refugio.model.manager;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import refugio.model.entities.Usuario;
import refugio.model.entities.Adoptante;

@Stateless
@LocalBean
public class ManagerRefugio {
	@PersistenceContext(unitName = "RefugioDS")
	private EntityManager em;
	@EJB
	private ManagerDAO managerdao;

	// REGISTRAR-USUARIOS
	public void registrarUsuario(String cedula_usuario, String nombre_usuario, String apellido_usuario,
			String telefono_usuario, String email_usuario, String clave_usuario, String tipo_usuario) throws Exception {
		Usuario usua = findUsuario(cedula_usuario);
		if (usua != null) {
			throw new Exception("Ya está registrado");
		}
		if (validadorDeCedula(cedula_usuario)) {
			if (validarCorreo(email_usuario)) {
				Usuario u = new Usuario();
				u.setCedulaUsuario(cedula_usuario);
				u.setNombreUsuario(nombre_usuario);
				u.setApellidoUsuario(apellido_usuario);
				u.setTelefonoUsuario(telefono_usuario);
				u.setEmailUsuario(email_usuario);
				u.setClaveUsuario(clave_usuario);
				u.setTipoUsuario(tipo_usuario);
				em.persist(u);
			} else {
				throw new Exception("Correo incorrecto");
			}
		} else {
			throw new Exception("Cédula incorrecta");
		}

	}

	public Usuario findUsuario(String cedula_usuario) throws Exception {
		Usuario u = em.find(Usuario.class, cedula_usuario);
		return u;
	}

	// ACTUALIZAR-USUARIOS
	public void actualizarUsuario(String cedula_usuario, String nombre_usuario, String apellido_usuario,
			String telefono_usuario, String email_usuario, String clave_usuario, String tipo_usuario) throws Exception {
		Usuario u = findUsuario(cedula_usuario);

		u.setCedulaUsuario(cedula_usuario);
		u.setNombreUsuario(nombre_usuario);
		u.setApellidoUsuario(apellido_usuario);
		u.setTelefonoUsuario(telefono_usuario);
		u.setEmailUsuario(email_usuario);
		u.setClaveUsuario(clave_usuario);
		u.setTipoUsuario(tipo_usuario);
		em.merge(u);
	}

	@SuppressWarnings("unchecked")
	public List<Usuario> findAllUsuarios() {
		Query q = em.createQuery("select u from Usuario u order by u.apellidoUsuario");
		List<Usuario> lista = q.getResultList();
		return lista;
	}

	public void eliminarUsuario(String cedula_usuario) throws Exception {

		Usuario u = findUsuario(cedula_usuario);
		if (u == null)
			throw new Exception("No existe el usuario especificado");
		managerdao.eliminar(Usuario.class, cedula_usuario);
	}

	// Login
	public boolean comprobarUsuario(String cedula, String clave) throws Exception {
		Usuario u = findUsuario(cedula);
		if (u == null)
			throw new Exception("No existe el usuario " + cedula);
		if (u.getClaveUsuario().equals(clave))
			return true;
		throw new Exception("Contraseña no válida.");
	}

	//// REGISTRAR- ADOPTANTE
	public void registrarAdoptante(String cedula_adoptante, String nombre_adoptante, String apellido_adoptante,
			String telefono_adoptante, String celular_adoptante, String email_adoptante, int edad_adoptante,
			String ocupacion_adoptante, String direccion_adoptante) throws Exception {
		Adoptante adop = findAdoptante(cedula_adoptante);
		if (adop != null) {
			throw new Exception("Ya está registrado");
		}
		if (validadorDeCedula(cedula_adoptante)) {
			if (validarCorreo(email_adoptante)) {
		Adoptante a = new Adoptante();
		a.setCedulaAdoptante(cedula_adoptante);
		a.setNombreAdoptante(nombre_adoptante);
		a.setApellidoAdoptante(apellido_adoptante);
		a.setTelefonoAdoptante(telefono_adoptante);
		a.setCelularAdoptante(celular_adoptante);
		a.setEmailAdoptante(email_adoptante);
		a.setEdadAdoptante(edad_adoptante);
		a.setOcupacionAdoptante(ocupacion_adoptante);
		a.setDireccionAdoptante(direccion_adoptante);
		em.persist(a);
			} else {
				throw new Exception("Correo incorrecto");
			}
		} else {
			throw new Exception("Cédula incorrecta");
		}
		}

	public Adoptante findAdoptante(String cedula_adoptante) throws Exception {
		Adoptante a = em.find(Adoptante.class, cedula_adoptante);
		return a;
	}

	// ACTUALIZAR-ADOPTANTE
	public void actualizarAdoptante(String cedula_adoptante, String nombre_adoptante, String apellido_adoptante,
			String telefono_adoptante, String celular_adoptante, String email_adoptante, int edad_adoptante,
			String ocupacion_adoptante, String direccion_adoptante) throws Exception {
		Adoptante a = findAdoptante(cedula_adoptante);
		a.setCedulaAdoptante(cedula_adoptante);
		a.setNombreAdoptante(nombre_adoptante);
		a.setApellidoAdoptante(apellido_adoptante);
		a.setTelefonoAdoptante(telefono_adoptante);
		a.setCelularAdoptante(celular_adoptante);
		a.setEmailAdoptante(email_adoptante);
		a.setEdadAdoptante(edad_adoptante);
		a.setOcupacionAdoptante(ocupacion_adoptante);
		a.setDireccionAdoptante(direccion_adoptante);
		em.merge(a);
	}

	@SuppressWarnings("unchecked")
	public List<Adoptante> findAllAdoptantes() {
		Query q = em.createQuery("select a from Adoptante a order by a.apellidoAdoptante");
		List<Adoptante> lista = q.getResultList();
		return lista;
	}

	public void eliminarAdoptante(String cedula_adoptante) throws Exception {

		Adoptante a = findAdoptante(cedula_adoptante);
		if (a == null)
			throw new Exception("No existe la persona especificada");
		managerdao.eliminar(Adoptante.class, cedula_adoptante);
	}

	// Validaciones
	// VALIDAR CEDULA
	public boolean validadorDeCedula(String cedula_usuario) {
		boolean cedulaCorrecta = false;

		try {

			if (cedula_usuario.length() == 10) // ConstantesApp.LongitudCedula
			{
				int tercerDigito = Integer.parseInt(cedula_usuario.substring(2, 3));
				if (tercerDigito < 6) {
					// Coeficientes de validación cédula
					// El decimo digito se lo considera dígito verificador
					int[] coefValCedula = { 2, 1, 2, 1, 2, 1, 2, 1, 2 };
					int verificador = Integer.parseInt(cedula_usuario.substring(9, 10));
					int suma = 0;
					int digito = 0;
					for (int i = 0; i < (cedula_usuario.length() - 1); i++) {
						digito = Integer.parseInt(cedula_usuario.substring(i, i + 1)) * coefValCedula[i];
						suma += ((digito % 10) + (digito / 10));
					}

					if ((suma % 10 == 0) && (suma % 10 == verificador)) {
						cedulaCorrecta = true;
					} else if ((10 - (suma % 10)) == verificador) {
						cedulaCorrecta = true;
					} else {
						cedulaCorrecta = false;
					}
				} else {
					cedulaCorrecta = false;
				}
			} else {
				cedulaCorrecta = false;
			}
		} catch (NumberFormatException nfe) {
			cedulaCorrecta = false;
		} catch (Exception err) {
			System.out.println("Una excepción ocurrió en el proceso de validación");
			cedulaCorrecta = false;
		}
		if (!cedulaCorrecta) {
			System.out.println("La cédula ingresada es incorrecta");
		}
		return cedulaCorrecta;
	}

	// VALIDAR CORREO
	public boolean validarCorreo(String email_usuario) {
		Pattern pattern = Pattern.compile(
				"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
		Matcher mather = pattern.matcher(email_usuario);
		boolean correcto = mather.find();
		return correcto;
	}

}
