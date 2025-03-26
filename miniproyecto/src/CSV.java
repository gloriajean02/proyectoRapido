import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class CSV {
    private static final List<Map<String, String>> dato = new ArrayList<>();

    private File carpetaSeleccionada;    // Ruta de la carpeta seleccionada
    private File ficheroSeleccionado;    // Fichero seleccionado
    private String contenidoCarpeta;    // Contenido de la carpeta seleccionada

    // Getters y Setters
    public File getCarpetaSeleccionada() {
        return carpetaSeleccionada;
    }

    public void setCarpetaSeleccionada(File carpetaSeleccionada) {
        this.carpetaSeleccionada = carpetaSeleccionada;
    }

    public File getFicheroSeleccionado() {
        return ficheroSeleccionado;
    }

    public void setFicheroSeleccionado(File ficheroSeleccionado) {
        this.ficheroSeleccionado = ficheroSeleccionado;
    }

    public String getContenidoCarpeta() {
        return contenidoCarpeta;
    }

    public void setContenidoCarpeta(String contenidoCarpeta) {
        this.contenidoCarpeta = contenidoCarpeta;
    }

    public void leerCSV(File file){
        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            dato.clear(); /*Limpia los datos existentes para asegurar que la lista está vacía*/

            String primeraLinea = br.readLine();
            if(primeraLinea == null){/*Comporobamos si la primera línea del archivo está vacía */
                System.out.println("El archivo CSV esta vacío");
                return;
            }

            String [] encabezado = primeraLinea.split(",");/*Con esto leemos la primera línea del archivo que suele ser siempre el encabezado y los separamos por comas */

            String linea;

            setCarpetaSeleccionada(file.getParentFile()); /*Carpeta del archivo */
            setFicheroSeleccionado(file);/*fichero seleccionado */

            File[] archivosEnCarpeta = getCarpetaSeleccionada().listFiles();
            if(archivosEnCarpeta != null){
                StringBuilder contenido = new StringBuilder();
                for(File archivo : archivosEnCarpeta){
                    contenido.append(archivo.getName()).append("\n");
                }
                setContenidoCarpeta(contenido.toString());
            }else{
                setContenidoCarpeta("La carpeta está vacía");
            }
        
            while((linea = br.readLine()) != null){/*Lee línea por línea el archivo*/
                String [] values = linea.split(",");/*La linea leída se divide en un array usando coma para cada valor, es decir, cuando hay un espacio se coloca una coma */

                Map<String, String> entry = new HashMap<>();/*Creamos un map vacío donde se almacenarán los datos de la línea leida */

                for (int i = 0; i < encabezado.length; i++){
                    entry.put(encabezado[i], values[i]);/*recorremos el array y vamos agregandolos al map */
                }
                dato.add(entry);
            }
            System.out.println("El archivo CSV ha sido leído correctamente");
        }catch (IOException e){
            e.printStackTrace();/*Muestra el error completo con la línea donde ocurrió */
        }
    }

    public static void escrituraCSV(String nomarchivo){
        try(FileWriter writer = new FileWriter(nomarchivo + ".csv")){

            if (!dato.isEmpty()){/*Verificamos si hay datos para escribir */
                Map<String, String> primerdato = dato.get(0);/*Obtenemos la clave que serán los encabezados */
                writer.write(String.join(",", primerdato.keySet()) + "\n");/*Escribirmos los encabezados en la primera línea */

                for(Map<String, String> entry : dato){/*Escribimos los datos fila por fila */
                StringBuilder fila = new StringBuilder();

                    for(String value : entry.values()){ /*Recorremos los valores de cada fila */
                        if(value == null){/*Si el valor es null, lo reemplazamos por una cadena vacía */
                            fila.append("").append(",");
                        }else{
                            fila.append(value).append(",");
                        }
                    }

                    if(fila.length() > 0){/*Eliminamos la última coma */
                        fila.setLength(fila.length() -1);
                    }

                    writer.write(fila.toString() + "\n");/*Escribimos la fila en el archivo */
                }
                    System.out.println("Archivo CSV exportado correctamente.");
                }
            } catch (IOException e) {
                e.printStackTrace();/*Muestra el error completo con la línea donde ocurrió */
        }
    }
}

