package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionarea.canonical.type.EliminarAreaResponseType;

public class EliminarAreaResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private EliminarAreaResponseType eliminarAreaResponse;

	public EliminarAreaResponseType getEliminarAreaResponse() {
		return eliminarAreaResponse;
	}

	public void setEliminarAreaResponse(EliminarAreaResponseType eliminarAreaResponse) {
		this.eliminarAreaResponse = eliminarAreaResponse;
	}

}
