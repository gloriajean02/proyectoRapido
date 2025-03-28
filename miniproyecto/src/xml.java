import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * @author Gloria Curado García
 * @author Guadalupe Morcillo Guijarro
 * @author María Teresa Calvo Peña
 */

public class xml {

    private File fichero;
    private File carpetaSeleccionada;

    public xml(String ruta){
        this.fichero = new File(ruta);
        carpetaSeleccionada = fichero.getParentFile();
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(String newRuta) {
        this.fichero = new File(newRuta);
        this.carpetaSeleccionada = fichero.getParentFile();
    }

    public File getCarpetaSeleccionada() {
        return carpetaSeleccionada;
    }

    public String getContenidoCarpeta() {
        if (carpetaSeleccionada != null && carpetaSeleccionada.isDirectory()) {
            StringBuilder contenido = new StringBuilder();
            File[] archivos = carpetaSeleccionada.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    contenido.append(archivo.getName()).append("\n");
                }
            }
            return contenido.toString();
        }
        return "Carpeta vacía o no válida";
    }

    public boolean comprobarExiste(){
        if (fichero.exists()) {
            return true;
        } else return false;
    }

    public boolean comprobarFicheroVacio() {
        boolean vacio = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            if ((br.readLine()) == null){
                vacio = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            vacio = true;
        }
        return vacio;
    }

    public void escribirFichero(GestorDatos gestor) {
    try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
        String linea;
        br.readLine(); 
        boolean dentroDeElemento = false;
        HashMap<String, String> nuevoElemento = null;

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
    
            if (linea.startsWith("<") && !linea.startsWith("</") && !linea.contains("</")) {
                dentroDeElemento = true;
                nuevoElemento = new HashMap<>();
            }

            if (linea.startsWith("</") && nuevoElemento != null && !nuevoElemento.isEmpty())  {
                dentroDeElemento = false;
                gestor.insertarElemento(nuevoElemento);
                nuevoElemento = null;
                
            }

            if (dentroDeElemento && nuevoElemento != null) {
                linea = linea.replace("<", "").replace(">", " ").replace("/", " ");
                String[] palabras = linea.split(" ");
                
                if (palabras.length > 2) { 
                    String clave = palabras[0].trim();
                    String valor = palabras[1].trim();

                    nuevoElemento.put(clave, valor);

                }
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    @Override
    public String toString() {
        return "Fichero: "+fichero.getName();
    }

}