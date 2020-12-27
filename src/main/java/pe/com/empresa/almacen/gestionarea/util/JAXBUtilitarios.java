package pe.com.empresa.almacen.gestionarea.util;

import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Node;
import org.w3c.dom.bootstrap.DOMImplementationRegistry;
import org.w3c.dom.ls.DOMImplementationLS;
import org.w3c.dom.ls.LSSerializer;
import org.xml.sax.InputSource;

public final class JAXBUtilitarios {
	private static final Logger log = LoggerFactory.getLogger(JAXBUtilitarios.class);

	@SuppressWarnings("rawtypes")
	private static HashMap<Class, JAXBContext> mapContexts = new HashMap<>();

	private static String ERROR_PARSEANDO = "Error parseando object to xml:";

	private JAXBUtilitarios() {
		super();
	}

	@SuppressWarnings("rawtypes")
	private static JAXBContext obtainJaxBContextFromClass(Class clas) {
		JAXBContext context;
		context = mapContexts.get(clas);
		if (context == null) {
			try {
				log.info("Inicializando jaxcontext... para la clase " + clas.getName());
				context = JAXBContext.newInstance(clas);
				mapContexts.put(clas, context);
			} catch (Exception e) {
				log.error("Error creando JAXBContext:", e);
			}
		}
		return context;
	}

	public static String getXmlTextFromJaxB(Object objJaxB) {

		String commandoRequestEnXml = null;
		JAXBContext context;
		try {
			context = obtainJaxBContextFromClass(objJaxB.getClass());
			if (context != null) {
				Marshaller marshaller = context.createMarshaller();
				StringWriter xmlWriter = new StringWriter();
				marshaller.marshal(objJaxB, xmlWriter);
				commandoRequestEnXml = prettyFormatXML(xmlWriter.toString());
			}
		} catch (Exception e) {
			log.error(ERROR_PARSEANDO, e);
		}
		return commandoRequestEnXml;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String anyObjectToXmlText(Object objJaxB) {

		String commandoRequestEnXml = null;
		JAXBContext context;
		try {
			context = obtainJaxBContextFromClass(objJaxB.getClass());
			if (context != null) {
				Marshaller marshaller = context.createMarshaller();
				StringWriter xmlWriter = new StringWriter();
				marshaller.marshal(
						new JAXBElement(new QName("", objJaxB.getClass().getName()), objJaxB.getClass(), objJaxB),
						xmlWriter);
				commandoRequestEnXml = xmlWriter.toString();
			}
		} catch (Exception e) {
			log.error(ERROR_PARSEANDO, e);
		}
		return commandoRequestEnXml;
	}

	public static String prettyFormatXML(String xml) {
		try {
			final InputSource src = new InputSource(new StringReader(xml));
			final Node document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(src)
					.getDocumentElement();
			final Boolean keepDeclaration = Boolean.valueOf(xml.startsWith("<?xml"));
			final DOMImplementationRegistry registry = DOMImplementationRegistry.newInstance();
			final DOMImplementationLS impl = (DOMImplementationLS) registry.getDOMImplementation("LS");
			final LSSerializer writer = impl.createLSSerializer();

			writer.getDomConfig().setParameter("format-pretty-print", Boolean.TRUE);
			writer.getDomConfig().setParameter("xml-declaration", keepDeclaration);
			return writer.writeToString(document);
		} catch (Exception e) {
			log.error(ERROR_PARSEANDO, e);
		}
		return null;
	}

	public static String getErrorTrace(Exception error) {
		StringWriter errors = new StringWriter();
		error.printStackTrace(new PrintWriter(errors));
		return errors.toString();
	}
}
