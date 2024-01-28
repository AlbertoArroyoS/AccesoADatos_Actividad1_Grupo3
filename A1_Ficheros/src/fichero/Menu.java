package fichero;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;



public class Menu {	
	//Declaramos a nivel de clase un ArrayList para guardar los objetos de tipo articulo, así 
	//como la clase Scanner para la lectura de entrada de datos.	
	 static List<Articulo> listaArticulos =new ArrayList<Articulo>();
	 static Scanner leer = new Scanner (System.in);
	 
	 //Creamos un menú con las distintas opciones	 
	 public static void main(String[] args) {		 
		// Al iniciar el programa, intentamos cargar la lista de artículos desde el archivo,si este existe.
		 LecturaListaArticulos leer = new LecturaListaArticulos();
	     listaArticulos = leer.leerArticulos();
	     System.out.println(listaArticulos);

			int opcion = 0;
			do {
				opcion = menu();

				switch (opcion) { 

				case 1:
					altaArticulo();
					break;

				case 2:
					borrarArticulo();
					break;

				case 3:
					 buscarArticulo();
						System.out.println();
					break;

				case 4:
					listarTodos();
					break;	
					
				case 5:
					exportar();
					break;				
				}

			} while (opcion != 6);
			
			// Al salir del programa, guardamos la lista de artículos en el fichero
			  EscrituraListaArticulos escritor = new EscrituraListaArticulos();
		        escritor.escribirArticulos(listaArticulos);
			System.out.println("Fin Menú");
		}
		
		

		// Metodos propios del menú
	 
	 		//Método que recorre el Array e imprime por consola la lista de articulos
		
			private static void listarTodos() {
		
				for(Articulo art: listaArticulos) {
					System.out.println(art);
				}
		
	}
			/*
			 * Método que permite al usuario obtener la información de un articulo que existe en la lista
			 * si el id introducido existe.Si no existe el id introducido por el usuario 
			 * devuelve un mensaje de error.
			 */
	
			private static void buscarArticulo() {
			System.out.println("Introduce el id del artículo que desees buscar");
				int id=leer.nextInt();				
				boolean existe =false;
				
				for(Articulo art: listaArticulos) {
					
					if (art.getId()== id) {
						System.out.println(art);
						existe=true;
						break;
					}else if(!existe) {
						System.out.println("No existe ningún articulo con el id : " + id);
					}
				}
				
				
	}
			/*
			 * Método bara dar de baja un artículo existente de la lista, el usuario introducirá un id,
			 * el método buscará en la lista si existe el id introducido por el usuario en cuyo caso dará
			 * de baja el objeto de la lista, y sino existe le saldrá un mensaje de error.
			 * 
			 */
			
			private static void borrarArticulo() {
				System.out.println("Introduce el id del articulo que desees borrar de la lista");
				int id = leer.nextInt();
				for(int i =0; i< listaArticulos.size(); i++) {
					if( listaArticulos.get(i).getId()== id) {
						listaArticulos.remove(i);
						System.out.println("Artículo eliminado de la lista");
					}else {
						System.out.println("No esxiste ningún artículo con ese id");
					}
						
				}
		
	}

			/* Método para dar de alta un objeto nuevo de tipo artículo en la lista, primero
			 * comprobará si el id que introduce el usuario existe o no. Si no existe, le seguirá 
			 * pidiendo la aplicación todos los datos necesarios para dar de alta el objeto.
			 * Si ya existe el id introducido, le saldrá al cliente un mensaje de error. 
			 * 
			 */
		
			private static void altaArticulo() {		
						
				System.out.println("Introduce el id del artículo");
				int id = leer.nextInt();
				
				//Comprobamos antes de seguir si existe el id introducido o no, llamando al método existeArticulo 
				// que recibe por parámetro el identificador introducido por el usuario.
				
				while(existeArticulo(id)) {
					System.out.println("Existe un articulo con ese ID, introduce otro id distinto");
					id =leer.nextInt();				
					
				}
				
				System.out.println("Introduce el número de unidades");
				int uds = leer.nextInt();	
				System.out.println("Introduce el nombre del artículo");
				String nombre = leer.nextLine();
				leer.nextLine(); 
				System.out.println("Introduce una descripción");
				String descripcion =leer.nextLine();				
				System.out.println("Introduce el precio unitario");
				double precio = leer.nextDouble();
				
				//Creamos un objeto de tipo Articulo con los datos introducidos y lo añadimos a la lista
				Articulo art = new Articulo(id, uds,nombre, descripcion,precio);
				listaArticulos.add(art);
				System.out.println("Articulo añadido correctamente a la lista");
				
	}	
			
			
		/*Método para comprobar que dos artículos no pueden tener el mismo id.El método recibe por parámetro 
		 * un id introducido por el usuario y lo compara con los ids que hay en la lista.
		*/	
			public static boolean existeArticulo(int id) {
				for (Articulo art : listaArticulos) {
					if(art.getId() == id) {
						return true;
					}
				}
				return false;
			}
			
		//Método que se encargar de exportar los datos de la listaArticulos en un archivo .csv
		//Para ellos llamaremos desde la clase FicheroCSV al método exportarCSV(), dicho método recibe
		//por parámetro el array listaArticulos y el nombre del fichero con extensión .csv	
			
			private static void exportar() {
				
				FicheroCSV.exportarCSV(listaArticulos, "articulos.csv");
				System.out.println("Exportacion de datos ejecutada correctamente");
				
			}
			

		// Menú, diseño de la interfaz usuario.
		
		public static int menu() {

			int opcion;

			System.out.println("");
			System.out.println("***** MENU *****");
			System.out.println("----------------");
			System.out.println("1. Añadir nuevo artículo");
			System.out.println("2. Borrar articulo por id");
			System.out.println("3. Consultar artículo por id");
			System.out.println("4. Listado de todos los artículos");
			System.out.println("5. Exportar artículos a archivo CSV");
			System.out.println("6. Terminar programa");
			System.out.println("Introduce una opción");
			opcion = leer.nextInt();

			while (opcion < 1 || opcion > 6) {
				System.out.println("Recuerda, del 1 al 6");
				opcion = leer.nextInt();
			}

			return opcion;

		}

		
}
