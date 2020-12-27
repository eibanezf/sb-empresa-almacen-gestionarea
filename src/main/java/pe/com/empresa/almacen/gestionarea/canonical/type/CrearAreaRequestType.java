package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import pe.com.empresa.almacen.gestionarea.util.Constantes;

public class CrearAreaRequestType implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = Constantes.NOT_NULL)
	@Size(message = Constantes.SIZE, max = 45)
	private String nombre;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
