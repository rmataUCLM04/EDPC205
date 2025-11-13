package Vancouver;

/*********************************************************************
*
* Nombre de la clase: SequentialFileReader
* Iniciales autores / Grupo prácticas: RMM, HJG, PMS / G05
* Fecha de creación: 
* Versión de la clase: 
* Descripción de la clase:
*   Interfaz funcional mínima que deben implementar las clases cuyos
*   objetos se van a rellenar leyendo datos de un fichero secuencial.
*   Define el método readData, que recibe los campos ya separados.
*
**********************************************************************/
public interface SequentialFileReader {

	/*********************************************************************
    *
    * Nombre del método: readData
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Rellena el estado interno del objeto a partir de los datos leídos
    *   de una línea del fichero. La línea debe haberse separado en campos
    *   (por ejemplo usando split(";")) antes de llamar a este método.
    *
    * Argumentos de entrada:
    *   String[] data -> array con los valores de la línea descompuesta.
    *
    * Valor de retorno:
    *   void. El propio objeto debe actualizar sus atributos internos.
    *
    * Ficheros requeridos:
    *   Se asume que la lectura del fichero y la apertura/cierre del mismo
    *   la gestiona otra clase (por ejemplo FicheroSecuencial).
    *
    * Excepciones comprobadas:
    *   Depende de la implementación concreta. Por ejemplo, puede lanzar
    *   NumberFormatException si algún campo numérico no es válido.
    *
    *********************************************************************/
    void readData(String[] data);
}