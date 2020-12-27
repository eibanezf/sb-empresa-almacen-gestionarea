package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionarea.canonical.type.EliminarAreaRequestType;

public class EliminarAreaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private EliminarAreaRequestType eliminarAreaRequest;

	public EliminarAreaRequestType getEliminarAreaRequest() {
		return eliminarAreaRequest;
	}

	public void setEliminarAreaRequest(EliminarAreaRequestType eliminarAreaRequest) {
		this.eliminarAreaRequest = eliminarAreaRequest;
	}

}
