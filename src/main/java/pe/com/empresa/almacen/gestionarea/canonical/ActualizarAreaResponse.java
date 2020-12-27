package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionarea.canonical.type.ActualizarAreaResponseType;

public class ActualizarAreaResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private ActualizarAreaResponseType actualizarAreaResponse;

	public ActualizarAreaResponseType getActualizarAreaResponse() {
		return actualizarAreaResponse;
	}

	public void setActualizarAreaResponse(ActualizarAreaResponseType actualizarAreaResponse) {
		this.actualizarAreaResponse = actualizarAreaResponse;
	}

}
