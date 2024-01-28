package modelo;

import java.io.Serializable;

/**
 * La clase Articulo representa un artículo en un almacén.
 * Incluye información como ID, nombre, descripción, stock y precio.
 * Implementa Serializable para permitir su almacenamiento en archivos.
 */
public class Articulo implements Serializable{
    
	private static final long serialVersionUID = 1L;
	
	private int id;
    private String nombre;
    private String descripcion;
    private int stock;
    private double precio;

    /**
     * Constructor con parámetros.
     *
     * @param id. El identificador único del artículo.
     * @param nombre. El nombre del artículo.
     * @param descripcion. Una descripción detallada del artículo.
     * @param stock. La cantidad disponible del artículo.
     * @param precio. El precio del artículo.
     */
    public Articulo(int id, String nombre, String descripcion, int stock, double precio) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    // Método toString modificado para representar el artículo como una cadena de texto
    @Override
    public String toString() {
        return "Artículo con " +
                "ID: " + id +
                ", nombre: '" + nombre + '\'' +
                ", descripción: '" + descripcion + '\'' +
                ", stock: " + stock +
                ", precio: " + precio +
                '€';
    }
}

