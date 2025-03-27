import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

public class Conversor {

    File fichero;
    String rutaArchivo;
    GestorDatos gestor;

    public Conversor (GestorDatos gestor){
        //QUE CADA UNA CAMBIE A LO QUE NECESITE
        fichero = new File("miniproyecto/fichero/coches.json");
        //LA RUTA A DONDE QUEREMOS COPIAR EL ARCHIVO
        rutaArchivo = "miniproyecto/fichero";
        //DESDE EL MAIN, CUANDO PROBEMOS, TENDREMOS QUE LLAMAR AL GESTOR QUE ANTES HEMOS LLENADO
        this.gestor = gestor;
    }

    public Conversor(String ruta, String rutaArchivo, GestorDatos gestor){
        this.fichero = new File(ruta);
        this.rutaArchivo = rutaArchivo;
        this.gestor = gestor;
    }

    public void conversorXML(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("Hola, mundo!\n");
            bw.write("Esta es una prueba de escritura en un archivo usando BufferedWriter.\n");
            bw.write("Fin del archivo.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void conversorCSV(){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("Hola, mundo!\n");
            bw.write("Esta es una prueba de escritura en un archivo usando BufferedWriter.\n");
            bw.write("Fin del archivo.\n");
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
