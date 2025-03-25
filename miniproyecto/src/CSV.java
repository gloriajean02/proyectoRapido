import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;


public class CSV {
    private final List<Map<String, String>> dato = new ArrayList<>();

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
    
}
