package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionarea.canonical.type.BuscarAreaResponseType;

public class BuscarAreaResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private BuscarAreaResponseType buscarAreaResponse;

	public BuscarAreaResponseType getBuscarAreaResponse() {
		return buscarAreaResponse;
	}

	public void setBuscarAreaResponse(BuscarAreaResponseType buscarAreaResponse) {
		this.buscarAreaResponse = buscarAreaResponse;
	}

}
