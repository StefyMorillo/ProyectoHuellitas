package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the condiciones_mascota database table.
 * 
 */
@Entity
@Table(name="condiciones_mascota")
@NamedQuery(name="CondicionesMascota.findAll", query="SELECT c FROM CondicionesMascota c")
public class CondicionesMascota implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="CONDICIONES_MASCOTA_IDCONDICIONESMASCOTA_GENERATOR", sequenceName="SEQ_CONDICIONES_MASCOTA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="CONDICIONES_MASCOTA_IDCONDICIONESMASCOTA_GENERATOR")
	@Column(name="id_condiciones_mascota", unique=true, nullable=false)
	private Integer idCondicionesMascota;

	@Column(name="descripcion_condiciones", length=2147483647)
	private String descripcionCondiciones;

	//bi-directional many-to-one association to Mascota
	@OneToMany(mappedBy="condicionesMascota")
	private List<Mascota> mascotas;

	public CondicionesMascota() {
	}

	public Integer getIdCondicionesMascota() {
		return this.idCondicionesMascota;
	}

	public void setIdCondicionesMascota(Integer idCondicionesMascota) {
		this.idCondicionesMascota = idCondicionesMascota;
	}

	public String getDescripcionCondiciones() {
		return this.descripcionCondiciones;
	}

	public void setDescripcionCondiciones(String descripcionCondiciones) {
		this.descripcionCondiciones = descripcionCondiciones;
	}

	public List<Mascota> getMascotas() {
		return this.mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public Mascota addMascota(Mascota mascota) {
		getMascotas().add(mascota);
		mascota.setCondicionesMascota(this);

		return mascota;
	}

	public Mascota removeMascota(Mascota mascota) {
		getMascotas().remove(mascota);
		mascota.setCondicionesMascota(null);

		return mascota;
	}

}