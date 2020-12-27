package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionarea.util.Constantes;

public class BuscarAreaRequestType implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull(message = Constantes.NOT_NULL)
	private String idArea;

	public String getIdArea() {
		return idArea;
	}

	public void setIdArea(String idArea) {
		this.idArea = idArea;
	}

}
