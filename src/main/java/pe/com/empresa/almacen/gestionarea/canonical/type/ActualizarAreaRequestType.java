package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pe.com.empresa.almacen.gestionarea.util.Constantes;

public class ActualizarAreaRequestType implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = Constantes.NOT_NULL)
	private Long idArea;

	@NotNull(message = Constantes.NOT_NULL)
	@Size(message = Constantes.SIZE, max = 45)
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
