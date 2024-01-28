package fichero;

import java.io.Serializable;

public class Articulo implements Serializable{
	
	private static final long serialVersionUID = -930902834467400478L;
	
	//Definimos los atributos de la clase	
	private int id, uds;
	private String nombre, descripcion;
	private double precio;
	
	//Constructor de la clase con los atributos
	public Articulo(int id, int uds, String nombre, String descripcion, double precio) {
		super();
		this.id = id;
		this.uds = uds;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
	}
	public Articulo() {
		super();
	}
	
	//Getters y Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUds() {
		return uds;
	}
	public void setUds(int uds) {
		this.uds = uds;
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
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	//Generamos método toString
	@Override
	public String toString() {
		return "Articulo [id=" + id + ", uds=" + uds + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", precio=" + precio + "]";
	}
	
	
	

}
