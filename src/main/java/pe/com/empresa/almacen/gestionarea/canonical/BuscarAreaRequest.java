package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import pe.com.empresa.almacen.gestionarea.canonical.type.BuscarAreaRequestType;

public class BuscarAreaRequest implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotNull
	private BuscarAreaRequestType buscarAreaRequest;

	public BuscarAreaRequestType getBuscarAreaRequest() {
		return buscarAreaRequest;
	}

	public void setBuscarAreaRequest(BuscarAreaRequestType buscarAreaRequest) {
		this.buscarAreaRequest = buscarAreaRequest;
	}

}
