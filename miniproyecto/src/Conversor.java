import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {
            bw.write("Hola, mundo!\n");
            bw.write("Esta es una prueba de escritura en un archivo usando BufferedWriter.\n");
            bw.write("Fin del archivo.\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
