package pe.com.empresa.almacen.gestionarea.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "AREA", schema = "EIBANEZF")
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_AREA_GENERATOR")
	@SequenceGenerator(name = "SEQ_AREA_GENERATOR", schema = "EIBANEZF", sequenceName = "SEQ_AREA", allocationSize = 0)
	@Column(name = "ID_AREA", updatable = false, nullable = false)
	private Long idArea;

	@Column(name = "NOMBRE")
	private String nombre;

	public Long getIdArea() {
		return idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
