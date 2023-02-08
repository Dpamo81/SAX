import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

public class Start {
//	Para crear el archivo XML y escribir dentro.
	static void CrearXML() throws TransformerFactoryConfigurationError, TransformerException, ParserConfigurationException{
		String primerNombre ="";
		String segundoNombre="";
		String datoPersona="";
		String valor="";
		String nombre="";
		int n=0;
		int m=0;
		int i=0;
		int j =0;
		Scanner edd = new Scanner(System.in);
		System.out.println("Introduzca el nombre del fichero a crear: ");
		String ruta = edd.nextLine();
//		Creamos la instancia para crear el documento.
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
//		Creamos un documento vacio
		DocumentBuilder db = dbf.newDocumentBuilder();
//		Creamos un DOMIImplementation.
		DOMImplementation domi = db.getDOMImplementation();
		System.out.println("Introduzca el nombre del primer elemento a crear.");
		edd.nextLine();
		primerNombre = edd.nextLine();
//		Creo un elemento con un nombre raiz.
		Document doc = domi.createDocument(null, primerNombre, null);
		doc.setXmlVersion("1.0");
		System.out.println("Introduzca el nombre del segundo elemento a crear.");
		segundoNombre = edd.nextLine();	
		System.out.println("Cuantos " + segundoNombre + " quieres introducir.");
		n= edd.nextInt();
		System.out.println("Introduzca el numero de campos ha añadir");
		m = edd.nextInt();		
		for (i = 1 ; i<=n ; i++) {
			Element tercerNombre = doc.createElement(segundoNombre);// para crear el nodo
			doc.getDocumentElement().appendChild(tercerNombre); // para incluir el elemento al documento en la raiz
			for(j=1;j<=m;j++) {
				System.out.println("Introduzca el nombre del valor " + j + " del objeto " + i);
				edd.nextLine();
				datoPersona=edd.next();
				System.out.println("Introduzca el valor " + j + " del objeto " + i);
				valor=edd.next();					
				CrearElemento(datoPersona,valor,tercerNombre,doc);
//				Asocio el source con el documento.
				Source source = new DOMSource(doc);
//				Creo el Result indicando que fichero se va crear.
				Result result = new StreamResult(new java.io.File(nombre + ".xml"));
//				Creo un transform, se crea un fichero XML
				Transformer transformer = TransformerFactory.newInstance().newTransformer();				
				transformer.transform(source,result);
			}
		}
	}
//	Para la insercion de los datos.
	static void CrearElemento(String datoPersona, String valor, Element raiz, Document doc) {
//		Creo los elementos.
		Element elem = doc.createElement(datoPersona);
		Text text = doc.createTextNode(valor);
		raiz.appendChild(elem);
		elem.appendChild(text);
	}
//	Para leer en SAX.
	static void LeerSax() throws SAXException, IOException {
		String f ="";
		Scanner edd = new Scanner(System.in);
		System.out.println("Introduzca el nombre del fichero a leer: ");
		f = edd.nextLine();
		XMLReader procesadorXML = XMLReaderFactory.createXMLReader();
		GestorContenido gestor = new GestorContenido();
		procesadorXML.setContentHandler(gestor);
		InputSource fileXML = new InputSource(f);
		procesadorXML.parse(fileXML);
		
	}
	
	public static void main(String[] args) throws TransformerFactoryConfigurationError, TransformerException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
//		Creamos Variables.
		int opcion=0;
		int i=0;
		Scanner entradaDeDatos = new Scanner (System.in);
//		Menú
		do {
			System.out.println("*********************************************");
			System.out.println("*** Persistencia XML con java (DOM y SAX) ***");
			System.out.println("*********************************************");
			System.out.println("");
			System.out.println("Escoja una opción de la lista.");
			System.out.println("   1.-Crear archivo xml.");
			System.out.println("   2.-Leer fichero xml con SAX.");
			System.out.println("   0.-Salir.");
			opcion = entradaDeDatos.nextInt();
			
			if (opcion == 1) {
				CrearXML();
			}else if (opcion == 2) {
				LeerSax();
			}
			
		}while (opcion!=0);
	}

}
