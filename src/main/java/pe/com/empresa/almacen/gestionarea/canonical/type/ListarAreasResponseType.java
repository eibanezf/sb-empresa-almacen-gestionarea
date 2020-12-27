package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;
import java.util.List;

import pe.com.empresa.almacen.gestionarea.entity.Area;

public class ListarAreasResponseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private HeaderResponseType headerResponse;
	private List<Area> areas;

	public HeaderResponseType getHeaderResponse() {
		return headerResponse;
	}

	public void setHeaderResponse(HeaderResponseType headerResponse) {
		this.headerResponse = headerResponse;
	}

	public List<Area> getAreas() {
		return areas;
	}

	public void setAreas(List<Area> areas) {
		this.areas = areas;
	}

}
