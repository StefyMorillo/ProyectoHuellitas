package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the adoptante database table.
 * 
 */
@Entity
@Table(name="adoptante")
@NamedQuery(name="Adoptante.findAll", query="SELECT a FROM Adoptante a")
public class Adoptante implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="cedula_adoptante", unique=true, nullable=false, length=2147483647)
	private String cedulaAdoptante;

	@Column(name="apellido_adoptante", length=2147483647)
	private String apellidoAdoptante;

	@Column(name="celular_adoptante", length=2147483647)
	private String celularAdoptante;

	@Column(name="direccion_adoptante", length=2147483647)
	private String direccionAdoptante;

	@Column(name="edad_adoptante")
	private Integer edadAdoptante;

	@Column(name="email_adoptante", length=2147483647)
	private String emailAdoptante;

	@Column(name="nombre_adoptante", length=2147483647)
	private String nombreAdoptante;

	@Column(name="ocupacion_adoptante", length=2147483647)
	private String ocupacionAdoptante;

	@Column(name="telefono_adoptante", length=2147483647)
	private String telefonoAdoptante;

	//bi-directional many-to-one association to Reserva
	@OneToMany(mappedBy="adoptante")
	private List<Reserva> reservas;

	//bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy="adoptante")
	private List<Seguimiento> seguimientos;

	public Adoptante() {
	}

	public String getCedulaAdoptante() {
		return this.cedulaAdoptante;
	}

	public void setCedulaAdoptante(String cedulaAdoptante) {
		this.cedulaAdoptante = cedulaAdoptante;
	}

	public String getApellidoAdoptante() {
		return this.apellidoAdoptante;
	}

	public void setApellidoAdoptante(String apellidoAdoptante) {
		this.apellidoAdoptante = apellidoAdoptante;
	}

	public String getCelularAdoptante() {
		return this.celularAdoptante;
	}

	public void setCelularAdoptante(String celularAdoptante) {
		this.celularAdoptante = celularAdoptante;
	}

	public String getDireccionAdoptante() {
		return this.direccionAdoptante;
	}

	public void setDireccionAdoptante(String direccionAdoptante) {
		this.direccionAdoptante = direccionAdoptante;
	}

	public Integer getEdadAdoptante() {
		return this.edadAdoptante;
	}

	public void setEdadAdoptante(Integer edadAdoptante) {
		this.edadAdoptante = edadAdoptante;
	}

	public String getEmailAdoptante() {
		return this.emailAdoptante;
	}

	public void setEmailAdoptante(String emailAdoptante) {
		this.emailAdoptante = emailAdoptante;
	}

	public String getNombreAdoptante() {
		return this.nombreAdoptante;
	}

	public void setNombreAdoptante(String nombreAdoptante) {
		this.nombreAdoptante = nombreAdoptante;
	}

	public String getOcupacionAdoptante() {
		return this.ocupacionAdoptante;
	}

	public void setOcupacionAdoptante(String ocupacionAdoptante) {
		this.ocupacionAdoptante = ocupacionAdoptante;
	}

	public String getTelefonoAdoptante() {
		return this.telefonoAdoptante;
	}

	public void setTelefonoAdoptante(String telefonoAdoptante) {
		this.telefonoAdoptante = telefonoAdoptante;
	}

	public List<Reserva> getReservas() {
		return this.reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Reserva addReserva(Reserva reserva) {
		getReservas().add(reserva);
		reserva.setAdoptante(this);

		return reserva;
	}

	public Reserva removeReserva(Reserva reserva) {
		getReservas().remove(reserva);
		reserva.setAdoptante(null);

		return reserva;
	}

	public List<Seguimiento> getSeguimientos() {
		return this.seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Seguimiento addSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().add(seguimiento);
		seguimiento.setAdoptante(this);

		return seguimiento;
	}

	public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().remove(seguimiento);
		seguimiento.setAdoptante(null);

		return seguimiento;
	}

}