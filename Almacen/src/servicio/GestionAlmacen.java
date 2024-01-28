package servicio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import dao.InventarioDao;
import modelo.Articulo;

/**
 * La clase GestionAlmacen se encarga de gestionar un almacén de artículos. 
 * Esta clase contiene métodos para cargar y guardar artículos en un archivo, 
 * exportarlos a un archivo CSV, y mostrar un menú para interactuar con el usuario.
 * Utiliza un ArrayList para almacenar los objetos Articulo.
 */
public class GestionAlmacen {
	
	/**
     * Constante que define el separador utilizado en los mensajes del menú.
     */
	static final String SEP = "----------------------------";

	public static void main(String[] args) {
		
		
		// Crear ArrayList para almacenar artículos
        ArrayList<Articulo> inventario = cargarArticulos();

        try (Scanner sc = new Scanner(System.in)) {
			int opcion;

			do {
			    mostrarMenu();
			    opcion = sc.nextInt();
			    switch (opcion) {
			        case 1:
			            // Añadir artículo
			        	InventarioDao.add(inventario, sc);
			            break;
			        case 2:
			            // Borrar artículo por id
			        	InventarioDao.delete(inventario, sc);
			            break;
			        case 3:
			            // Consultar artículo por id
			        	InventarioDao.get(inventario, sc);
			            break;
			        case 4:
			            // Listar todos los artículos
			        	InventarioDao.list(inventario);
			            break;
			        case 5:
			            // Exportar a CSV (Requerimiento 2)
			        	exportarACSV(inventario);
			            break;
			        case 6:
			            // Terminar programa y guardar en articulos.dat
			            guardarArticulos(inventario);
			            break;
			    }
			} while (opcion != 6);
		}
    }

	/**
     * Muestra el menú de opciones en la consola.
     */
    private static void mostrarMenu() {
    	System.out.println(SEP);
    	System.out.println("\nMenú de Gestión del Almacén:\n");
        System.out.println("1. Añadir nuevo artículo");
        System.out.println("2. Borrar artículo por ID");
        System.out.println("3. Consultar artículo por ID");
        System.out.println("4. Listar todos los artículos");
        System.out.println("5. Exportar artículos a archivo CSV");
        System.out.println("6. Terminar el programa");
        System.out.print("\nSelecciona una opción: ");
        System.out.println("\n\n" + SEP);
    }

    /**
     * Carga los artículos desde el archivo 'articulos.dat' si existe.
     * Si el archivo no existe, se inicializa un ArrayList vacío.
     *
     * @return ArrayList de Articulo que contiene los artículos cargados o vacío si el archivo no existe.
     */
    private static ArrayList<Articulo> cargarArticulos() {
    	ArrayList<Articulo> inventario = new ArrayList<>();
        File archivo = new File("articulos.dat");

        if (archivo.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
                // Leer el objeto ArrayList desde el archivo
                inventario = (ArrayList<Articulo>) ois.readObject();
            } catch (FileNotFoundException e) {
                System.out.println("Archivo no encontrado.");
            } catch (IOException e) {
                System.out.println("Error al leer el archivo.");
            } catch (ClassNotFoundException e) {
                System.out.println("Clase no encontrada.");
            }
        }

        return inventario;
    }

    /**
     * Guarda la lista actual de artículos en el archivo 'articulos.dat'.
     * Sobrescribe el archivo si ya existe.
     *
     * @param inventario ArrayList de Articulo que contiene los artículos a guardar.
     */
    private static void guardarArticulos(ArrayList<Articulo> inventario) {
    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("articulos.dat"))) {
            oos.writeObject(inventario);
            System.out.println("Artículos guardados en articulos.dat con éxito.");
        } catch (IOException e) {
            System.out.println("Error al guardar los artículos en el archivo.");
        }
    }
    
    /**
     * Exporta la lista actual de artículos a un archivo CSV 'articulos.csv'.
     * Formatea los datos de los artículos en el formato CSV.
     *
     * @param inventario ArrayList de Articulo que contiene los artículos a exportar.
     */
    private static void exportarACSV(ArrayList<Articulo> inventario) {
        try (FileWriter fw = new FileWriter("articulos.csv")) {
            fw.write("ID; Nombre; Descripción; Stock; Precio\n"); 

            for (Articulo art : inventario) {
                fw.write(art.getId() + ";" + art.getNombre() + ";" + 
                         art.getDescripcion() + ";" + art.getStock() + ";" + 
                         art.getPrecio() + "\n");
            }

            System.out.println("Artículos exportados a articulos.csv con éxito.");
        } catch (IOException e) {
            System.out.println("Ocurrió un error al exportar a CSV: " + e.getMessage());
        }
    }
    
    
}
