/*********************************************************************
*
* Nombre de la clase: FicheroSecuencial
* Iniciales autores / Grupo prácticas: RMM, HJC, PMS / G05
* Fecha de creación: 
* Versión de la clase: 
* Descripción de la clase:
*   Clase genérica para leer ficheros de forma secuencial. Envuelve la lógica de apertura del fichero,
*   lectura línea a línea, conversión de cada línea en un objeto de tipo T,
*   y cierre del recurso.
*
*   T debe implementar la interfaz SequentialFileReader para poder
*   rellenarse a sí mismo a partir de los campos de texto leídos.
*
**********************************************************************/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.NoSuchElementException;

public class FicheroSecuencial<T extends SequentialFileReader> {

    private Scanner scan;     // Scanner asociado al fichero de entrada
    private String separador; // Separador de campos, por ejemplo ";"

    /*********************************************************************
    *
    * Nombre del método: FicheroSecuencial
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Constructor. Inicializa el lector secuencial abriendo el fichero
    *   indicado y dejando preparado el Scanner para leer línea a línea.
    *
    * Argumentos de entrada:
    *   String nombreFichero -> ruta o nombre del fichero a leer.
    *   String separador     -> separador de campos (ej. ";").
    *
    * Valor de retorno:
    *   (no aplica, es un constructor)
    *
    * Ficheros requeridos:
    *   Debe existir el fichero indicado por 'nombreFichero'.
    *   Este constructor abre el fichero.
    *
    * Excepciones comprobadas:
    *   FileNotFoundException -> si el fichero no existe o no se puede abrir.
    *
    *********************************************************************/
    public FicheroSecuencial(String nombreFichero, String separador) throws FileNotFoundException {
        this.scan = new Scanner(new File(nombreFichero));
        this.separador = separador;
    }

    /*********************************************************************
    *
    * Nombre del método: leerRegistro
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Lee la siguiente línea del fichero, la separa usando el separador,
    *   y rellena un objeto de tipo T con esos datos llamando a readData.
    *
    * Argumentos de entrada:
    *   T objeto -> instancia ya creada de la clase T que queremos rellenar.
    *               Por ejemplo, new Peticion() antes de llamar.
    *
    * Valor de retorno:
    *   T -> el mismo objeto recibido, pero ahora con sus campos cargados.
    *
    * Ficheros requeridos:
    *   El fichero debe estar abierto (Scanner inicializado).
    *
    * Excepciones comprobadas:
    *   NoSuchElementException -> si no hay más líneas que leer.
    *   IllegalStateException  -> si el Scanner ya está cerrado.
    *   NumberFormatException  -> propagada desde objeto.readData(...) si
    *                              algún campo numérico del CSV es inválido.
    *
    *********************************************************************/
    public T leerRegistro(T objeto) {
        // líneaActual: línea completa leída del fichero
        String lineaActual = scan.nextLine(); // Puede lanzar NoSuchElementException
        // campos: array con cada valor separado por el delimitador
        String[] campos = lineaActual.split(separador);
        // Delegamos en el propio objeto la interpretación de campos
        objeto.readData(campos);
        return objeto;
    }

    /*********************************************************************
    *
    * Nombre del método: isEndOfFile
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Indica si ya no quedan más líneas por leer en el fichero secuencial.
    *
    * Argumentos de entrada:
    *   (ninguno)
    *
    * Valor de retorno:
    *   boolean -> true si ya se ha llegado al final del fichero,
    *              false en caso contrario.
    *
    * Ficheros requeridos:
    *   El fichero debe estar abierto.
    *
    * Excepciones comprobadas:
    *   IllegalStateException -> si el Scanner está cerrado externamente.
    *
    *********************************************************************/
    public boolean isEndOfFile() {
        return !scan.hasNextLine();
    }

    /*********************************************************************
    *
    * Nombre del método: cerrar
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Cierra el Scanner asociado al fichero. Debe llamarse cuando ya
    *   no se van a leer más registros, para liberar el recurso.
    *
    * Argumentos de entrada:
    *   (ninguno)
    *
    * Valor de retorno:
    *   void
    *
    * Ficheros requeridos:
    *   El fichero debe haber sido abierto previamente por el constructor.
    *
    * Excepciones comprobadas:
    *   Ninguna comprobada. Puede lanzar IllegalStateException si se llama
    *   más de una vez sobre un Scanner ya cerrado.
    *
    *********************************************************************/
    public void cerrar() {
        scan.close();
    }
}
