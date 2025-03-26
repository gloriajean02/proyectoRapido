import java.io.File;

public class App {
    public static void main(String[] args) {
        /*Crea una instancia de la clase CSV*/
        CSV csv = new CSV();

        
        File archivoCSV = new File("C:\\Users\\maria\\OneDrive\\Escritorio\\Desarrollo de Aplicaciones Web (DAW1ºA)\\Programación\\3TRIMESTRE\\proyecto\\proyectoRapido\\miniproyecto\\fichero\\coches.csv");  // Asegúrate de poner la ruta correcta del archivo CSV

        /*Llamada al método para leer el archivo CSV*/
        csv.leerCSV(archivoCSV);

        /*Muestra la información de la carpeta seleccionada*/
        System.out.println("Ruta de la carpeta seleccionada: " + csv.getCarpetaSeleccionada().getAbsolutePath());

        /*Muestra el contenido de la carpeta seleccionada*/
        System.out.println("Contenido de la carpeta seleccionada: ");
        System.out.println(csv.getContenidoCarpeta());

        /*Muestra el fichero seleccionado*/
        System.out.println("Fichero seleccionado: " + csv.getFicheroSeleccionado().getName());

    }
}

