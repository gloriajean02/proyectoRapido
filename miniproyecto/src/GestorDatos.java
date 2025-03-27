import java.util.ArrayList;
import java.util.HashMap;

public class GestorDatos{
    private ArrayList<HashMap<String,String>> gestor;

    /**
     * Constructor de GestorDatos
     */
    public GestorDatos() {
        gestor = new ArrayList<HashMap<String,String>>();
      
    }

    /**
     * Insertar un elemento dentro de ArrayList
     * @param elemento tipo HashMap<String, String>
     */
    public void insertarElemento(HashMap<String, String> elemento) {
        gestor.add(elemento);
    }

    public ArrayList<HashMap<String, String>> getGestor() {
        return gestor;
    }

    @Override
    public String toString() {
    String cadenaString = "";
    
        if (gestor.isEmpty()) {
            return "El gestor está vacío.";
        }

        for (HashMap<String, String> elemento : gestor) {
            cadenaString += elemento + "\n";
        }

        return cadenaString;

    }

    public int size() {
       return gestor.size();
    }

    public ArrayList<HashMap<String, String>> getGestor() {
        return gestor;
    }

}
   

