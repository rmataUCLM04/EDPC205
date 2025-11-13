package Vancouver;

/*********************************************************************
*
* Class Name: FicheroSecuencial
* Author/s name: RMM, HJG, PMS 
* Release/Creation date: 
* Class version: 1.0
* Class description:
*   Clase genérica para la lectura secuencial de ficheros de texto
*   Permite leer línea a línea, dividir los campos mediante un 
*   separador y delegar la carga de datos a objetos que implementen 
*   la interfaz SequentialFileReader.
*
**********************************************************************/

import java.io.*;
import java.util.*;

public class FicheroSecuencial<T extends SequentialFileReader> {

    private File file;       // Referencia al fichero a leer
    private Scanner scan;    // Scanner utilizado para leer el contenido del fichero
    private String separator; // Separador de campos (por ejemplo, ";")

    /*********************************************************************
    *
    * Method name: FicheroSecuencial
    *
    * Description of the Method:
    *   Constructor de la clase. Abre el fichero indicado y prepara un
    *   Scanner para su lectura secuencial.
    *
    * Calling arguments:
    *   nombreFile (String) Nombre o ruta del fichero a abrir.
    *   separator (String) Carácter o cadena que separa los campos.
    *
    * Return value: none
    *
    * Required Files:
    *   Requiere el fichero indicado por 'nombreFile' existente en el sistema.
    *
    * Checked Exceptions:
    *   FileNotFoundException: si el fichero especificado no existe o
    *   no puede abrirse para lectura.
    *
    *********************************************************************/
    public FicheroSecuencial(String nombreFile, String separator) throws FileNotFoundException {
        this.file = new File(nombreFile);
        this.scan = new Scanner(file);
        this.separator = separator;
    }

	/*********************************************************************
	 *
	 * Method name: read
	 *
	 * Description of the Method: Lee una línea del fichero, la separa en campos
	 * usando el separador especificado, y pasa dichos datos al método readData del
	 * objeto recibido, que será responsable de interpretar y almacenar la
	 * información.
	 *
	 * Calling arguments: objeto (T) Objeto del tipo que implementa
	 * SequentialFileReader, sobre el que se cargan los datos leídos.
	 *
	 * Return value: none
	 *
	 * Required Files: El fichero debe estar abierto mediante el Scanner interno.
	 *
	 * Checked Exceptions: IOException: si se produce un error al leer la línea.
	 * NoSuchElementException: si se intenta leer más allá del final del fichero.
	 *
	 *********************************************************************/
    public void read(T objeto) throws IOException {
        String[] data = scan.nextLine().split(separator); // Divide la línea en campos
        objeto.readData(data); // Carga los datos en el objeto correspondiente
    }

    /*********************************************************************
    *
    * Method name: skip
    *
    * Description of the Method:
    *   Avanza el puntero del Scanner una línea en el fichero, sin procesar
    *   su contenido. Se usa para saltar cabeceras o líneas no deseadas.
    *
    * Calling arguments: none
    *
    * Return value: none
    *
    * Required Files:
    *   El fichero debe estar abierto y contener al menos una línea adicional.
    *
    * Checked Exceptions:
    *   NoSuchElementException: si se intenta saltar más allá del final del fichero.
    *
    *********************************************************************/
    public void skip() {
        scan.nextLine();
    }

    /*********************************************************************
    *
    * Method name: close
    *
    * Description of the Method:
    *   Cierra el Scanner asociado al fichero, liberando los recursos del sistema.
    *
    * Calling arguments: none
    *
    * Return value: none
    *
    * Required Files:
    *   El fichero debe haber sido abierto previamente por la clase.
    *
    * Checked Exceptions: none
    *
    *********************************************************************/
    public void close() {
        scan.close();
    }

    /*********************************************************************
    *
    * Method name: isEndOfFile
    *
    * Description of the Method:
    *   Indica si se ha alcanzado el final del fichero.
    *   Devuelve true si no quedan más líneas por leer.
    *
    * Calling arguments: none
    *
    * Return value:
    *   boolean → true si no quedan líneas, false en caso contrario.
    *
    * Required Files:
    *   El fichero debe estar abierto mediante el Scanner.
    *
    * Checked Exceptions: none
    *
    *********************************************************************/
    public boolean isEndOfFile() {
        return !scan.hasNextLine();
    }
}