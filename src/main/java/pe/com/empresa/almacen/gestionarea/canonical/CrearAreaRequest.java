package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionarea.canonical.type.CrearAreaRequestType;

public class CrearAreaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private CrearAreaRequestType crearAreaRequest;

	public CrearAreaRequestType getCrearAreaRequest() {
		return crearAreaRequest;
	}

	public void setCrearAreaRequest(CrearAreaRequestType crearAreaRequest) {
		this.crearAreaRequest = crearAreaRequest;
	}

}
