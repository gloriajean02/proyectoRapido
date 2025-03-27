import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
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

}
