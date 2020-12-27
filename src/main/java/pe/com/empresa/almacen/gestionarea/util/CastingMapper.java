package pe.com.empresa.almacen.gestionarea.util;

import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import pe.com.empresa.almacen.gestionarea.canonical.type.ActualizarAreaRequestType;
import pe.com.empresa.almacen.gestionarea.canonical.type.BuscarAreaRequestType;
import pe.com.empresa.almacen.gestionarea.canonical.type.CrearAreaRequestType;
import pe.com.empresa.almacen.gestionarea.canonical.type.EliminarAreaRequestType;
import pe.com.empresa.almacen.gestionarea.canonical.type.HeaderRequestType;
import pe.com.empresa.almacen.gestionarea.canonical.type.HeaderResponseType;

public class CastingMapper {
	private static final Logger log = LoggerFactory.getLogger(CastingMapper.class);

	public static Object validarParametrosEntrada(String msjTx, HeaderRequestType header, Object beanRequest) {
		HeaderResponseType headerResponse = null;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<HeaderRequestType>> constraintViolations = validator.validate(header);

		log.info(Constantes.LOG_2P, msjTx, "Evaluando header y body");

		if (!constraintViolations.isEmpty()) {
			headerResponse = new HeaderResponseType();
			headerResponse.setCodigoRespuesta(String.valueOf(Constantes.STR_UNO));
			headerResponse.setMensajeRespuesta(Constantes.DATOS_HEADER_INCOMPLETOS + header.toString());
			log.info(Constantes.LOG_2P, msjTx, "Header no cumple validacion");
			return headerResponse;
		}
		String sBody = requestConfirmValues(beanRequest);
		if (!sBody.isEmpty()) {
			headerResponse = new HeaderResponseType();
			headerResponse.setCodigoRespuesta(String.valueOf(Constantes.STR_UNO));
			headerResponse.setMensajeRespuesta(Constantes.DATOS_BODY_INCOMPLETOS + sBody);
			log.info(Constantes.LOG_2P, msjTx, "Body no cumple con parametros obligatorios");
			return headerResponse;
		}

		log.info(Constantes.LOG_2P, msjTx, "Validacion correcta de header y body");
		headerResponse = new HeaderResponseType();
		headerResponse.setCodigoRespuesta(Constantes.STR_CERO);

		return headerResponse;
	}

	public static String requestConfirmValues(Object beanRequest) {
		String msgError = Constantes.VACIO;
		if (beanRequest instanceof BuscarAreaRequestType) {
			msgError = beanHaveViolations(beanRequest);
			return msgError;
		}
		
		if (beanRequest instanceof CrearAreaRequestType) {
			msgError = beanHaveViolations(beanRequest);
			return msgError;
		}
		
		if (beanRequest instanceof ActualizarAreaRequestType) {
			msgError = beanHaveViolations(beanRequest);
			return msgError;
		}
		
		if (beanRequest instanceof EliminarAreaRequestType) {
			msgError = beanHaveViolations(beanRequest);
			return msgError;
		}

		return msgError;
	}

	public static String beanHaveViolations(Object request) {
		String msgError = Constantes.VACIO;
		ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
		Validator validator = factory.getValidator();
		Set<ConstraintViolation<Object>> constraintViolations = validator.validate(request);
		if (constraintViolations.size() >= Constantes.INT_UNO) {
			for (ConstraintViolation<Object> cv : constraintViolations) {
				msgError = cv.getPropertyPath() + Constantes.PUNTO + cv.getMessage();
			}
		}
		
		return msgError;
	}
}
