
public class App {
    public static void main(String[] args) {
        /*Crea una instancia de la clase CSV*/
        CSV csv = new CSV ("C:\\Users\\maria\\OneDrive\\Escritorio\\Desarrollo de Aplicaciones Web (DAW1ºA)\\Programación\\3TRIMESTRE\\proyecto\\proyectoRapido\\miniproyecto\\fichero\\coches.csv");
        GestorDatos gestor = new GestorDatos();

        if (csv.comprobarExiste()) {
            System.out.println("El archivo existe.");
            System.out.println("Ruta de la carpeta seleccionada: " + csv.getCarpetaSeleccionada());
            System.out.println("Contenido de la carpeta seleccionada:\n" + csv.getContenidoCarpeta());
            System.out.println("Fichero seleccionado: " + csv.getFichero().getName());
            
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

