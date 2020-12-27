package pe.com.empresa.almacen.gestionarea.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pe.com.empresa.almacen.gestionarea.canonical.ActualizarAreaRequest;
import pe.com.empresa.almacen.gestionarea.canonical.ActualizarAreaResponse;
import pe.com.empresa.almacen.gestionarea.canonical.BuscarAreaRequest;
import pe.com.empresa.almacen.gestionarea.canonical.BuscarAreaResponse;
import pe.com.empresa.almacen.gestionarea.canonical.CrearAreaRequest;
import pe.com.empresa.almacen.gestionarea.canonical.CrearAreaResponse;
import pe.com.empresa.almacen.gestionarea.canonical.EliminarAreaRequest;
import pe.com.empresa.almacen.gestionarea.canonical.EliminarAreaResponse;
import pe.com.empresa.almacen.gestionarea.canonical.ListarAreasResponse;
import pe.com.empresa.almacen.gestionarea.canonical.type.ActualizarAreaResponseType;
import pe.com.empresa.almacen.gestionarea.canonical.type.BuscarAreaResponseType;
import pe.com.empresa.almacen.gestionarea.canonical.type.CrearAreaResponseType;
import pe.com.empresa.almacen.gestionarea.canonical.type.EliminarAreaResponseType;
import pe.com.empresa.almacen.gestionarea.canonical.type.HeaderRequestType;
import pe.com.empresa.almacen.gestionarea.canonical.type.HeaderResponseType;
import pe.com.empresa.almacen.gestionarea.canonical.type.ListarAreasResponseType;
import pe.com.empresa.almacen.gestionarea.entity.Area;
import pe.com.empresa.almacen.gestionarea.repository.AreaRepository;
import pe.com.empresa.almacen.gestionarea.util.Constantes;
import pe.com.empresa.almacen.gestionarea.util.PropiedadesExternas;
import pe.com.empresa.almacen.gestionarea.util.ResponseValidarVacio;
import pe.com.empresa.almacen.gestionarea.util.Utilitarios;

@Service
public class AreaService {
	private static final Logger log = LoggerFactory.getLogger(AreaService.class);

	@Autowired
	private PropiedadesExternas p;

	@Autowired
	AreaRepository areaRepository;

