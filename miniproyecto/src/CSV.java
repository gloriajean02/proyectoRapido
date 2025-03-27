import java.io.*;
import java.util.HashMap;

public class CSV {
    private File fichero;
    private File carpetaSeleccionada;

    /*Constructor por defecto que establece un archivo CSV por defecto */
    public CSV() {
        fichero = new File("proyectoRapido\\miniproyecto/fichero/coches.csv");
        carpetaSeleccionada = fichero.getParentFile();
    }

    /*Constructor que permite especificar una ruta de archivo CSV */
    public CSV(String ruta) {
        this.fichero = new File(ruta);
        this.carpetaSeleccionada = fichero.getParentFile();
    }

    /*Método para obtener el archivo CSV seleccionado */
    public File getFichero() {
        return fichero;
    }

    /*Método para cambiar el archivo CSV y actualizar la carpeta correspondiente */
    public void setFichero(String newRuta) {
        this.fichero = new File(newRuta);
        this.carpetaSeleccionada = fichero.getParentFile();
    }

    /*Método para obtener la carpeta donde se encuentra el archivo CSV */
    public File getCarpetaSeleccionada() {
        return carpetaSeleccionada;
    }

    /*Método para obtener el contenido de la carpeta seleccionada */
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

    /*Método para comprobar si el archivo CSV existe */
    public boolean comprobarExiste() {
        return fichero.exists();
    }

    public boolean comprobarExtension(){
        boolean extensionCorrecta = false;
        if (fichero.getName().endsWith(".csv")) {
            extensionCorrecta = true;
        } 
        
        return extensionCorrecta;
    }

    /*Método para comprobar si el archivo CSV está vacío */
    @SuppressWarnings("CallToPrintStackTrace")
    public boolean comprobarFicheroVacio() {
        boolean vacio = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            if ((br.readLine()) == null) {
                vacio = true;
            }
        } catch (IOException e) {
            e.printStackTrace();
            vacio = true;
        }
        return vacio;
    }

    /*Método para leer archivo CSV y almacenar sus datos GestorDatos */
    @SuppressWarnings("CallToPrintStackTrace")
    public void escribirFichero(GestorDatos gestor) {
        try (BufferedReader br = new BufferedReader(new FileReader(fichero))) {
            String primeraLinea = br.readLine(); // Leer encabezado
            if (primeraLinea == null) {
                System.out.println("El archivo CSV está vacío");
                return;
            }
            
            String[] encabezado = primeraLinea.split(",");
            String linea;

            while ((linea = br.readLine()) != null) {
                String[] valores = linea.split(",");
                HashMap<String, String> nuevoElemento = new HashMap<>();
                
                for (int i = 0; i < encabezado.length; i++) {
                    nuevoElemento.put(encabezado[i].trim(), valores[i].trim());
                }
                gestor.insertarElemento(nuevoElemento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Fichero: " + fichero.getName();
    }
}

