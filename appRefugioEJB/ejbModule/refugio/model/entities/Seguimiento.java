package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the seguimiento database table.
 * 
 */
@Entity
@Table(name="seguimiento")
@NamedQuery(name="Seguimiento.findAll", query="SELECT s FROM Seguimiento s")
public class Seguimiento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEGUIMIENTO_IDSEGUIMIENTO_GENERATOR", sequenceName="SEQ_SEGUIMIENTO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEGUIMIENTO_IDSEGUIMIENTO_GENERATOR")
	@Column(name="id_seguimiento", unique=true, nullable=false)
	private Integer idSeguimiento;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_seguimiento")
	private Date fechaSeguimiento;

	@Column(length=2147483647)
	private String observaciones;

	//bi-directional many-to-one association to Adoptante
	@ManyToOne
	@JoinColumn(name="cedula_adoptante")
	private Adoptante adoptante;

	//bi-directional many-to-one association to CondicionesEspacio
	@ManyToOne
	@JoinColumn(name="id_condiciones_espacio", nullable=false)
	private CondicionesEspacio condicionesEspacio;

	//bi-directional many-to-one association to Mascota
	@ManyToOne
	@JoinColumn(name="id_mascota", nullable=false)
	private Mascota mascota;

	public Seguimiento() {
	}

	public Integer getIdSeguimiento() {
		return this.idSeguimiento;
	}

	public void setIdSeguimiento(Integer idSeguimiento) {
		this.idSeguimiento = idSeguimiento;
	}

	public Date getFechaSeguimiento() {
		return this.fechaSeguimiento;
	}

	public void setFechaSeguimiento(Date fechaSeguimiento) {
		this.fechaSeguimiento = fechaSeguimiento;
	}

	public String getObservaciones() {
		return this.observaciones;
	}

	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}

	public Adoptante getAdoptante() {
		return this.adoptante;
	}

	public void setAdoptante(Adoptante adoptante) {
		this.adoptante = adoptante;
	}

	public CondicionesEspacio getCondicionesEspacio() {
		return this.condicionesEspacio;
	}

	public void setCondicionesEspacio(CondicionesEspacio condicionesEspacio) {
		this.condicionesEspacio = condicionesEspacio;
	}

	public Mascota getMascota() {
		return this.mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

}