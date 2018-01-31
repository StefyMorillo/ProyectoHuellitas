package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the condiciones_espacio database table.
 * 
 */
@Entity
@Table(name="condiciones_espacio")
@NamedQuery(name="CondicionesEspacio.findAll", query="SELECT c FROM CondicionesEspacio c")
public class CondicionesEspacio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONDICIONES_ESPACIO_IDCONDICIONESESPACIO_GENERATOR", sequenceName="SEQ_CONDICIONES_ESPACIO")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONDICIONES_ESPACIO_IDCONDICIONESESPACIO_GENERATOR")
	@Column(name="id_condiciones_espacio", unique=true, nullable=false)
	private Integer idCondicionesEspacio;

	@Column(name="descripcion_espacio", length=2147483647)
	private String descripcionEspacio;

	//bi-directional many-to-one association to Seguimiento
	@OneToMany(mappedBy="condicionesEspacio")
	private List<Seguimiento> seguimientos;

	public CondicionesEspacio() {
	}

	public Integer getIdCondicionesEspacio() {
		return this.idCondicionesEspacio;
	}

	public void setIdCondicionesEspacio(Integer idCondicionesEspacio) {
		this.idCondicionesEspacio = idCondicionesEspacio;
	}

	public String getDescripcionEspacio() {
		return this.descripcionEspacio;
	}

	public void setDescripcionEspacio(String descripcionEspacio) {
		this.descripcionEspacio = descripcionEspacio;
	}

	public List<Seguimiento> getSeguimientos() {
		return this.seguimientos;
	}

	public void setSeguimientos(List<Seguimiento> seguimientos) {
		this.seguimientos = seguimientos;
	}

	public Seguimiento addSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().add(seguimiento);
		seguimiento.setCondicionesEspacio(this);

		return seguimiento;
	}

	public Seguimiento removeSeguimiento(Seguimiento seguimiento) {
		getSeguimientos().remove(seguimiento);
		seguimiento.setCondicionesEspacio(null);

		return seguimiento;
	}

}