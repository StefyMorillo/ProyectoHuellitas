package refugio.model.manager;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.Column;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import refugio.model.entities.Usuario;
import refugio.model.entities.Adoptante;
import refugio.model.entities.CondicionesMascota;
import refugio.model.entities.EspecieRaza;
import refugio.model.entities.Mascota;

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
			throw new Exception("Ya est� registrado");
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
			throw new Exception("Cedula incorrecta");
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
		throw new Exception("Contrase�a no v�lida.");
	}

	//// REGISTRAR- ADOPTANTE
	public void registrarAdoptante(String cedula_adoptante, String nombre_adoptante, String apellido_adoptante,
			String telefono_adoptante, String celular_adoptante, String email_adoptante, int edad_adoptante,
			String ocupacion_adoptante, String direccion_adoptante) throws Exception {
		Adoptante adop = findAdoptante(cedula_adoptante);
		if (adop != null) {
			throw new Exception("Ya est� registrado");
		}
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
	
	//// REGISTRAR- MASCOTA
	public void registrarMascota(String caracteristicas_mascota, int edad_mascota,
			String observaciones_mascota, String sexo_mascota, int id_condiciones_mascota,
			int id_especie_raza) throws Exception {
		
		Mascota m = new Mascota();
		CondicionesMascota c = findCondicionesMascota(id_condiciones_mascota);
		EspecieRaza e = findEspecieRaza(id_especie_raza);
		
		m.setCaracteristicasMascota(caracteristicas_mascota);
		m.setEdadMascota(edad_mascota);
		m.setFechaIngresoMascota(new Date());
		m.setObservacionesMascota(observaciones_mascota);
		m.setSexoMascota(sexo_mascota);
		m.setCondicionesMascota(c);
		m.setEspecieRaza(e);
		em.persist(m);
	}
	public Mascota findMascota(int id_mascota) throws Exception {
		Mascota m = em.find(Mascota.class, id_mascota);
		return m;
	}
	public CondicionesMascota findCondicionesMascota(int id_condiciones_mascota) throws Exception {
		CondicionesMascota c = em.find(CondicionesMascota.class, id_condiciones_mascota);
		return c;
	}
	
	public EspecieRaza findEspecieRaza(int id_especie_raza) throws Exception {
		EspecieRaza e = em.find(EspecieRaza.class, id_especie_raza);
		return e;
	}
	
	//ACTUALIZAR- MASCOTA
	public void ActualizarMascota(int id_mascota, String caracteristicas_mascota, int edad_mascota, 
			String observaciones_mascota, String sexo_mascota, int id_condiciones_mascota,
			int id_especie_raza) throws Exception {		
		Mascota m = findMascota(id_mascota);
		CondicionesMascota c = findCondicionesMascota(id_condiciones_mascota);
		EspecieRaza e = findEspecieRaza(id_especie_raza);		
		m.setCaracteristicasMascota(caracteristicas_mascota);
		m.setEdadMascota(edad_mascota);
		m.setFechaIngresoMascota(new Date());
		m.setObservacionesMascota(observaciones_mascota);
		m.setSexoMascota(sexo_mascota);
		m.setCondicionesMascota(c);
		m.setEspecieRaza(e);
		em.merge(m);
	}
	@SuppressWarnings("unchecked")
	public List<Mascota> findAllMascota() {
		Query q = em.createQuery("select a from Mascota a order by a.idMascota");
		List<Mascota> lista = q.getResultList();
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<CondicionesMascota> findAllCondicionesMascota() {
		Query q = em.createQuery("select a from CondicionesMascota a order by a.idCondicionesMascota");
		List<CondicionesMascota> lista = q.getResultList();
		return lista;
	}
	@SuppressWarnings("unchecked")
	public List<EspecieRaza> findAllEspecieRaza() {
		Query q = em.createQuery("select a from EspecieRaza a order by a.idEspecieRaza");
		List<EspecieRaza> lista = q.getResultList();
		return lista;
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
					// Coeficientes de validaci�n c�dula
					// El decimo digito se lo considera d�gito verificador
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
			System.out.println("Una excepci�n ocurri� en el proceso de validaci�n");
			cedulaCorrecta = false;
		}
		if (!cedulaCorrecta) {
			System.out.println("La c�dula ingresada es incorrecta");
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
