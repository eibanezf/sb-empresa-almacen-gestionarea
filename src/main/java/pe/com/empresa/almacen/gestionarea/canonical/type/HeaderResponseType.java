package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;

public class HeaderResponseType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String idTransaccion;
	private String codigoRespuesta;
	private String mensajeRespuesta;

	public String getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(String idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public String getCodigoRespuesta() {
		return codigoRespuesta;
	}

	public void setCodigoRespuesta(String codigoRespuesta) {
		this.codigoRespuesta = codigoRespuesta;
	}

	public String getMensajeRespuesta() {
		return mensajeRespuesta;
	}

	public void setMensajeRespuesta(String mensajeRespuesta) {
		this.mensajeRespuesta = mensajeRespuesta;
	}
}
