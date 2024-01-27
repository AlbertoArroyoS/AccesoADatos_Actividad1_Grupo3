package negocio;

import java.util.ArrayList;

import entidad.Articulo;
import persistencia.DaoArticulo;

/**
 * La clase GestorAlmacen actúa como una capa intermedia entre la presentación
 * de la aplicación y el acceso a los datos.
 */
public class GestorAlmacen {

	private DaoArticulo daoArticulo; // Objeto para acceder a los datos de los artículos

	/**
	 * Constructor de la clase GestorAlmacen. Inicializa el objeto DaoArticulo y
	 * crea el archivo de datos si no existe.
	 */
	public GestorAlmacen() {
		this.daoArticulo = new DaoArticulo();
		daoArticulo.crearFile();
	}

	/**
	 * Agrega un nuevo artículo.
	 * 
	 * @param articulo El artículo a agregar.
	 * @return true si se agrega con éxito, false si el artículo ya existe.
	 */
	public boolean agregarArticulo(Articulo articulo) {
		return daoArticulo.addArticulo(articulo);
	}

	/**
	 * Borra un artículo por su ID.
	 * 
	 * @param id El ID del artículo a borrar.
	 */
	public void borrarArticulo(int id) {
		daoArticulo.borrarArticulo(id);
	}

	/**
	 * Consulta un artículo por su ID.
	 * 
	 * @param id El ID del artículo a consultar.
	 * @return El artículo encontrado, o null si no se encuentra.
	 */
	public Articulo consultarArticulo(int id) {
		return daoArticulo.consultarArticulo(id);
	}

	/**
	 * Obtiene una lista de todos los artículos.
	 * 
	 * @return La lista de artículos.
	 */
	public ArrayList<Articulo> listarArticulos() {
		return daoArticulo.listarArticulos();
	}

	/**
	 * Exporta la lista de artículos a un archivo CSV.
	 * 
	 * @param listaArticulos La lista de artículos a exportar.
	 */
	public void exportarCSV(ArrayList<Articulo> listaArticulos) {
		daoArticulo.exportarCSV(listaArticulos);
	}

	/**
	 * Escribe los artículos en el archivo de datos.
	 */
	public void escribirEnArchivo() {
		daoArticulo.escribirEnArchivo();
	}
}