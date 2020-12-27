package pe.com.empresa.almacen.gestionarea.util;

import java.math.BigDecimal;
import java.net.InetAddress;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeConstants;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

public class Utilitarios {
	private static final Logger log = LoggerFactory.getLogger(Utilitarios.class);

	public static String getMsjTx(String metodo, String idTx) {
		return Constantes.CORCHETE_IZQ + metodo + Constantes.ID_TXT + idTx + Constantes.CORCHETE_DER;
	}
	
	public static void imprimirLogInicioMetodo(String msjTx, String metodo) {
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		log.info(Constantes.LOG_4P, msjTx, Constantes.INICIO_METODO, metodo, Constantes.CORCHETE_DER);
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
	}
	
	public static void imprimirLogFinMetodo(String msjTx, String metodo, String printResponse, long tiempo) {
		log.info(Constantes.LOG_4P, msjTx, Constantes.DEVOLVERRESPONSE, Constantes.SALTO_LINEA, printResponse);
		log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
		log.info(Constantes.LOG_6P, msjTx, Constantes.FIN_METODO, metodo, Constantes.CORCHETE_DER, Constantes.TIEMPO, tiempo);
		log.info(Constantes.LOG_3P, msjTx, Constantes.SEPARADOR, Constantes.SALTO_LINEA);
	}

	public static String getExceptionToMensaje(Exception e) {
		String msg;
		if (e.getCause() != null) {
			msg = e.getCause().toString();
		} else {
			msg = e.toString();
		}
		return msg;
	}

	public static DateFormat getLocalFormat() {
		DateFormat dateFormat = new SimpleDateFormat(Constantes.FORMATOFECHACABECERA);
		dateFormat.setTimeZone(TimeZone.getDefault());
		return dateFormat;
	}

	public static String printPrettyJSONString(Object o) throws JsonProcessingException {
		return new ObjectMapper().setDateFormat(Utilitarios.getLocalFormat())
				.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false).writerWithDefaultPrettyPrinter()
				.writeValueAsString(o);
	}

	public static Integer convertirInteger(Object object) {

		Integer res = null;
		if (object != null) {
			if (object instanceof BigDecimal) {
				BigDecimal bd = (BigDecimal) object;
				res = bd.intValueExact();
			} else {
				log.info(Constantes.LOG_1P, object.getClass().getSimpleName());
			}
		}
		return res;
	}

	public static Float convertirFloat(Object object) {
		Float res = null;
		if (object instanceof BigDecimal) {
			BigDecimal bd = (BigDecimal) object;
			res = bd.floatValue();		
		}
		return res;
	}

	public static String dateAString(Date fecha, String formato) {
		if (fecha != null) {
			SimpleDateFormat formatoDF = new SimpleDateFormat(formato, Locale.ROOT);
			return formatoDF.format(fecha);
		} else {
			return null;
		}
	}

	public static Calendar toCalendar(final String iso8601string) {
		Calendar calendar = GregorianCalendar.getInstance();
		String s = iso8601string.replace("Z", "+00:00");
		try {
			s = s.substring(0, 22) + s.substring(23); // to get rid of the ":"
			Date date = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ROOT).parse(s);
			calendar.setTime(date);
		} catch (IndexOutOfBoundsException e) {
			log.error("Ocurrio un error al recorrer la cadena de Fecha", e);
			calendar = null;
		} catch (ParseException e) {
			log.error("Ocurrio un error al convertir a Date la cadena de la fecha", e);
			calendar = null;
		}
		return calendar;
	}

	public static Date stringADate(String fecha, String formatoFecha) {
		Date fechaGenerada = null;
		if (fecha != null) {
			try {
				SimpleDateFormat formato = new SimpleDateFormat(formatoFecha);
				fechaGenerada = formato.parse(fecha);
			} catch (Exception e) {
				log.error("Error al formatear fecha :" + fecha, e);
			}
		}

		return fechaGenerada;
	}

	public static XMLGregorianCalendar convertDateToGregorianCalendar(Date fecha) {
		GregorianCalendar calender = new GregorianCalendar();
		calender.setTime(fecha);

		try {
			return DatatypeFactory.newInstance().newXMLGregorianCalendarDate(calender.get(Calendar.YEAR),
					calender.get(Calendar.MONTH) + 1, calender.get(Calendar.DAY_OF_MONTH),
					DatatypeConstants.FIELD_UNDEFINED);
		} catch (DatatypeConfigurationException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static XMLGregorianCalendar toXMLGregorianCalendarTimeStamp(Timestamp timeStamp)
			throws DatatypeConfigurationException {
		GregorianCalendar c = new GregorianCalendar();
		c.setTime(timeStamp);
		XMLGregorianCalendar date2 = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		return date2;
	}

	public static Date XMLGregorianCalendarToDate(XMLGregorianCalendar calendar) {
		if (calendar == null) {
			return null;
		}
		return calendar.toGregorianCalendar().getTime();
	}

	public static String obtenerIp() {
		String ip = Constantes.VACIO;
		try {
			ip = InetAddress.getLocalHost().getHostAddress();
		} catch (Exception e) {
			log.error("Error al obtener IP: " + e.getMessage(), e);
		}
		return ip;
	}

}
