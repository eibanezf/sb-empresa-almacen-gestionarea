package pe.com.empresa.almacen.gestionarea.canonical.type;

import javax.validation.constraints.NotNull;

import org.apache.commons.lang3.StringUtils;

import pe.com.empresa.almacen.gestionarea.util.Constantes;

public class HeaderRequestType {
	@NotNull
	private String idTransaccion;
	
	@NotNull
	private String usuario;
	
	@NotNull
	private String aplicacion;

	public HeaderRequestType() {
		super();
	}
	
	public HeaderRequestType(String idTransaccion, String usuario, String aplicacion) {
		super();
		this.idTransaccion = StringUtils.isNotBlank(idTransaccion) ? idTransaccion : Constantes.VACIO;
		this.usuario = StringUtils.isNotBlank(usuario) ? usuario : Constantes.VACIO;
		this.aplicacion = StringUtils.isNotBlank(aplicacion) ? aplicacion : Constantes.APLICACION;
	}

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getAplicacion() {
		return aplicacion;
	}

	public void setAplicacion(String aplicacion) {
		this.aplicacion = aplicacion;
	}

}