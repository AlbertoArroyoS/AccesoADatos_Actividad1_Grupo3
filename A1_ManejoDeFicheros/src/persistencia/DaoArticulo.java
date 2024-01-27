package persistencia;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import entidad.Articulo;

/**
 * La clase DaoArticulo proporciona métodos para gestionar una lista de
 * artículos y realizar operaciones como agregar, eliminar, consultar, listar,
 * exportar a CSV, escribir y cargar datos desde un archivo.
 */
public class DaoArticulo {
	private static final String FICHERO = "articulos.dat";
	private static final String CSV_FILE = "articulos.csv";
	private ArrayList<Articulo> articulos;

	/**
	 * Constructor de la clase DaoArticulo. Inicializa la lista de artículos y carga
	 * los datos desde el archivo si existen.
	 */
	public DaoArticulo() {
		this.articulos = new ArrayList<>();
		cargarDatos(); // Cargar datos del archivo si existieran
	}

	/**
	 * Crea un nuevo archivo si no existe.
	 */
	public void crearFile() {
		try {
			File file = new File(FICHERO);

			if (!file.exists()) {
				if (file.createNewFile()) {
					System.out.println("Archivo creado con éxito.");
				} else {
					System.out.println("Error al crear el archivo.");
				}
			} else {
				System.out.println("El archivo ya existe. Cargado con éxito.");
			}
		} catch (IOException e) {
			System.err.println("Error al crear el archivo: " + e.getMessage());
		}
	}

	/**
	 * Agrega un nuevo artículo a la lista.
	 * 
	 * @param articulo El artículo a agregar.
	 * @return true si se agrega con éxito, false si el artículo ya existe.
	 */
	public boolean addArticulo(Articulo articulo) {
		if (!existeArticulo(articulo.getId())) {
			articulos.add(articulo);
			System.out.println("El artículo con el ID " + articulo.getId() + " se ha añadido correctamente.");
			return true;
		} else {
			System.out.println("El artículo con el ID " + articulo.getId() + " ya existe.");
			return false;
		}
	}

	/**
	 * Elimina un artículo de la lista por su ID.
	 * 
	 * @param id El ID del artículo a borrar.
	 */
	public void borrarArticulo(int id) {
		Articulo idBorrar = null;
		for (Articulo aux : articulos) {
			if (aux.getId() == id) {
				idBorrar = aux;
				break;
			}
		}

		if (idBorrar != null) {
			articulos.remove(idBorrar);
			System.out.println("Artículo con ID " + id + " borrado correctamente.");
		} else {
			System.out.println("No se encontró ningún artículo con ID " + id + ".");
		}
	}

	/**
	 * Consulta un artículo por su ID.
	 * 
	 * @param id El ID del artículo a consultar.
	 * @return El artículo encontrado o null si no se encuentra.
	 */
	public Articulo consultarArticulo(int id) {
		for (Articulo articulo : articulos) {
			if (articulo.getId() == id) {
				return articulo;
			}
		}
		return null;
	}

	/**
	 * Obtiene la lista de todos los artículos.
	 * 
	 * @return La lista de artículos.
	 */
	public ArrayList<Articulo> listarArticulos() {
		return articulos;
	}

	/**
	 * Exporta la lista de artículos a un archivo CSV.
	 * 
	 * @param listaArticulos La lista de artículos a exportar.
	 */
	public void exportarCSV(ArrayList<Articulo> listaArticulos) {
		try (FileWriter writer = new FileWriter(CSV_FILE)) {

			writer.append("ID;Nombre;Descripcion;Stock;Precio\n");

			for (Articulo articulo : listaArticulos) {
				writer.append(String.valueOf(articulo.getId()));
				writer.append(";");
				writer.append(articulo.getNombre());
				writer.append(";");
				writer.append(articulo.getDescripcion());
				writer.append(";");
				writer.append(String.valueOf(articulo.getStock()));
				writer.append(";");
				writer.append(String.valueOf(articulo.getPrecio()));
				writer.append("\n");
			}

			System.out.println("Los artículos se han exportado al archivo " + CSV_FILE);
		} catch (IOException e) {
			System.out.println("Error al exportar los artículos al archivo CSV: " + e.getMessage());
		}
	}

	/**
	 * Carga los datos desde el archivo de datos.
	 */
	private void cargarDatos() {
		try (ObjectInputStream objectInput = new ObjectInputStream(new FileInputStream(FICHERO))) {
			// Intenta cargar los datos desde el archivo
			while (true) {
				try {
					Articulo articulo = (Articulo) objectInput.readObject();
					articulos.add(articulo);
				} catch (EOFException e) {
					// Se alcanzó el final del archivo, se sale del bucle
					break;
				}
			}
		} catch (FileNotFoundException e) {
			System.out.println("El archivo " + FICHERO + " no existe.");
			// Aquí puedes decidir si quieres crear el archivo o manejarlo de otra manera
		} catch (IOException | ClassNotFoundException e) {
		}
	}

	/**
	 * Escribe los artículos en el archivo de datos.
	 */
	public void escribirEnArchivo() {
		try (ObjectOutputStream objectOutput = new ObjectOutputStream(new FileOutputStream(FICHERO))) {
			for (Articulo articulo : articulos) {
				objectOutput.writeObject(articulo);
			}
			System.out.println("Los artículos se han guardado en el archivo " + FICHERO);
		} catch (IOException e) {
			System.out.println("Error al escribir en el archivo: " + e.getMessage());
		}
	}

	/**
	 * Verifica si un artículo existe en la lista por su ID.
	 * 
	 * @param id El ID del artículo a verificar.
	 * @return true si el artículo existe, false en caso contrario.
	 */
	private boolean existeArticulo(int id) {
		for (Articulo articulo : articulos) {
			if (articulo.getId() == id) {
				return true;
			}
		}
		return false;
	}
}
