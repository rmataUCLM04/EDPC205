/*********************************************************************
*
* Nombre de la clase: FicheroSecuencial
* Nombre de los autores: RMM, HJG, PMS
* Fecha de creación:
* Versión de la clase:
* Descripción de la clase: Clase genérica que permite leer registros
* de un fichero de texto secuencial (por líneas), separando los campos
* mediante un separador indicado, y cargándolos en objetos que implementen
* la interfaz SequentialFileReader.
*
**********************************************************************/

import java.io.*;     // Librería para manejo de archivos y excepciones.
import java.util.*;   // Librería para utilidades como Scanner.

public class FicheroSecuencial <T extends SequentialFileReader> {
	
	private File file;        // Objeto File que representa el fichero físico.
	private Scanner scan;     // Scanner para recorrer el fichero línea a línea.
	private String separator; // Separador que se usará para dividir cada línea.

	/*********************************************************************
	*
	* Nombre del método: FicheroSecuencial
	*
	* Descripción del método:
	*   Constructor de la clase. Abre el fichero indicado y prepara el
	*   objeto Scanner para lectura secuencial.
	*
	* Argumentos de entrada:
	*   - String fileName: nombre o ruta del fichero a leer.
	*   - String separator: carácter separador usado entre campos.
	*
	* Valor de retorno:
	*   No devuelve ningún valor (constructor).
	*
	* Ficheros requeridos:
	*   El fichero indicado debe existir en el sistema de archivos.
	*
	* Excepciones comprobadas:
	*   - FileNotFoundException: si el fichero no se encuentra.
	*
	*********************************************************************/
	
	public FicheroSecuencial(String fileName, String separator) throws FileNotFoundException {
		this.file = new File(fileName); // Se crea el objeto File con el nombre recibido.
		this.scan = new Scanner(file);  // Se asocia el Scanner al fichero para leerlo.
		this.separator = separator;     // Se guarda el separador especificado.
	}
	
	

	/*********************************************************************
	*
	* Nombre del método: read
	*
	* Descripción del método:
	*   Lee una línea del fichero, la separa en campos usando el separador
	*   definido y pasa los datos al objeto Peticion (u otro que implemente
	*   SequentialFileReader) para que los procese.
	*
	* Argumentos de entrada:
	*   - Peticion objeto: instancia donde se cargarán los datos leídos.
	*
	* Valor de retorno:
	*   No devuelve valor (void).
	*
	* Ficheros requeridos:
	*   El fichero debe estar abierto mediante el Scanner.
	*
	* Excepciones comprobadas:
	*   - IOException: si ocurre algún problema de entrada/salida.
	*
	*********************************************************************/
	
	public void read(Peticion objeto) throws IOException {
		String[] data = scan.nextLine().split(separator); // Lee una línea y la divide por el separador.
		objeto.readData(data);                            // Envía los datos al método readData del objeto.
	}
    // Devuelve internamente un vector de cadenas String con los datos separados.

	
	/*********************************************************************
	*
	* Nombre del método: skip
	*
	* Descripción del método:
	*   Salta una línea del fichero sin procesarla. Útil si se quiere
	*   omitir cabeceras u otras líneas.
	*
	* Argumentos de entrada:
	*   Ninguno.
	*
	* Valor de retorno:
	*   No devuelve valor (void).
	*
	* Ficheros requeridos:
	*   El fichero debe estar abierto.
	*
	*********************************************************************/
	
	public void skip() {
		scan.nextLine(); // Lee y descarta la siguiente línea.
	}
	
	/*********************************************************************
	*
	* Nombre del método: close
	*
	* Descripción del método:
	*   Cierra el Scanner asociado al fichero para liberar los recursos.
	*
	* Argumentos de entrada:
	*   Ninguno.
	*
	* Valor de retorno:
	*   No devuelve valor (void).
	*
	* Ficheros requeridos:
	*   El fichero debe haber sido abierto anteriormente.
	*
	*********************************************************************/
	
	public void close() {
		scan.close(); // Cierra el Scanner (y el fichero).
	}
	
	/*********************************************************************
	*
	* Nombre del método: isEndOfFile
	*
	* Descripción del método:
	*   Comprueba si se ha alcanzado el final del fichero.
	*
	* Argumentos de entrada:
	*   Ninguno.
	*
	* Valor de retorno:
	*   boolean -> true si no quedan más líneas por leer, false en caso contrario.
	*
	* Ficheros requeridos:
	*   El fichero debe estar abierto mediante el Scanner.
	*
	*********************************************************************/
	
	public boolean isEndOfFile() {
		return !scan.hasNextLine(); // Devuelve true si no hay más líneas disponibles.
	}
}
