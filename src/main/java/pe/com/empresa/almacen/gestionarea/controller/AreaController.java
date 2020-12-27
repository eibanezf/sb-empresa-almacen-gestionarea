package pe.com.empresa.almacen.gestionarea.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

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
import pe.com.empresa.almacen.gestionarea.service.AreaService;
import pe.com.empresa.almacen.gestionarea.util.CastingMapper;
import pe.com.empresa.almacen.gestionarea.util.Constantes;
import pe.com.empresa.almacen.gestionarea.util.PropiedadesExternas;
import pe.com.empresa.almacen.gestionarea.util.Utilitarios;

@RestController
@RequestMapping(Constantes.BASE_PATH)
public class AreaController {
	private static final Logger log = LoggerFactory.getLogger(AreaController.class);

	@Autowired
	private PropiedadesExternas p;

	@Autowired
	private AreaService areaService;

	@PostMapping(value = Constantes.METODO_LISTAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public ListarAreasResponse listarAreas(
			@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion) 
					throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_LISTAR;

		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		ListarAreasResponseType responseType = new ListarAreasResponseType();
		ListarAreasResponse response = new ListarAreasResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);

		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(Constantes.VACIO);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			response = areaService.listarAreas(msjTx, headerRequest);
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(p.idt3Msj + Constantes.ESPACIO + e.getCause());

			responseType.setHeaderResponse(headerResponse);
			response.setListarAreasResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getListarAreasResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_BUSCAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public BuscarAreaResponse buscarArea(
			@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody BuscarAreaRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_BUSCAR;

		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		BuscarAreaResponseType responseType = new BuscarAreaResponseType();
		BuscarAreaResponse response = new BuscarAreaResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);

		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getBuscarAreaRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setBuscarAreaResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = areaService.buscarArea(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setBuscarAreaResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getBuscarAreaResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_CREAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public CrearAreaResponse crearUsuario(@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody CrearAreaRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_CREAR;

		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		CrearAreaResponseType responseType = new CrearAreaResponseType();
		CrearAreaResponse response = new CrearAreaResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);
		
		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getCrearAreaRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setCrearAreaResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = areaService.crearArea(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setCrearAreaResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getCrearAreaResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_ACTUALIZAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public ActualizarAreaResponse actualizarArea(@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody ActualizarAreaRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_ACTUALIZAR;
		
		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		ActualizarAreaResponseType responseType = new ActualizarAreaResponseType();
		ActualizarAreaResponse response = new ActualizarAreaResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);
		
		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getActualizarAreaRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setActualizarAreaResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = areaService.actualizarArea(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setActualizarAreaResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getActualizarAreaResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}

	@PostMapping(value = Constantes.METODO_ELIMINAR, consumes = Constantes.APPLICATION_JSON, produces = Constantes.APPLICATION_JSON)
	public EliminarAreaResponse eliminarPorId(@RequestHeader(name = Constantes.IDTRANSACCION, required = true) String idTransaccion,
			@RequestHeader(name = Constantes.USUARIO, required = true) String usuario, 
			@RequestHeader(name = Constantes.APLICACION, required = true) String aplicacion,
			@RequestBody EliminarAreaRequest bodyRequest) throws JsonProcessingException {
		long tiempo = System.currentTimeMillis();
		String metodo = Constantes.METODO_ELIMINAR;
		
		HeaderRequestType headerRequest = new HeaderRequestType(idTransaccion, usuario, aplicacion);
		HeaderResponseType headerResponse = new HeaderResponseType();
		EliminarAreaResponseType responseType = new EliminarAreaResponseType();
		EliminarAreaResponse response = new EliminarAreaResponse();

		String idTx = headerRequest.getIdTransaccion();
		String msjTx = Utilitarios.getMsjTx(metodo, idTx);

		String printHeader = null;
		String printRequest = null;
		String printResponse = null;

		Utilitarios.imprimirLogInicioMetodo(msjTx, metodo);
		
		try {
			printHeader = Utilitarios.printPrettyJSONString(headerRequest);
			printRequest = Utilitarios.printPrettyJSONString(bodyRequest);

			log.info(Constantes.LOG_2P, msjTx, Constantes.PARAMETROSENTRADA);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSHEADER, Constantes.SALTO_LINEA, printHeader);
			log.info(Constantes.LOG_4P, msjTx, Constantes.PARAMETROSBODY, Constantes.SALTO_LINEA, printRequest);

			HeaderResponseType validacionResponse = (HeaderResponseType) CastingMapper.validarParametrosEntrada(msjTx, headerRequest, bodyRequest.getEliminarAreaRequest());

			if (!Constantes.STR_CERO.equals(validacionResponse.getCodigoRespuesta())) {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSINCORRECTOS);
				headerResponse.setCodigoRespuesta(validacionResponse.getCodigoRespuesta());
				headerResponse.setMensajeRespuesta(validacionResponse.getMensajeRespuesta());
				responseType.setHeaderResponse(headerResponse);
				response.setEliminarAreaResponse(responseType);
			} else {
				log.info(Constantes.LOG_2P, msjTx, Constantes.VALIDACIONPARAMETROSCORRECTOS);
				response = areaService.eliminarArea(msjTx, bodyRequest);
			}
		} catch (Exception e) {
			headerResponse.setCodigoRespuesta(p.idt3Cod);
			headerResponse.setMensajeRespuesta(String.format(p.idt3Msj, Utilitarios.getExceptionToMensaje(e)));

			responseType.setHeaderResponse(headerResponse);
			response.setEliminarAreaResponse(responseType);

			log.error(Constantes.LOG_2P, msjTx, Utilitarios.getExceptionToMensaje(e));
		} finally {
			response.getEliminarAreaResponse().getHeaderResponse().setIdTransaccion(idTx);
			printResponse = Utilitarios.printPrettyJSONString(response);
			tiempo = (System.currentTimeMillis() - tiempo);
			Utilitarios.imprimirLogFinMetodo(msjTx, metodo, printResponse, tiempo);
		}

		return response;
	}
}
