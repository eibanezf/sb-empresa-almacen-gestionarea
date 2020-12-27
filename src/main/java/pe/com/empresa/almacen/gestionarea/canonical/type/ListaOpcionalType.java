package pe.com.empresa.almacen.gestionarea.canonical.type;

import java.io.Serializable;

public class ListaOpcionalType implements Serializable {
	private static final long serialVersionUID = 1L;

	private String clave;
	private String valor;

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
}