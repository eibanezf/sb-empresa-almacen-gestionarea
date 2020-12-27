package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;

import pe.com.empresa.almacen.gestionarea.entity.Area;

public class CrearAreaResponseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private HeaderResponseType headerResponse;
	private Area area;

	public HeaderResponseType getHeaderResponse() {
		return headerResponse;
	}

	public void setHeaderResponse(HeaderResponseType headerResponse) {
		this.headerResponse = headerResponse;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

}
