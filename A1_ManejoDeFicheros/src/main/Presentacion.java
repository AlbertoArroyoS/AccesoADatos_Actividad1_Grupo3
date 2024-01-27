package main;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entidad.Articulo;
import negocio.GestorAlmacen;

/**
 * La clase Presentacion proporciona un menú de consola para interactuar con un
 * sistema de gestión de almacén. Permite al usuario realizar operaciones como
 * agregar, borrar, consultar, listar y exportar artículos.
 */
public class Presentacion {

	private static GestorAlmacen gestor;

	/**
	 * Método principal que inicia la aplicación. Crea una instancia de
	 * GestorAlmacen y muestra el menú de opciones.
	 */
	public static void main(String[] args) {
		gestor = new GestorAlmacen();
		mostrarMenu();
	}

	/**
	 * Muestra el menú de opciones y gestiona la interacción del usuario.
	 */
	public static void mostrarMenu() {
		Scanner scanner = new Scanner(System.in);

		int opcion;
		do {
			mostrarOpciones();
			opcion = obtenerOpcion(scanner);

			switch (opcion) {
			case 1:
				agregarArticulo(scanner);
				break;
			case 2:
				borrarArticulo(scanner);
				break;
			case 3:
				consultarArticulo(scanner);
				break;
			case 4:
				listarArticulos();
				break;
			case 5:
				exportarCSV();
				break;
			case 6:
				System.out.println("Programa finalizado");
				gestor.escribirEnArchivo();
				break;
			default:
				System.out.println("Opción no válida. Por favor, elija nuevamente.");
				break;
			}
		} while (opcion != 6);

		scanner.close();
	}

	/**
	 * Muestra las opciones del menú.
	 */
	public static void mostrarOpciones() {
		System.out.println("MENU:");
		System.out.println("1. Añadir nuevo artículo");
		System.out.println("2. Borrar artículo por ID");
		System.out.println("3. Consultar artículo por ID");
		System.out.println("4. Listado de todos los artículos");
		System.out.println("5. Exportar a formato CSV");
		System.out.println("6. Terminar el programa");
		System.out.println("Elija una opción:");
	}

	/**
	 * Obtiene la opción seleccionada por el usuario.
	 * 
	 * @param scanner Scanner para la entrada de la opcion.
	 * @return La opción seleccionada.
	 */
	public static int obtenerOpcion(Scanner scanner) {
		int opcion = 0;
		try {
			opcion = scanner.nextInt();
		} catch (InputMismatchException e) {
			System.out.println("Por favor, ingrese un número entero.");
			scanner.next(); // Limpiar el buffer del scanner
		}
		return opcion;
	}

	/**
	 * Agrega un nuevo artículo al sistema.
	 * 
	 * @param scanner Scanner para la entrada del articulo.
	 */
	public static void agregarArticulo(Scanner scanner) {
		System.out.println("Añadir nuevo artículo");
		System.out.println("Ingrese el ID del artículo:");
		int id = scanner.nextInt();
		scanner.nextLine(); // Limpiar el buffer
		System.out.println("Ingrese el nombre del artículo:");
		String nombre = scanner.nextLine();
		System.out.println("Ingrese la descripción del artículo:");
		String descripcion = scanner.nextLine();
		System.out.println("Ingrese el stock del artículo:");
		int stock = scanner.nextInt();
		System.out.println("Ingrese el precio del artículo:");
		double precio = scanner.nextDouble();
		Articulo articulo = new Articulo(id, nombre, descripcion, stock, precio);
		gestor.agregarArticulo(articulo);
	}

	/**
	 * Borra un artículo existente por su ID.
	 * 
	 * @param scanner Scanner para la entrada del articulo.
	 */
	public static void borrarArticulo(Scanner scanner) {
		System.out.println("Introduce el ID para borrar el artículo");
		gestor.borrarArticulo(scanner.nextInt());
	}

	/**
	 * Consulta un artículo existente por su ID.
	 * 
	 * @param scanner Scanner para la entrada del articulo.
	 */
	public static void consultarArticulo(Scanner scanner) {
		System.out.println("Introduce el ID para consultar el artículo");
		int idConsulta = scanner.nextInt();
		Articulo articuloConsultado = gestor.consultarArticulo(idConsulta);
		if (articuloConsultado != null) {
			System.out.println("Artículo encontrado:");
			System.out.println("ID: " + articuloConsultado.getId());
			System.out.println("Nombre: " + articuloConsultado.getNombre());
			System.out.println("Descripción: " + articuloConsultado.getDescripcion());
			System.out.println("Stock: " + articuloConsultado.getStock());
			System.out.println("Precio: " + articuloConsultado.getPrecio());
		} else {
			System.out.println("El artículo con ID " + idConsulta + " no se encontró.");
		}
	}

	/**
	 * Lista todos los artículos disponibles.
	 */
	public static void listarArticulos() {
		ArrayList<Articulo> articulos = gestor.listarArticulos();
		if (articulos.isEmpty()) {
			System.out.println("No hay artículos disponibles.");
		} else {
			System.out.println("Listado de todos los artículos:");
			for (Articulo aux : articulos) {
				System.out.println("ID: " + aux.getId());
				System.out.println("Nombre: " + aux.getNombre());
				System.out.println("Descripción: " + aux.getDescripcion());
				System.out.println("Stock: " + aux.getStock());
				System.out.println("Precio: " + aux.getPrecio());
				System.out.println("--------------------------");
			}
		}
	}

	/**
	 * Exporta la lista de artículos a un archivo CSV.
	 */
	public static void exportarCSV() {
		System.out.println("Exportar a formato CSV");
		gestor.exportarCSV(gestor.listarArticulos());
	}
}
