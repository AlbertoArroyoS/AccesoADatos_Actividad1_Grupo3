package fichero;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

public class LecturaListaArticulos {

    public static final String nombreFichero = "articulos.dat";

    public static List<Articulo> leerArticulos() {
        List<Articulo> listaArticulos = new ArrayList<>();

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreFichero))) {
            listaArticulos = (List<Articulo>) ois.readObject();
            System.out.println("Existe fichero : " + nombreFichero);
            System.out.println("Artículos leídos de " + nombreFichero);
        } catch (FileNotFoundException e) {
            System.out.println("El archivo " + nombreFichero + " no existe. Se creará uno nuevo.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return listaArticulos;
    }
}