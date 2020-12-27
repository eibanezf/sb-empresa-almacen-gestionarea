package pe.com.empresa.almacen.gestionarea.util;

public class ResponseValidarVacio {
	private String mensaje;
	private String atributo;
	private boolean valido;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public String getAtributo() {
		return atributo;
	}

	public void setAtributo(String atributo) {
		this.atributo = atributo;
	}

	public boolean esValido() {
		return valido;
	}

	public void setValido(boolean valido) {
		this.valido = valido;
	}

}
