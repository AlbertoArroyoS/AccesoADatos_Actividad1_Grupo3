package dao;

import java.util.ArrayList;
import java.util.Scanner;

import modelo.Articulo;

/**
 * La clase InventarioDao proporciona métodos para gestionar las operaciones 
 * sobre una colección de artículos, como añadir, eliminar, consultar y listar artículos.
 */
public class InventarioDao {

	/**
     * Añade un nuevo artículo al inventario. Solicita al usuario los detalles del artículo 
     * y verifica que no exista un artículo con el mismo ID antes de añadirlo al inventario.
     *
     * @param inventario La colección de artículos a la que se añadirá el nuevo artículo.
     * @param sc. El Scanner para leer la entrada del usuario.
     */
	public static void add( ArrayList<Articulo> inventario, Scanner sc) {
		
			// Solicitar datos del artículo
			System.out.print("Introduce el ID: ");
			int id = sc.nextInt();
			sc.nextLine(); // Limpiar buffer del scanner

			// Verificar si el ID ya existe
			boolean existe = false;
			for (Articulo art : inventario) {
			    if (art.getId() == id) {
			        existe = true;
			    }
			}

			if (existe) {
			    System.out.println("Error: Ya existe un artículo con ese ID.\n");
			    return;
			}

			System.out.print("Introduce el nombre: ");
			String nombre = sc.nextLine();

			System.out.print("Introduce la descripción: ");
			String descripcion = sc.nextLine();

			System.out.print("Introduce el stock: ");
			int stock = sc.nextInt();

			System.out.print("Introduce el precio: ");
			double precio = sc.nextDouble();

			// Crear y añadir el artículo
			Articulo nuevoArticulo = new Articulo(id, nombre, descripcion, stock, precio);
			inventario.add(nuevoArticulo);
			System.out.println("Artículo añadido con éxito.\n");
	    
	}
	
	/**
     * Elimina un artículo del inventario basándose en el ID proporcionado por el usuario.
     *
     * @param inventario La colección de artículos de la que se eliminará el artículo.
     * @param sc. El Scanner para leer la entrada del usuario.
     */
	public static void delete(ArrayList<Articulo> inventario, Scanner sc) {
		
        System.out.print("Introduce el ID del artículo a borrar: ");
        int id = sc.nextInt();

        Articulo articuloABorrar = null;
        for (Articulo art : inventario) {
            if (art.getId() == id) {
                articuloABorrar = art;
                break;
            }
        }

        if (articuloABorrar != null) {
            inventario.remove(articuloABorrar);
            System.out.println("Artículo eliminado con éxito.\n");
        } else {
            System.out.println("No se encontró un artículo con ese ID.\n");
        }
    }

	/**
     * Muestra los detalles de un artículo específico basándose en el ID proporcionado por el usuario.
     *
     * @param inventario. La colección de artículos en la que se buscará el artículo.
     * @param sc. El Scanner para leer la entrada del usuario.
     */
	public static void get(ArrayList<Articulo> inventario, Scanner sc) {
	        System.out.print("Introduce el ID del artículo a consultar: ");
	        int id = sc.nextInt();

	        for (Articulo art : inventario) {
	            if (art.getId() == id) {
	                System.out.println("Artículo encontrado: " + art + "\n");
	                return;
	            }
	        }
	        System.out.println("No se encontró un artículo con ese ID.\n");
	    }
	
	/**
     * Lista todos los artículos en el inventario.
     *
     * @param inventario La colección de artículos a listar.
     */
	public static void list(ArrayList<Articulo> inventario) {
        if (inventario.isEmpty()) {
            System.out.println("No hay artículos en el almacén.\n");
            return;
        }

        System.out.println("Listado de todos los artículos:\n");
        for (Articulo art : inventario) {
            System.out.println(art + "\n");
        }
    }
	
	
}
