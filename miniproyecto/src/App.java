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
            System.out.println("╚═════════════════════════════════════════╝");

            menuPrincipal();
            

            System.out.println( "\n¿Qué desea realizar?\n");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("\nIntroduce la ruta de la carpeta: ");
                    String ruta = sc.nextLine();
                    File carpeta = new File(ruta);
                    if (carpeta.exists() && carpeta.isDirectory()) {
                        if (carpeta.listFiles() == null || carpeta.listFiles().length == 0) {
                            System.out.println("\nLa carpeta está vacía\n\n");
                        } else {
                            System.out.println("\nCarpeta seleccionada con éxito\n");
                            System.out.println("\nRuta seleccionada: \n\n" + carpeta.getAbsolutePath());
                            System.out.println("\n\nContenido de la carpeta seleccionada: \n\n" + contenidoCarpeta(carpeta) + "\n");
                            menu2(carpeta);
                        }
                    } else {
                        System.out.println("\nCarpeta no encontrada o no válida\n");
                    }
                    break;
                case 0:
                    System.out.println("\nSaliendo del programa\n");
                    break;
                default: System.out.println("\nValor incorrecto\n\n");
                    break;
            }

        } while (opcion != 0);
    }

    public static void menuPrincipal(){
        System.out.println("╔═════════════════════════════════════════╗");
        System.out.println("║             ¿QUÉ DESEA HACER?           ║");
        System.out.println("║═════════════════════════════════════════║");
        System.out.println("║ 1 - Seleccionar carpeta                 ║");
        System.out.println("║ 0 - Salir                               ║");
        System.out.println("╚═════════════════════════════════════════╝");
    }

    public static void menu2(File carpetaseleccionada){
    
        int opcion = 0;
        do {

            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║             ¿QUÉ DESEA HACER?           ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║ 1 - Lectura de fichero                  ║");
            System.out.println("║ 0 - Salir                               ║");
            System.out.println("╚═════════════════════════════════════════╝");

            System.out.println("\n¿Qué desea realizar?\n");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: 
                    System.out.println("\nContenido de la carpeta seleccionada: \n\n" + contenidoCarpeta(carpetaseleccionada));
                    System.out.println("\nQué archivo quieres seleccionar?\n");
                    String nombrefichero = sc.nextLine();
                    String ruta = carpetaseleccionada.getAbsolutePath() + "/" + nombrefichero;
                    File fichero = new File(ruta);
                    if (!fichero.exists() || comprobarExtension(fichero).equals("\nTipo de archivo no válido\n\n")) {
                        System.out.println("\nError: No se ha seleccionado un archivo válido para convertir.\n\n");
                    }else{
                    lecturaFichero(fichero, ruta);
                    menu3(carpetaseleccionada, fichero);
                }
                break;
            
                case 0:
                    System.out.println("\nVolviendo al menú anterior\n\n");
                break;

                default:
                    System.out.println("\nValor incorrecto\n\n");
                break;
            }

        } while (opcion != 0);
    }


    public static void menu3(File carpetaseleccionada, File fichero){

        int opcion = 0;
        do {
            
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║             ¿QUÉ DESEA HACER?           ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║ 1 - Convertir fichero                   ║");
            System.out.println("║ 2 - Leer otro fichero                   ║");
            System.out.println("║ 0 - Salir                               ║");
            System.out.println("╚═════════════════════════════════════════╝");

            System.out.println("\n¿Qué desea realizar?\n");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1: 
                    
                    System.out.println("\n");
                    menu4(fichero);
                    
                break;

                case 2:
                    System.out.println("\nContenido de la carpeta seleccionada: \n\n" + contenidoCarpeta(carpetaseleccionada));
                    System.out.println("\nQué archivo quieres seleccionar?\n");
                    String nombrefichero = sc.nextLine();
                    String ruta = carpetaseleccionada.getAbsolutePath() + "/" + nombrefichero;
                    File nuevoFichero = new File(ruta);
                    if (!fichero.exists() || comprobarExtension(fichero).equals("\nTipo de archivo no válido\n\n")) {
                        System.out.println("\nError: No se ha seleccionado un archivo válido para convertir.\n\n");
                    }else{
                    lecturaFichero(nuevoFichero, ruta);
                    menu3(carpetaseleccionada, nuevoFichero);
                }
                break;
            
                case 0:
                    System.out.println("\nVolviendo al menú anterior\n\n");
                break;

                default:
                    System.out.println("\nValor incorrecto\n\n");
                break;
            }

        } while (opcion != 0);


    }

    public static void menu4(File fichero){

        int opcion = 0;
        do {
            
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║             ¿QUÉ DESEA HACER?           ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║ 1 - .CSV                                ║");
            System.out.println("║ 2 - .JSON                               ║");
            System.out.println("║ 3 - .XML                                ║");
            System.out.println("║ 0 - Salir                               ║");
            System.out.println("╚═════════════════════════════════════════╝");

            System.out.println("\n¿Qué desea realizar?\n");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

            case 1:
            if(!fichero.getName().endsWith(".csv")){
                System.out.println("\n¿En qué ruta quieres alojar el archivo?\n");
                String ruta=sc.nextLine();
                System.out.println("\n¿Qué nombre quieres ponerle al archivo? Escríbelo SIN la extensión\n");
                String nombreArchivo = sc.nextLine();
                String rutaCompleta = ruta+"/"+nombreArchivo+".csv";
                File rutaDestino = new File(ruta);
                if(rutaDestino.exists()){
                Conversor conversor = new Conversor(rutaCompleta, gestor);
                conversor.conversorCSV();
                System.out.println("\nArchivo convertido a CSV con éxito.\n\n");
                } else {
                    System.out.println("\nError: La ruta especificada no es válida.\n\n");
                }
            }else{
                System.out.println("\nError: El archivo ya es un CSV, no se puede convertir\n\n");
            }               
            break;

            case 2:
            if(!fichero.getName().endsWith(".json")){
                System.out.println("\n¿En qué ruta quieres alojar el archivo?\n");
                String ruta=sc.nextLine();
                System.out.println("\n¿Qué nombre quieres ponerle al archivo? Escríbelo SIN la extensión\n");
                String nombreArchivo = sc.nextLine();
                String rutaCompleta = ruta+"/"+nombreArchivo+".json";
                File rutaDestino = new File(ruta);
                if(rutaDestino.exists()){
                Conversor conversor = new Conversor(rutaCompleta, gestor);
                conversor.conversorJSON();
                System.out.println("\nArchivo convertido a Json con éxito.\n\n");
                } else {
                    System.out.println("\nError: La ruta especificada no es válida.\n\n");
                }

            }else{
                System.out.println("\nError: El archivo ya es un Json, no se puede convertir\n\n");
            } 
            break;

            case 3:
            if(!fichero.getName().endsWith(".xml")){
                System.out.println("\n¿En qué ruta quieres alojar el archivo?\n");
                String ruta = sc.nextLine();
                System.out.println("\n¿Qué nombre quieres ponerle al archivo? Escríbelo SIN la extensión\n");
                String nombreArchivo = sc.nextLine();
                String rutaCompleta = ruta+"/"+nombreArchivo+".xml";
                File rutaDestino = new File(ruta);

                if(rutaDestino.exists()){
                    System.out.println("\n\n¿Cómo te gustaría llamar al elemento raíz?\n");
                    String elementoRaiz = sc.nextLine();
                    System.out.println("\n¿Cómo te gustaría llamar a los elementos?\n");
                    String elementoItem = sc.nextLine();
                    Conversor conversor = new Conversor(rutaCompleta, gestor, elementoRaiz, elementoItem);
                    conversor.conversorXML();
                    System.out.println("\nArchivo convertido a XML con éxito.\n\n");
                }else {
                    System.out.println("\nError: La ruta especificada no es válida.\n\n");
                }   
            } else {
                System.out.println("\nError: El archivo ya es un XML, no se puede convertir\n\n");
            }
            break;
            case 0:
                System.out.println("\nVolviendo al menú anterior\n\n");
                break;
                default:
                System.out.println("\nValor incorrecto\n\n");
                break;
            }

        } while (opcion != 0);

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
        return "\nCarpeta no válida o vacía\n\n";
    }

    public static String comprobarExtension(File fichero){
        
        if (fichero.getName().endsWith(".csv")) {
            return ".csv";
        }else if(fichero.getName().endsWith(".json")){
            return ".json";
        }else if(fichero.getName().endsWith(".xml")){
            return ".xml";
        }else{
            return "\nTipo de archivo no válido\n\n";
        }
        
    }

    public static void lecturaFichero(File fichero, String ruta){
        if(fichero.exists()){                   
            if(comprobarExtension(fichero).equals(".csv")){
                CSV csv = new CSV (ruta);
                csv.escribirFichero(gestor);
                System.out.println("\nFichero correcto\n\n");
            }else if(comprobarExtension(fichero).equals(".json")){
                Json json = new Json (ruta);
                json.escribirFichero(gestor);
                System.out.println("\nFichero correcto\n\n");
            }else if(comprobarExtension(fichero).equals(".xml")){
                xml XML = new xml (ruta);
                XML.escribirFichero(gestor);
                System.out.println("\nFichero correcto\n\n");
            }else{
                System.out.println("\nError\n\n");
            }
        }else{
            System.out.println("\nEl fichero no ha sido encontrado\n\n");
        }
    }

}
