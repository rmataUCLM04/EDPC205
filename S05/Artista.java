/*********************************************************************
*
* Class Name: Artista
* Author/s name: RMM, HJG, PMS 
* Release/Creation date: 
* Class version: 1.0
* Class description:
*   Representa un artista del festival Glastonbury Madrid 2025.
*   Contiene información sobre su nombre, género, día de actuación,
*   escenario, duración del concierto y nivel de popularidad.
*   Implementa las interfaces SequentialFileReader y Comparable<Artista>
*   para permitir la lectura desde fichero y la comparación entre artistas.
*
**********************************************************************/

public class Artista implements SequentialFileReader, Comparable<Artista> {

	 private String nombre;       // Nombre del artista o grupo
	 private String genero;       // Género musical (Rock, Pop, etc.)
	 private int dia;             // Día de actuación (1, 2 o 3)
	 private String escenario;    // Escenario asignado (Main o River)
	 private int duracion;        // Duración del concierto en minutos
	 private int popularidad;     // Nivel de popularidad (1 a 5)
	 
		/*********************************************************************
		 *
		 * Method name: Artista Description of the Method: Constructor vacío. Se utiliza
		 * para crear un objeto Artista antes de leer los datos desde un fichero.
		 * Calling arguments: none Return value: none Required Files: none Checked
		 * Exceptions: none
		 *
		 *********************************************************************/
		public Artista() {

		}
		
		/*********************************************************************
	    *
	    * Method name: Artista
	    * Description of the Method:
	    *   Constructor completo. Inicializa todos los atributos con los valores
	    *   recibidos por parámetro.
	    * Calling arguments:
	    *   nombre (String) Nombre del artista
	    *   genero (String) Género musical principal
	    *   dia (int) Día de actuación
	    *   escenario (String) Escenario asignado
	    *   duracion (int) Duración del concierto (en minutos)
	    *   popularidad (int) Nivel de popularidad (1–5)
	    * Return value: none
	    * Required Files: none
	    * Checked Exceptions: none
	    *
	    *********************************************************************/
    
    public Artista(String nombre, String genero, int dia, String escenario, int duracion, int popularidad) {
        this.nombre = nombre;
        this.genero = genero;
        this.dia = dia;
        this.escenario = escenario;
        this.duracion = duracion;
        this.popularidad = popularidad;
    }
    
    /*********************************************************************
    *
    * Method name: readData
    * Description of the Method:
    *   Asigna a los atributos del objeto los valores recibidos desde un
    *   array de cadenas, normalmente obtenido de la lectura de un fichero CSV.
    * Calling arguments:
    *   data (String[]) Array con los campos del artista:
    *          [0]=nombre 
    *          [1]=género 
    *          [2]=día 
    *          [3]=escenario
    *          [4]=duración
    *          [5]=popularidad
    * Return value: none
    * Required Files: none
    * Checked Exceptions: NumberFormatException (si algún valor numérico
    *                     no puede convertirse correctamente)
    *
    *********************************************************************/
    
    public void readData(String[] data){
        this.nombre = data[0];
        this.genero = data[1];
        this.dia = Integer.parseInt(data[2]);
        this.escenario = data[3];
        this.duracion = Integer.parseInt(data[4]);
        this.popularidad = Integer.parseInt(data[5]);
    }

    /** Getters */
    public String getNombre() {
        return nombre; 
    }
    public String getGenero() {
        return genero; 
    }
    public int getDia() { 
        return dia; 
    }
    public String getEscenario() { 
        return escenario; 
    }
    public int getDuracion() { 
        return duracion; 
    }
    public int getPopularidad() { 
        return popularidad; 
    }

    /** Setters */
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public void setGenero(String genero) { 
        this.genero = genero; 
    }

    public void setDia(int dia) { 
        this.dia = dia; 
    }

    public void setEscenario(String escenario) { 
        this.escenario = escenario; 
    }

    public void setDuracion(int duracion) { 
        this.duracion = duracion; 
    }

    public void setPopularidad(int popularidad) { 
        this.popularidad = popularidad; 
    }
    
    /*********************************************************************
    *
    * Method name: compareTo
    * Description of the Method:
    *   Compara el objeto actual con otro artista siguiendo los criterios de
    *   ordenación establecidos en la práctica:
    *     1. Popularidad ascendente
    *     2. Duración ascendente
    *     3. Nombre alfabético
    * Calling arguments:
    * 	otro (Artista) El otro artista con el que se compara
    * Return value:
    *   int → valor negativo si este artista debe ir antes,
    *          0 si son equivalentes,
    *          positivo si debe ir después.
    * Required Files: none
    * Checked Exceptions: none
    *
    *********************************************************************/
    
    public int compareTo(Artista otro) {
        int ordenar = 0;
        ordenar = Integer.compare(this.popularidad, otro.getPopularidad());

        if(ordenar == 0){
            ordenar = Integer.compare(this.duracion, otro.getDuracion());

        }

        if(ordenar == 0){

            ordenar = this.nombre.compareTo(otro.getNombre());
        }   
            
        return ordenar;
    }
    
	/*********************************************************************
	 *
	 * Method name: toString Description of the Method: Devuelve una representación
	 * textual del artista con todos sus datos, utilizada principalmente para
	 * mostrar información en consola. Calling arguments: none Return value: String
	 * → Cadena formateada con los datos del artista. Required Files: none Checked
	 * Exceptions: none
	 *
	 *********************************************************************/

	public String toString() {
		return String.format("%s (%s) - Dia %d, %s, %d min, Popularidad: %d", nombre, genero, dia, escenario, duracion,
				popularidad);
	}
}
