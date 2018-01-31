package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the reserva database table.
 * 
 */
@Entity
@Table(name="reserva")
@NamedQuery(name="Reserva.findAll", query="SELECT r FROM Reserva r")
public class Reserva implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="RESERVA_IDRESERVA_GENERATOR", sequenceName="SEQ_RESERVA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="RESERVA_IDRESERVA_GENERATOR")
	@Column(name="id_reserva", unique=true, nullable=false)
	private Integer idReserva;

	@Column(name="estado_reserva")
	private Boolean estadoReserva;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_reserva")
	private Date fechaReserva;

	//bi-directional many-to-one association to Adoptante
	@ManyToOne
	@JoinColumn(name="cedula_adoptante")
	private Adoptante adoptante;

	//bi-directional many-to-one association to Mascota
	@ManyToOne
	@JoinColumn(name="id_mascota", nullable=false)
	private Mascota mascota;

	public Reserva() {
	}

	public Integer getIdReserva() {
		return this.idReserva;
	}

	public void setIdReserva(Integer idReserva) {
		this.idReserva = idReserva;
	}

	public Boolean getEstadoReserva() {
		return this.estadoReserva;
	}

	public void setEstadoReserva(Boolean estadoReserva) {
		this.estadoReserva = estadoReserva;
	}

	public Date getFechaReserva() {
		return this.fechaReserva;
	}

	public void setFechaReserva(Date fechaReserva) {
		this.fechaReserva = fechaReserva;
	}

	public Adoptante getAdoptante() {
		return this.adoptante;
	}

	public void setAdoptante(Adoptante adoptante) {
		this.adoptante = adoptante;
	}

	public Mascota getMascota() {
		return this.mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

}