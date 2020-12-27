package pe.com.empresa.almacen.gestionarea.canonical;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionarea.canonical.type.ListarAreasResponseType;

public class ListarAreasResponse implements Serializable {
	private static final long serialVersionUID = 1L;

	private ListarAreasResponseType listarAreasResponse;

	public ListarAreasResponseType getListarAreasResponse() {
		return listarAreasResponse;
	}

	public void setListarAreasResponse(ListarAreasResponseType listarAreasResponse) {
		this.listarAreasResponse = listarAreasResponse;
	}

}
