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
            System.out.println("");

            System.out.println( "¿Qué desea realizar?\n");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.println("\nIntroduce la ruta de la carpeta: \n");
                    String ruta = sc.nextLine();
                    File carpeta = new File(ruta);
                    if (comprobarCarpeta(carpeta) != null) {
                        System.out.println("Carpeta selecciona con éxito\n");
                        System.out.println("\nRuta seleccionada: "+carpeta.getAbsolutePath());
                        System.out.println("Contenido de la carpeta seleccionada: \n\n" + contenidoCarpeta(carpeta));
                        menu2(carpeta);
                    }else{
                        System.out.println("Carpeta no encontrada");
                    }
                    break;
                case 0:
                    System.out.println("Saliendo del programa");
                    break;
                default: System.out.println("Valor incorrecto");
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
        System.out.println("║═════════════════════════════════════════║");
    }

    public static void menu2(File carpetaseleccionada){
    
        int opcion = 0;
        do {

            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║             ¿QUÉ DESEA HACER?           ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║ 1 - Lectura de fichero                  ║");
            System.out.println("║ 0 - Salir                               ║");
            System.out.println("║═════════════════════════════════════════║");

            System.out.println("¿Qué desea realizar?");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1: 
                    lecturaFichero(carpetaseleccionada);
                    menu3(carpetaseleccionada);
                break;
            
                case 0:
                    System.out.println("Volviendo al menú anterior");
                break;

                default:
                    System.out.println("Valor incorrecto");
                break;
            }

        } while (opcion != 0);
    }


    public static void menu3(File carpetaseleccionada){

        int opcion = 0;
        do {
            
            System.out.println("╔═════════════════════════════════════════╗");
            System.out.println("║             ¿QUÉ DESEA HACER?           ║");
            System.out.println("║═════════════════════════════════════════║");
            System.out.println("║ 1 - Convertir fichero                   ║");
            System.out.println("║ 2 - Leer otro fichero                   ║");
            System.out.println("║ 0 - Salir                               ║");
            System.out.println("║═════════════════════════════════════════║");

            System.out.println("¿Qué desea realizar?");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

                case 1: 
                    menu4(fichero);
                break;

                case 2: 
                    lecturaFichero(carpetaseleccionada);
                    menu3(carpetaseleccionada);
                break;
            
                case 0:
                    System.out.println("Volviendo al menú anterior");
                break;

                default:
                    System.out.println("Valor incorrecto");
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
            System.out.println("║═════════════════════════════════════════║");

            System.out.println("¿Qué desea realizar?");
            opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {

            case 1:
            if(!fichero.getName().endsWith(".csv")){
                System.out.println("¿En qué ruta quieres alojar el archivo?");
                String ruta=sc.nextLine();
                File archivoDestino = new File(ruta);
                if(archivoDestino.exists()){
                Conversor conversor = new Conversor(ruta, gestor);
                conversor.conversorCSV();
                }
                }else{
                    System.out.println("Error: El archivo ya es un CSV, no se puede convertir");
                }               
                break;

            case 2:
            if(!fichero.getName().endsWith(".json")){
                System.out.println("¿En qué ruta quieres alojar el archivo?");
                String ruta=sc.nextLine();
                File archivoDestino = new File(ruta);
                if(archivoDestino.exists()){
                Conversor conversor = new Conversor(ruta, gestor);
                conversor.conversorJSON();
                }
            }else{
                System.out.println("Error: El archivo ya es un Json, no se puede convertir");
            } 
            break;

            case 3:
            if(!fichero.getName().endsWith(".xml")){
                System.out.println("¿En qué ruta quieres alojar el archivo?");
                String ruta = sc.nextLine();
                File archivoDestino = new File(ruta);
                
                if(archivoDestino.exists()){
                    System.out.println("¿Cómo te gustaría llamar al elemento raíz?");
                    String elementoRaiz = sc.nextLine();
                    System.out.println("¿Cómo te gustaría llamar a los elementos?");
                    String elementoItem = sc.nextLine();
                    Conversor conversor = new Conversor(ruta, gestor, elementoRaiz, elementoItem);
                    conversor.conversorXML();
                }
            } else {
                System.out.println("Error: El archivo ya es un XML, no se puede convertir");
            }

            case 0:
                System.out.println("Volviendo al menú anterior");
                break;
                default:
                System.out.println("Valor incorrecto");
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

    public static void lecturaFichero(File carpetaseleccionada){

        System.out.println("\nContenido de la carpeta seleccionada: " + contenidoCarpeta(carpetaseleccionada));
        System.out.println("Qué archivo quieres seleccionar?");
        String nombrefichero = sc.nextLine();
        String ruta = carpetaseleccionada.getAbsolutePath() + "/" + nombrefichero;
        File fichero = new File(ruta);
        if(fichero.exists()){
                    
            if(comprobarExtension(fichero).equals(".csv")){
                CSV csv = new CSV (ruta);
                csv.escribirFichero(gestor);
                System.out.println("Fichero correcto");
            }else if(comprobarExtension(fichero).equals(".json")){
                Json json = new Json (ruta);
                json.escribirFichero(gestor);
                System.out.println("Fichero correcto");
            }else if(comprobarExtension(fichero).equals(".xml")){
                xml XML = new xml (ruta);
                XML.escribirFichero(gestor);
                System.out.println("Fichero correcto");
            }else{
                System.out.println("Error");
            }
        }else{
            System.out.println("El fichero no ha sido encontrado");
        }

    }


}
