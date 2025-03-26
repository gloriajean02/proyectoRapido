public class App {
    public static void main(String[] args) throws Exception {
        XML xml = new XML();
        GestorDatos gestor = new GestorDatos();

        xml.escribirFichero(gestor);
        System.out.println(gestor.toString());
    }
}
