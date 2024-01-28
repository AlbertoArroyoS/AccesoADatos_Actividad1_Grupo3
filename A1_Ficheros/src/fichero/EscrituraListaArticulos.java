package fichero;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

public class EscrituraListaArticulos {

    public static final String nombreFichero = "articulos.dat";

    public static void escribirArticulos(List<Articulo> listaArticulos) {
    
		try (FileOutputStream fos = new FileOutputStream(nombreFichero);
   			 ObjectOutputStream oos = new ObjectOutputStream(fos)) {
   			
   			oos.writeObject(listaArticulos);
   			System.out.println("Objeto introducido");
   		} catch (IOException e) {
   			
   			e.printStackTrace();
   		} 
   		
   		System.out.println("Cerrando programa");
   	}

   
  }
    

	


