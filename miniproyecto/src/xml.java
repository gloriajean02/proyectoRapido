
import org.w3c.dom.*;
import javax.xml.parsers.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.io.*;
import java.util.*;

public class xml{

    public static List<Map<String, String>> leerXML(File archivo, String etiquetaRaiz) {
        List<Map<String, String>> datos = new ArrayList<>();
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.parse(archivo);
            doc.getDocumentElement().normalize();

            NodeList listaElementos = doc.getElementsByTagName(etiquetaRaiz);
            for (int i = 0; i < listaElementos.getLength(); i++) {
                Node nodo = listaElementos.item(i);
                if (nodo.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) nodo;
                    Map<String, String> mapaDatos = new HashMap<>();

                    NodeList hijos = elemento.getChildNodes();
                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node hijo = hijos.item(j);
                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                            mapaDatos.put(hijo.getNodeName(), hijo.getTextContent());
                        }
                    }
                    datos.add(mapaDatos);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return datos;
    }

    public static void escribirXML(List<Map<String, String>> datos, String etiquetaRaiz, String archivoSalida) {
        try {
            DocumentBuilderFactory fabrica = DocumentBuilderFactory.newInstance();
            DocumentBuilder constructor = fabrica.newDocumentBuilder();
            Document doc = constructor.newDocument();

            Element elementoRaiz = doc.createElement(etiquetaRaiz + "s");
            doc.appendChild(elementoRaiz);

            for (Map<String, String> mapa : datos) {
                Element elemento = doc.createElement(etiquetaRaiz);
                elementoRaiz.appendChild(elemento);

                for (Map.Entry<String, String> entry : mapa.entrySet()) {
                    Element nodo = doc.createElement(entry.getKey());
                    nodo.appendChild(doc.createTextNode(entry.getValue()));
                    elemento.appendChild(nodo);
                }
            }

            TransformerFactory fabricaTransformador = TransformerFactory.newInstance();
            Transformer transformador = fabricaTransformador.newTransformer();
            transformador.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource fuente = new DOMSource(doc);
            StreamResult resultado = new StreamResult(new File(archivoSalida));
            transformador.transform(fuente, resultado);

            System.out.println("Archivo XML generado correctamente.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        File archivo = new File("C:\\Users\\Guada\\Desktop\\DAW\\Programación\\Tema 6\\proyectoRapido\\miniproyecto\\fichero\\coches.xml");
       
        List<Map<String, String>> datos = leerXML(archivo, "coche");

        for (Map<String, String> mapa : datos) {
            System.out.println(mapa);
        }

        escribirXML(datos, "coche", "nuevo_coches.xml");
    }
}