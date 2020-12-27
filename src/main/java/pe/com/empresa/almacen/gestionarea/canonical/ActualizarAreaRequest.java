package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionarea.canonical.type.ActualizarAreaRequestType;

public class ActualizarAreaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	@NotNull
	private ActualizarAreaRequestType actualizarAreaRequest;

	public ActualizarAreaRequestType getActualizarAreaRequest() {
		return actualizarAreaRequest;
	}

	public void setActualizarAreaRequest(ActualizarAreaRequestType actualizarAreaRequest) {
		this.actualizarAreaRequest = actualizarAreaRequest;
	}

}
