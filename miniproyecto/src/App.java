import java.io.File;
import java.util.Scanner;

/**
 * @author Gloria Curado García
 * @author Guadalupe Morcillo Guijarro
 * @author María Teresa Calvo Peña
 */

public class App {

    public static Scanner sc = new Scanner(System.in);
    public static GestorDatos gestor = new GestorDatos();

    //COLORES
    /** Código ANSI para restablecer el color de la consola. */
    public static final String RESET = "\u001B[0m";
    /** Código ANSI para texto en rojo. */
    public static final String RED = "\u001B[31m";
    /** Código ANSI para texto en verde. */
    public static final String GREEN = "\u001B[32m";
    /** Código ANSI para texto en amarillo. */
    public static final String BLUE = "\u001B[34m";
    /** Código ANSI para texto en violeta. */
    public static final String PURPLE = "\u001B[35m";
    /** Código ANSI para texto en cian. */


    public static void main(String[] args) {

        int opcion = 0;
        do {
            System.out.println(bienvenida());
            System.out.println(menuPrincipal());
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                File carpeta; 
                do {
                    System.out.print("\nIntroduce la ruta de la carpeta: ");
                    String ruta = sc.nextLine();
                    carpeta = new File(ruta);
                    if (carpeta.exists() && carpeta.isDirectory()) {
                        if (carpeta.listFiles() == null || carpeta.listFiles().length == 0) {
                            System.out.println(RED+"\nLa carpeta está vacía\n\n"+RESET);
                        } else {
                            System.out.println(GREEN+"\nCarpeta seleccionada con éxito\n"+RESET);
                            System.out.println(BLUE+"\nRuta seleccionada: \n\n"+RESET + carpeta.getAbsolutePath());
                            menu2(carpeta);
                        }
                    } else {
                        System.out.println(RED+"\nCarpeta no encontrada o no válida\n"+RESET);
                    }
                } while (carpeta == null || !carpeta.isDirectory() || carpeta.listFiles() == null || carpeta.listFiles().length == 0);
                    break;
                case 0:
                    System.out.println(BLUE+"\nSaliendo del programa\n"+RESET);
                    break;
                default: System.out.println(RED+"\nValor incorrecto\n\n"+RESET);
                    break;
            }

        } while (opcion != 0);
    }

    public static String bienvenida(){
        return "\n"
              +PURPLE+"                     ╔═════════════════════════════════════════════════╗\n"
              +PURPLE+"                     ║"+BLUE+"           BIENVENIDO A ILOVECONVERSOR           "+PURPLE+"║\n"
              +PURPLE+"                     ║"+BLUE+"                       ---                       "+PURPLE+"║\n"
              +PURPLE+"                     ║"+RESET+"    La app definitiva para convertir archivos    "+PURPLE+"║\n"
              +PURPLE+"                     ╚═════════════════════════════════════════════════╝\n"+RESET;
    }
    
    public static String menuPrincipal(){
        return PURPLE+"╔════════════════════════════════════════════════════════════════════════════════════════╗\n"
              +PURPLE+"║"+BLUE+"            Para empezar, selecciona la carpeta donde se encuentra tu archivo           "+PURPLE+"║\n"
              +PURPLE+"║════════════════════════════════════════════════════════════════════════════════════════║\n"
              +PURPLE+"║"+RESET+"           [1] Seleccionar carpeta                                                      "+PURPLE+"║\n"
              +PURPLE+"║"+RESET+"           [0] Salir                                                                    "+PURPLE+"║\n"
              +PURPLE+"╚════════════════════════════════════════════════════════════════════════════════════════╝\n"+RESET;
    }

    public static String menuFormatos(){
        return PURPLE+"╔═════════════════════════════════════════╗\n"+
                "║"+BLUE+"      Elige el formato de conversión     "+PURPLE+"║\n"+
                "║═════════════════════════════════════════║\n"+
                "║"+RESET+" 1 - .CSV                                "+PURPLE+"║\n"+
                "║"+RESET+" 2 - .JSON                               "+PURPLE+"║\n"+
                "║"+RESET+" 3 - .XML                                "+PURPLE+"║\n"+
                "║"+RESET+" 0 - Salir                               "+PURPLE+"║\n"+
                "╚═════════════════════════════════════════╝\n"+RESET;
    }

    public static void menu2(File carpetaseleccionada){
    
        int opcion = 0;
    
            System.out.println(BLUE+"\nContenido de la carpeta seleccionada: \n\n"+RESET + contenidoCarpeta(carpetaseleccionada));
            System.out.println(GREEN+"Pulse [1] para convertir uno de estos ficheros.\n"+PURPLE+"Pulse [0] para salir.\n"+RESET);
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: 
                File fichero;
                do{
                    System.out.println(BLUE+"\n¿Qué archivo quieres seleccionar?\n"+RESET);
                    String nombrefichero = sc.nextLine();
                    String ruta = carpetaseleccionada.getAbsolutePath() + "/" + nombrefichero;
                    fichero = new File(ruta);
                    if (!fichero.exists() || comprobarExtension(fichero).equals("\nTipo de archivo no válido\n\n")) {
                        System.out.println(RED+"\nError: No se ha seleccionado un archivo válido para convertir.\n\n"+RESET);
                    }else{
                    lecturaFichero(fichero, ruta);
                    menu3(carpetaseleccionada, fichero);
                  }
                }while (!fichero.exists() || comprobarExtension(fichero).equals("\nTipo de archivo no válido\n\n"));
                    break;
            
                case 0:
                    System.out.println(GREEN+"\nVolviendo al menú anterior...\n\n"+RESET);
                    break;

                default:
                    System.out.println(RED+"\nValor incorrecto\n\n"+RESET);
                    menu2(carpetaseleccionada);
                    break;
            }

     
    }


    public static void menu3(File carpetaseleccionada, File fichero){

        int opcion = 0;
        
            
            System.out.println(BLUE+"Fichero seleccionado: "+RESET+fichero.getName());
            System.out.println(BLUE+"\nPulse [1] para convertir el fichero a otro formato.\n"+BLUE+"Pulse [2] para leer otro archivo."+PURPLE+"\nPulse [0] para salir.\n"+RESET);
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1: 
                    
                    System.out.println("\n");
                    menu4(fichero);
                    
                break;

                case 2:
                    menu2(carpetaseleccionada);
                return;
            
                case 0:
                    System.out.println(GREEN+"\nSaliendo...\n\n"+RESET);
                break;

                default:
                    System.out.println(RED+"\nValor incorrecto\n\n"+RESET);
                    menu3(carpetaseleccionada, fichero);
                break;
            }

        


    }

    public static void menu4(File fichero) {

        int opcion = 0;
        
            System.out.println(menuFormatos());
            opcion = Integer.parseInt(sc.nextLine());
    
            switch (opcion) {
                case 1:
                    if (!fichero.getName().endsWith(".csv")) {
                        convertirFichero(fichero, ".csv");
                    } else {
                        System.out.println(RED+"\nError: El archivo ya es un CSV, no se puede convertir\n\n"+RESET);
                    }
                    menu4(fichero);
                    break;
                
                case 2:
                    if (!fichero.getName().endsWith(".json")) {
                        convertirFichero(fichero, ".json");
                    } else {
                        System.out.println(RED+"\nError: El archivo ya es un JSON, no se puede convertir\n\n"+RESET);
                    }
                    menu4(fichero);
                
                    break;

                case 3:    
                
                    if (!fichero.getName().endsWith(".xml")) {
                        convertirFichero(fichero, ".xml");
                    } else {
                        System.out.println(RED+"\nError: El archivo ya es un XML, no se puede convertir\n\n"+RESET);
                    }
                    menu4(fichero);
                    break;
    
                case 0:
                    System.out.println(PURPLE+"\nVolviendo al menú anterior\n\n"+RESET);
                    break;
    
                default:
                    System.out.println(RED+"\nValor incorrecto\n\n"+RESET);
                    menu4(fichero);
                    break;
            }
        
    }
    
    private static void convertirFichero(File fichero, String extension) {
        System.out.println(BLUE+"\n¿En qué ruta quieres alojar el archivo?\n"+RESET);
        String ruta = sc.nextLine();
        System.out.println(BLUE+"\n¿Qué nombre quieres ponerle al archivo? Escríbelo "+PURPLE+"SIN la extensión\n"+RESET);
        String nombreArchivo = sc.nextLine();
        String rutaCompleta = ruta + "/" + nombreArchivo + extension;
        File rutaDestino = new File(ruta);
 
        if (rutaDestino.exists()) {
            if (extension.equals(".xml")) {
                System.out.println(BLUE+"\n¿Cómo te gustaría llamar al elemento raíz del xml?"+RESET+" Este abrirá y cerrará tu XML\n");
                String elementoRaiz = sc.nextLine();
                System.out.println(BLUE+"\n¿Cómo te gustaría llamar a los elementos?"+RESET+" Este abrirá y cerrará cada elemento del XML\n");
                String elementoItem = sc.nextLine();
                Conversor conversor = new Conversor(rutaCompleta, gestor, elementoRaiz, elementoItem);
                conversor.conversorXML();
            } else {
                Conversor conversor = new Conversor(rutaCompleta, gestor);
                if (extension.equals(".csv")) {
                    conversor.conversorCSV();
                } else if (extension.equals(".json")) {
                    conversor.conversorJSON();
                }
            }
            System.out.println(BLUE+"\nArchivo convertido a " + extension.toUpperCase() + " con éxito. Puedes encontrarlo en "+PURPLE+rutaCompleta+"\n\n"+RESET);
        } else {
            System.out.println(RED+"\nError: La ruta especificada no es válida.\n\n"+RESET);
        }
        
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
        } else return RED+"\nCarpeta no válida o vacía\n\n"+RESET;
    }

    public static String comprobarExtension(File fichero){
        
        if (fichero.getName().endsWith(".csv")) {
            return ".csv";
        }else if(fichero.getName().endsWith(".json")){
            return ".json";
        }else if(fichero.getName().endsWith(".xml")){
            return ".xml";
        }else{
            return RED+"\nTipo de archivo no válido\n\n"+RESET;
        }
        
    }

    public static void lecturaFichero(File fichero, String ruta){
        gestor.getGestor().clear();
        
        if(fichero.exists()){                   
            if(comprobarExtension(fichero).equals(".csv")){
                CSV csv = new CSV (ruta);
                csv.escribirFichero(gestor);
                System.out.println(GREEN+"\nFichero correcto\n\n"+RESET);
            }else if(comprobarExtension(fichero).equals(".json")){
                Json json = new Json (ruta);
                json.escribirFichero(gestor);
                System.out.println(GREEN+"\nFichero correcto\n\n"+RESET);
            }else if(comprobarExtension(fichero).equals(".xml")){
                xml XML = new xml (ruta);
                XML.escribirFichero(gestor);
                System.out.println(GREEN+"\nFichero correcto\n\n"+RESET);
            }else{
                System.out.println(RED+"\nError\n\n"+RESET);
            }
        }else{
            System.out.println(RED+"\nEl fichero no ha sido encontrado\n\n"+RESET);
        }
    }

}
