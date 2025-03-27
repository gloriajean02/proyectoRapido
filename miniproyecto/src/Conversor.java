import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

public class Conversor {

    private String rutaArchivo;
    private GestorDatos gestor;
    private String elementoRaiz;
    private String elementoItem;

    public Conversor (GestorDatos gestor){
        rutaArchivo = "miniproyecto/fichero";
        this.gestor = gestor;
        
    }

    public Conversor(String rutaArchivo, GestorDatos gestor){
        this.rutaArchivo = rutaArchivo;
        this.gestor = gestor;
      
    }

    public Conversor(GestorDatos gestor, String elementoRaiz, String elementoItem) {
        this.rutaArchivo = "miniproyecto/fichero/datos.xml";
        this.gestor = gestor;
        this.elementoRaiz = elementoRaiz;
        this.elementoItem = elementoItem;
    }

    public Conversor(String rutaArchivo, GestorDatos gestor, String elementoRaiz, String elementoItem) {
        this.rutaArchivo = rutaArchivo;
        this.gestor = gestor;
        this.elementoRaiz = elementoRaiz;
        this.elementoItem = elementoItem;
    }

    public void convertirXML() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
            bw.write("<" + elementoRaiz + ">\n");

            for (HashMap<String, String> elemento : gestor.getGestor()) {
                bw.write("  <" + elementoItem + ">\n");
                for (String clave : elemento.keySet()) {
                    bw.write("    <" + clave + ">" + elemento.get(clave) + "</" + clave + ">\n");
                }
                bw.write("  </" + elementoItem + ">\n");
            }

            bw.write("</" + elementoRaiz + ">\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conversorJSON(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo, true))) {
            bw.write("[\n");

            ArrayList<HashMap<String, String>> listadeHashmaps = gestor.getGestor();
            for (int i = 0; i < listadeHashmaps.size(); i++) {
                HashMap<String, String> elemento = listadeHashmaps.get(i);

                bw.write("  {\n");
                int contadorLineasHashmap = 0;

                for (Entry<String, String> entry : elemento.entrySet()){
                    bw.write("      \""+entry.getKey()+"\": "+"\""+entry.getValue()+"\"");
                    if (contadorLineasHashmap < elemento.size()-1) {
                        bw.write(",");
                    }
                    bw.write("\n");
                    contadorLineasHashmap++;
                }
                bw.write("  }");
                if (i < listadeHashmaps.size()-1) {
                    bw.write(",");
                }
                bw.write("\n");
            }
            bw.write("]\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    

    public void conversorCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            if (gestor != null && gestor.getGestor().size() > 0) {/*Comprobamos que el gestor no esté vacío */
                HashMap<String, String> primerElemento = gestor.getGestor().get(0);/*Obtenemos el primer elemento del gestor que hay en HashMap */
                
                List<String> claves = List.copyOf(primerElemento.keySet());/*Usamos el método keyset() para obtener todas las claves, las pasamos a una lista para poer acceder por índice */
                for (int i = 0; i < claves.size(); i++) {
                    bw.write(claves.get(i));/*Escribe el valor de la calve en la posición i de la lista claves */
                    if (i < claves.size() - 1) {/*Si no es la última clave de la lista */
                        bw.write(","); /*Agregar coma entre las claves*/
                    }
                }
                bw.newLine();

            
                List<HashMap<String, String>> elementos = gestor.getGestor();/*Extraemos todos los elementos del gestior que son HashMap<String, String> */
                for (int i = 0; i < elementos.size(); i++) {/*Recorremos la lista, obtenemos los valores de cada uno con elemento.values(). Los valores se copian en una lista para poder acceder a ellos de manera ordenada */
                    HashMap<String, String> elemento = elementos.get(i);
                    List<String> valores = List.copyOf(elemento.values());

                    for (int j = 0; j < valores.size(); j++) {/*Recorremos los valores del HashMap y los escribimos en el archivo, si la clave no es el último valor, agregamos una coma entre los valores */
                        bw.write(valores.get(j));
                        if (j < valores.size() - 1) {
                            bw.write(","); // Agregar coma entre los valores
                        }
                }
                bw.newLine();
                }
                System.out.println("Archivo CSV creado correctamente en: " + rutaArchivo);
            } else {
                System.out.println("No hay datos en el Gestor para convertir.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

