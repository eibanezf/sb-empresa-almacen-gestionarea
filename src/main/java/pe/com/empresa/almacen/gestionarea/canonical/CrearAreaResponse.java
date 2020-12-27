package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionarea.canonical.type.CrearAreaResponseType;

public class CrearAreaResponse implements Serializable {
	private static final long serialVersionUID = 1L;
	private CrearAreaResponseType crearAreaResponse;

	public CrearAreaResponseType getCrearAreaResponse() {
		return crearAreaResponse;
	}

	public void setCrearAreaResponse(CrearAreaResponseType crearAreaResponse) {
		this.crearAreaResponse = crearAreaResponse;
	}

}
