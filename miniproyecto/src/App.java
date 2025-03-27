import java.io.File;
import java.util.Scanner;


public class App {

    public static Scanner sc = new Scanner(System.in);
    public static GestorDatos gestor = new GestorDatos();

    public static void main(String[] args) {

        int opcion = 0;
        do {

            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║       BIENVENIDO A ILOVECONVERSOR       ║");
            System.out.println("║═════════════════════════════════════════║");

            menuPrincipal();

            System.out.println("¿Qué desea realizar?");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("Introduce la ruta de la carpeta: ");
                    String ruta = sc.nextLine();
                    File carpeta = new File(ruta);
                    if (comprobarCarpeta(carpeta) != null) {
                        System.out.println("Carpeta selecciona con éxito");
                        System.out.println("Ruta seleccionada: "+carpeta.getAbsolutePath());
                        System.out.println("Contenido de la carpeta seleccionada: " + contenidoCarpeta(carpeta));
                        menu2(carpeta);
                    }else{
                        System.out.println("Carpeta no encontrada");
                    }
                    break;
                case 2:
                    System.out.println("Saliendo del programa");
                    break;
                default: System.out.println("Valor incorrecto");
                    break;
            }

        } while (opcion != 2);
        

        
    //     /*Crea una instancia de la clase CSV*/
    //     CSV csv = new CSV();

        
    //     File archivoCSV = new File("C:\\Users\\maria\\OneDrive\\Escritorio\\Desarrollo de Aplicaciones Web (DAW1ºA)\\Programación\\3TRIMESTRE\\proyecto\\proyectoRapido\\miniproyecto\\fichero\\coches.csv");  // Asegúrate de poner la ruta correcta del archivo CSV

    //     /*Llamada al método para leer el archivo CSV*/
    //     csv.leerCSV(archivoCSV);

    //     /*Muestra la información de la carpeta seleccionada*/
    //     System.out.println("Ruta de la carpeta seleccionada: " + csv.getCarpetaSeleccionada().getAbsolutePath());

    //     /*Muestra el contenido de la carpeta seleccionada*/
    //     System.out.println("Contenido de la carpeta seleccionada: ");
    //     System.out.println(csv.getContenidoCarpeta());

    //     /*Muestra el fichero seleccionado*/
    //     System.out.println("Fichero seleccionado: " + csv.getFicheroSeleccionado().getName());

        
    
    // System.out.println("Ingresa la ruta del archivo: ");
    // String ruta = sc.nextLine();
    // /*Crea una instancia de la clase CSV*/
    // CSV csv = new CSV (ruta);

    // GestorDatos gestor = new GestorDatos();

    //     if (csv.comprobarExiste()) {
    //     System.out.println("El archivo existe.");
    //     System.out.println();
    //     System.out.println("Ruta de la carpeta seleccionada: " + csv.getCarpetaSeleccionada());
    //     System.out.println();
    //     System.out.println("Contenido de la carpeta seleccionada:\n" + getContenidoCarpeta());
    //     System.out.println("Fichero seleccionado: " + csv.getFichero().getName());
    //     System.out.println();
        
    //     if (!csv.comprobarFicheroVacio()) {
    //         csv.escribirFichero(gestor);
    //         System.out.println("Datos leídos del CSV:");
    //         System.out.println(gestor);
    //     } else {
    //         System.out.println("El archivo está vacío.");
    //     }
    // } else {
    //     System.out.println("El archivo no existe.");
    // }
    }

    public static void menuPrincipal(){
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║              MENÚ PRINCIPAL             ║");
        System.out.println("║═════════════════════════════════════════║");
        System.out.println("║ 1 - Seleccionar carpeta                 ║");
        System.out.println("║ 2 - Salir                               ║");
        System.out.println("║═════════════════════════════════════════║");
    }

    public static void menu2(File carpetaseleccionada){
    
        int opcion = 0;
        do {

            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║              MENÚ PRINCIPAL             ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║ 1 - Lectura de fichero                  ║");
            System.out.println("║ 2 - Salir                               ║");
            System.out.println("║═════════════════════════════════════════║");

            System.out.println("¿Qué desea realizar?");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                System.out.println("Contenido de la carpeta seleccionada: " + contenidoCarpeta(carpetaseleccionada));
                System.out.println("Qué archivo quieres seleccionar?");
                String nombrefichero = sc.nextLine();
                String ruta = carpetaseleccionada.getAbsolutePath() + "/" + nombrefichero;
                File fichero = new File(ruta);
                if(fichero.exists()){
                    System.out.println("Fichero correcto");
                    if(comprobarExtension(fichero).equals(".csv")){
                        CSV csv = new CSV (ruta);
                        csv.escribirFichero(gestor);
                    }else if(comprobarExtension(fichero).equals(".json")){
                        Json json = new Json (ruta);
                        json.escribirFichero(gestor);
                    }else if(comprobarExtension(fichero).equals(".xml")){
                        xml XML = new xml (ruta);
                        XML.escribirFichero(gestor);
                    }else{
                        System.out.println("Error");
                    }
                }else{
                    System.out.println("El fichero no ha sido encontrado");
                }
                    
                    break;
                case 2:
                    System.out.println("Saliendo del programa");
                    break;
                default: System.out.println("Valor incorrecto");
                    break;
            }

        } while (opcion != 0);
    }


    public static void menu3(){
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║              MENÚ PRINCIPAL             ║");
        System.out.println("║═════════════════════════════════════════║");
        System.out.println("║ 1 - Convertir fichero                   ║");
        System.out.println("║ 2 - Leer otro fichero                   ║");
        System.out.println("║ 3 - Salir                               ║");
        System.out.println("║═════════════════════════════════════════║");
    }

    public static void menu4(){
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║              MENÚ PRINCIPAL             ║");
        System.out.println("║═════════════════════════════════════════║");
        System.out.println("║ 1 - .CSV                                ║");
        System.out.println("║ 2 - .JSON                               ║");
        System.out.println("║ 3 - .XML                                ║");
        System.out.println("║ 4 - Salir                               ║");
        System.out.println("║═════════════════════════════════════════║");
    }

    public static File comprobarCarpeta(File carpeta){
        if (carpeta.exists() && carpeta.isDirectory()) {
            return carpeta;
        } else return null;
    }

    public static String contenidoCarpeta(File carpeta) {
        if (comprobarCarpeta(carpeta) != null && carpeta.isDirectory()) {
            StringBuilder contenido = new StringBuilder();
            File[] archivos = carpeta.listFiles();
            if (archivos != null) {
                for (File archivo : archivos) {
                    contenido.append(archivo.getName()).append("\n");
                }
            }
            return contenido.toString();
        }
        return "Carpeta vacía o no válida";
    }

    public static String comprobarExtension(File fichero){
        
        if (fichero.getName().endsWith(".csv")) {
            return ".csv";
        }else if(fichero.getName().endsWith(".json")){
            return ".json";
        }else if(fichero.getName().endsWith(".xml")){
            return ".xml";
        }else{
            return "Tipo de archivo no válido";
        }
        
    }

}
