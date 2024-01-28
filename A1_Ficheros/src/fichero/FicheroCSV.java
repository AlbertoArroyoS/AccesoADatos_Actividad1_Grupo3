package fichero;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.opencsv.CSVWriter;

public class FicheroCSV {
	
	public static void exportarCSV(List<Articulo> listaArticulos, String nombreArchivo) {
		
		if (listaArticulos.isEmpty()) {
			System.out.println("El archivo a exportar está vacío");
			return;
		}
		
		try (CSVWriter csvWriter = new CSVWriter(new FileWriter(nombreArchivo))) {
			// Escribir encabezados al CSV
			csvWriter.writeNext(new String[]{"ID", "Unidades", "Nombre", "Descripción", "Precio"});
			
			// Recorremos con un bucle FOR la información que tenemos en la listaArticulos 
			// y escribimos dicha información en el archivo  nuevo con extensión csv.El archivo .csv 
			//guarda la información en cadena, separado por comas.
			for (Articulo art : listaArticulos) {
				csvWriter.writeNext(new String[]{
						String.valueOf(art.getId()),
						String.valueOf(art.getUds()),
						art.getNombre(),
						art.getDescripcion(),
						String.valueOf(art.getPrecio())
				});
			}
			
			System.out.println("Artículos exportados correctamente a " + nombreArchivo);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
