package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historial database table.
 * 
 */
@Entity
@Table(name="historial")
@NamedQuery(name="Historial.findAll", query="SELECT h FROM Historial h")
public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="HISTORIAL_IDHISTORIAL_GENERATOR", sequenceName="SEQ_HISTORIAL")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="HISTORIAL_IDHISTORIAL_GENERATOR")
	@Column(name="id_historial", unique=true, nullable=false)
	private Integer idHistorial;

	@Column(name="descripcion_historial", length=2147483647)
	private String descripcionHistorial;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha_atencion")
	private Date fechaAtencion;

	//bi-directional many-to-one association to Mascota
	@ManyToOne
	@JoinColumn(name="id_mascota", nullable=false)
	private Mascota mascota;

	public Historial() {
	}

	public Integer getIdHistorial() {
		return this.idHistorial;
	}

	public void setIdHistorial(Integer idHistorial) {
		this.idHistorial = idHistorial;
	}

	public String getDescripcionHistorial() {
		return this.descripcionHistorial;
	}

	public void setDescripcionHistorial(String descripcionHistorial) {
		this.descripcionHistorial = descripcionHistorial;
	}

	public Date getFechaAtencion() {
		return this.fechaAtencion;
	}

	public void setFechaAtencion(Date fechaAtencion) {
		this.fechaAtencion = fechaAtencion;
	}

	public Mascota getMascota() {
		return this.mascota;
	}

	public void setMascota(Mascota mascota) {
		this.mascota = mascota;
	}

}