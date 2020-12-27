package pe.com.empresa.almacen.gestionarea.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PropiedadesExternas {
	// IDT
	@Value("${idt1.cod}")
	public String idt1Cod;

	@Value("${idt1.msj}")
	public String idt1Msj;

	@Value("${idt2.cod}")
	public String idt2Cod;

	@Value("${idt2.msj}")
	public String idt2Msj;

	@Value("${idt3.cod}")
	public String idt3Cod;

	@Value("${idt3.msj}")
	public String idt3Msj;

	// IDF
	@Value("${idf0.cod}")
	public String idf0Cod;

	@Value("${idf0.msj}")
	public String idf0Msj;
	
	@Value("${idf1.cod}")
	public String idf1Cod;

	@Value("${idf1.msj}")
	public String idf1Msj;
}
