
import java.util.Scanner;

public class App {
    public static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
       
        System.out.println("Ingresa la ruta del archivo: ");
        String ruta = sc.nextLine();
        /*Crea una instancia de la clase CSV*/
        CSV csv = new CSV (ruta);
        GestorDatos gestor = new GestorDatos();

        if (csv.comprobarExiste()) {
            System.out.println("El archivo existe.");
            System.out.println();
            System.out.println("Ruta de la carpeta seleccionada: " + csv.getCarpetaSeleccionada());
            System.out.println();
            System.out.println("Contenido de la carpeta seleccionada:\n" + csv.getContenidoCarpeta());
            System.out.println("Fichero seleccionado: " + csv.getFichero().getName());
            System.out.println();
            
            if (!csv.comprobarFicheroVacio()) {
                csv.escribirFichero(gestor);
                System.out.println("Datos leídos del CSV:");
                System.out.println(gestor);
            } else {
                System.out.println("El archivo está vacío.");
            }
        } else {
            System.out.println("El archivo no existe.");
        }
    }
}