	@Transactional(readOnly = true)
	public ListarAreasResponse listarAreas(String msjTx, HeaderRequestType headerRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		ListarAreasResponseType responseType = new ListarAreasResponseType();
		ListarAreasResponse response = new ListarAreasResponse();

		try {
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_OBT_AREAS);
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			
			List<Area> areas = (List<Area>) areaRepository.findAll();

			headerResponse.setCodigoRespuesta(p.idf0Cod);
			headerResponse.setMensajeRespuesta(p.idf0Msj);
			
			responseType.setHeaderResponse(headerResponse);
			responseType.setAreas(areas);

			response.setListarAreasResponse(responseType);
		} catch (Exception e) {
			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		}
		return response;
	}

	@Transactional(readOnly = true)
	public BuscarAreaResponse buscarArea(String msjTx, BuscarAreaRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		BuscarAreaResponseType responseType = new BuscarAreaResponseType();
		BuscarAreaResponse response = new BuscarAreaResponse();
		
		try {
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_OBT_AREA);
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			
			ResponseValidarVacio responseValidarVacio = validarVacio(msjTx, bodyRequest);
			
			if(!responseValidarVacio.esValido()) {
				headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
				headerResponse.setMensajeRespuesta(responseValidarVacio.getMensaje());
				responseType.setHeaderResponse(headerResponse);
				response.setBuscarAreaResponse(responseType);
				
				return response;
			}
			
			Long idArea = Long.valueOf(bodyRequest.getBuscarAreaRequest().getIdArea());
			
			Area area = areaRepository.findById(idArea).orElse(null);
			
			if(null == area) {
				headerResponse.setCodigoRespuesta(p.idf1Cod);
				headerResponse.setMensajeRespuesta(String.format(p.idf1Msj, idArea));
				responseType.setHeaderResponse(headerResponse);
				response.setBuscarAreaResponse(responseType);
				
				return response; 
			}
			
			headerResponse.setCodigoRespuesta(p.idf0Cod);
			headerResponse.setMensajeRespuesta(p.idf0Msj);
			
			responseType.setHeaderResponse(headerResponse);
			responseType.setArea(area);
			
			response.setBuscarAreaResponse(responseType);
						
		} catch (Exception e) {
			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));			
		}
		
		return response;
	}

	@Transactional
	public CrearAreaResponse crearArea(String msjTx, CrearAreaRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		CrearAreaResponseType responseType = new CrearAreaResponseType();
		CrearAreaResponse response = new CrearAreaResponse();
		
		try {
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_CREAR_AREA);
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			
			ResponseValidarVacio responseValidarVacio = validarVacio(msjTx, bodyRequest);
			
			if(!responseValidarVacio.esValido()) {
				headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
				headerResponse.setMensajeRespuesta(responseValidarVacio.getMensaje());
				responseType.setHeaderResponse(headerResponse);
				response.setCrearAreaResponse(responseType);
				
				return response;
			}
			
			Area requestArea = new Area();
			requestArea.setNombre(bodyRequest.getCrearAreaRequest().getNombre());

			Area responseArea = areaRepository.save(requestArea);
			responseType.setArea(responseArea);

			headerResponse.setCodigoRespuesta(p.idf0Cod);
			headerResponse.setMensajeRespuesta(p.idf0Msj);

			responseType.setHeaderResponse(headerResponse);
			response.setCrearAreaResponse(responseType);
		} catch (Exception e) {
			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));			
		}
		
		return response;
	}
	
	@Transactional
	public ActualizarAreaResponse actualizarArea(String msjTx, ActualizarAreaRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		ActualizarAreaResponseType responseType = new ActualizarAreaResponseType();
		ActualizarAreaResponse response = new ActualizarAreaResponse();
		
		try {
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_ACTUALIZAR_AREA);
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			
			ResponseValidarVacio responseValidarVacio = validarVacio(msjTx, bodyRequest);
			
			if(!responseValidarVacio.esValido()) {
				headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
				headerResponse.setMensajeRespuesta(responseValidarVacio.getMensaje());
				responseType.setHeaderResponse(headerResponse);
				response.setActualizarAreaResponse(responseType);
				
				return response;
			}
			
			Area requestArea = new Area();
			requestArea.setIdArea(bodyRequest.getActualizarAreaRequest().getIdArea());
			requestArea.setNombre(bodyRequest.getActualizarAreaRequest().getNombre());

			Area responseArea = areaRepository.save(requestArea);
			responseType.setArea(responseArea);

			headerResponse.setCodigoRespuesta(p.idf0Cod);
			headerResponse.setMensajeRespuesta(p.idf0Msj);

			responseType.setHeaderResponse(headerResponse);
			response.setActualizarAreaResponse(responseType);
		} catch (Exception e) {
			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));			
		}
		
		return response;
	}

	@Transactional
	public EliminarAreaResponse eliminarArea(String msjTx, EliminarAreaRequest bodyRequest) {
		HeaderResponseType headerResponse = new HeaderResponseType();
		EliminarAreaResponseType responseType = new EliminarAreaResponseType();
		EliminarAreaResponse response = new EliminarAreaResponse();

		try {
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			log.info(Constantes.LOG_2P, msjTx, Constantes.ACT1_ELIMINAR_AREA);
			log.info(Constantes.LOG_2P, msjTx, Constantes.SEPARADOR);
			
			ResponseValidarVacio responseValidarVacio = validarVacio(msjTx, bodyRequest);
			
			if(!responseValidarVacio.esValido()) {
				headerResponse.setCodigoRespuesta(Constantes.STR_UNO);
				headerResponse.setMensajeRespuesta(responseValidarVacio.getMensaje());
				responseType.setHeaderResponse(headerResponse);
				response.setEliminarAreaResponse(responseType);
				
				return response;
			}
			
			Long idArea = Long.valueOf(bodyRequest.getEliminarAreaRequest().getIdArea());
			
			Area area = areaRepository.findById(idArea).orElse(null);
			
			if(null == area) {
				headerResponse.setCodigoRespuesta(p.idf1Cod);
				headerResponse.setMensajeRespuesta(String.format(p.idf1Msj, idArea));
				responseType.setHeaderResponse(headerResponse);
				response.setEliminarAreaResponse(responseType);
				
				return response; 
			}
			
			areaRepository.deleteById(idArea);
			
			headerResponse.setCodigoRespuesta(p.idf0Cod);
			headerResponse.setMensajeRespuesta(p.idf0Msj);
			
			responseType.setHeaderResponse(headerResponse);
			response.setEliminarAreaResponse(responseType);
		} catch (Exception e) {
			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));			
		}
		
		return response;
	}
	
	public ResponseValidarVacio validarVacio(String msjTx, Object beanRequest) {
		ResponseValidarVacio response = new ResponseValidarVacio();
		String mensaje = Constantes.VACIO;
		String atributo = Constantes.VACIO;
		boolean valido = true;
		
		if (beanRequest instanceof BuscarAreaRequest) {
			BuscarAreaRequest request = ((BuscarAreaRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getBuscarAreaRequest().getIdArea())) {
				atributo = "idArea";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}
		
		if (beanRequest instanceof CrearAreaRequest) {
			CrearAreaRequest request = ((CrearAreaRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getCrearAreaRequest().getNombre())) {
				atributo = "nombre";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}
		
		if (beanRequest instanceof ActualizarAreaRequest) {
			ActualizarAreaRequest request = ((ActualizarAreaRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getActualizarAreaRequest().getNombre())) {
				atributo = "nombre";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}
		
		if (beanRequest instanceof EliminarAreaRequest) {
			EliminarAreaRequest request = ((EliminarAreaRequest) beanRequest);
			
			if(Constantes.VACIO.equals(request.getEliminarAreaRequest().getIdArea())) {
				atributo = "idArea";
				mensaje = Constantes.DATOS_BODY_INCOMPLETOS + atributo + Constantes.PUNTO + Constantes.NOT_NULL;
				valido = false;

				log.info(Constantes.LOG_2P, msjTx, mensaje);
			}
		}

		response.setMensaje(mensaje);
		response.setAtributo(atributo);
		response.setValido(valido);
		
		return response;
	}
}
