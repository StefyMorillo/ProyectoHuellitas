package refugio.model.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the especie_raza database table.
 * 
 */
@Entity
@Table(name="especie_raza")
@NamedQuery(name="EspecieRaza.findAll", query="SELECT e FROM EspecieRaza e")
public class EspecieRaza implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ESPECIE_RAZA_IDESPECIERAZA_GENERATOR", sequenceName="SEQ_ESPECIE_RAZA")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ESPECIE_RAZA_IDESPECIERAZA_GENERATOR")
	@Column(name="id_especie_raza", unique=true, nullable=false)
	private Integer idEspecieRaza;

	@Column(name="especie_animal", length=2147483647)
	private String especieAnimal;

	@Column(name="raza_animal", length=2147483647)
	private String razaAnimal;

	//bi-directional many-to-one association to Mascota
	@OneToMany(mappedBy="especieRaza")
	private List<Mascota> mascotas;

	public EspecieRaza() {
	}

	public Integer getIdEspecieRaza() {
		return this.idEspecieRaza;
	}

	public void setIdEspecieRaza(Integer idEspecieRaza) {
		this.idEspecieRaza = idEspecieRaza;
	}

	public String getEspecieAnimal() {
		return this.especieAnimal;
	}

	public void setEspecieAnimal(String especieAnimal) {
		this.especieAnimal = especieAnimal;
	}

	public String getRazaAnimal() {
		return this.razaAnimal;
	}

	public void setRazaAnimal(String razaAnimal) {
		this.razaAnimal = razaAnimal;
	}

	public List<Mascota> getMascotas() {
		return this.mascotas;
	}

	public void setMascotas(List<Mascota> mascotas) {
		this.mascotas = mascotas;
	}

	public Mascota addMascota(Mascota mascota) {
		getMascotas().add(mascota);
		mascota.setEspecieRaza(this);

		return mascota;
	}

	public Mascota removeMascota(Mascota mascota) {
		getMascotas().remove(mascota);
		mascota.setEspecieRaza(null);

		return mascota;
	}

}