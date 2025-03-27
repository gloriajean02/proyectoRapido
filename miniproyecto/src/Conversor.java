import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class Conversor {

    File fichero;
    String rutaArchivo;
    GestorDatos gestor;

    // Constructor que inicializa con valores predeterminados
    public Conversor(GestorDatos gestor) {
        fichero = new File("miniproyecto/fichero/coches.json"); // Ruta del archivo de entrada
        rutaArchivo = "miniproyecto/fichero/coches.csv"; // Ruta del archivo de salida
        this.gestor = gestor; // Gestor con los datos que se van a convertir
    }

    // Constructor que permite personalizar la ruta del archivo
    public Conversor(String ruta, String rutaArchivo, GestorDatos gestor) {
        this.fichero = new File(ruta); // Ruta del archivo de entrada
        this.rutaArchivo = rutaArchivo; // Ruta del archivo de salida
        this.gestor = gestor; // Gestor con los datos que se van a convertir
    }

    // Método que convierte los datos del GestorDatos a un archivo CSV
    public void conversorCSV() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(rutaArchivo))) {

            if (gestor != null && gestor.getElementos().size() > 0) {/*Comprobamos que el gestor no esté vacío */
                HashMap<String, String> primerElemento = gestor.getElementos().get(0);/*Obtenemos el primer elemento del gestor que hay en HashMap */
                
                List<String> claves = List.copyOf(primerElemento.keySet());/*Usamos el método keyset() para obtener todas las claves, las pasamos a una lista para poer acceder por índice */
                for (int i = 0; i < claves.size(); i++) {
                    bw.write(claves.get(i));/*Escribe el valor de la calve en la posición i de la lista claves */
                    if (i < claves.size() - 1) {/*Si no es la última clave de la lista */
                        bw.write(","); /*Agregar coma entre las claves*/
                    }
                }
                bw.newLine();

            
                List<HashMap<String, String>> elementos = gestor.getElementos();/*Extraemos todos los elementos del gestior que son HashMap<String, String> */
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
