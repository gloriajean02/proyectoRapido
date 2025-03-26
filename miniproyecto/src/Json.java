import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class Json {

    File fichero;

    public Json (){
        fichero = new File("miniproyecto/fichero/coches.json");
    }

    public Json(String ruta){
        this.fichero = new File(ruta);
    }

    public File getFichero() {
        return fichero;
    }

    public void setFichero(String newRuta) {
        this.fichero = new File(newRuta);
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
        boolean dentroDeElemento = false;
        HashMap<String, String> nuevoElemento = new HashMap<>();

        while ((linea = br.readLine()) != null) {
            linea = linea.trim();
    
            if (linea.startsWith("{")) {
                dentroDeElemento = true;
                nuevoElemento = new HashMap<>();
            }

            if (linea.startsWith("}")) {
                dentroDeElemento = false;
                gestor.insertarElemento(nuevoElemento);
            }

            if (dentroDeElemento) {
                String[] palabras = linea.split(":");

                if (palabras.length == 2) { 
                    String clave = palabras[0].trim().replace("\"", "");
                    String valor = palabras[1].trim().replace("\"", "").replace(",", "");

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
