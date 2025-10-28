/*********************************************************************
*
* Nombre de la clase: Peticion
* Iniciales autores / Grupo prácticas: RMM, HJC, PMS / G05
* Fecha de creación: 
* Versión de la clase: 
* Descripción de la clase:
*   Representa una petición de partida online en el sistema de emparejamiento
*   del videojuego. Cada petición contiene la información necesaria para
*   encolar al jugador en la cola correspondiente (premium/no premium,
*   partido largo/corto) y para priorizarlo según su nivel de habilidad.
*
**********************************************************************/

public class Peticion implements SequentialFileReader, Comparable<Peticion> {

    private String requestID;          // Identificador único de la petición en el sistema
    private String playerID;           // Identificador único del jugador que hace la petición
    private boolean premiumSubscription; // Indica si el jugador tiene suscripción premium (true) o no (false)
    private int skillLevel;            // Nivel de habilidad del jugador (se usa para prioridad en premium)
    private char matchType;            // Tipo de partido solicitado: 'L' (largo, ~30 min) o 'S' (corto, ~10 min)

    /*********************************************************************
    *
    * Nombre del método: Peticion
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Constructor vacío. Crea una petición sin inicializar campos.
    *   Útil cuando primero se crea el objeto y después se rellenan los
    *   valores leyendo del fichero secuencial.
    *
    * Argumentos de entrada:
    *   (ninguno)
    *
    * Valor de retorno:
    *   (no aplica, es un constructor)
    *
    * Ficheros requeridos:
    *   No requiere ficheros.
    *
    * Excepciones comprobadas:
    *   Ninguna.
    *
    *********************************************************************/
    public Peticion() {
        // Constructor por defecto sin inicialización explícita
    }

    /*********************************************************************
    *
    * Nombre del método: Peticion
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Constructor completo. Crea una petición con todos los datos
    *   relevantes para el emparejamiento.
    *
    * Argumentos de entrada:
    *   String requestID            -> identificador interno de la petición
    *   String playerID             -> identificador del jugador
    *   boolean premiumSubscription -> true si el jugador es premium
    *   int skillLevel              -> nivel de habilidad del jugador
    *   char matchType              -> tipo de partido solicitado ('L' o 'S')
    *
    * Valor de retorno:
    *   (no aplica, es un constructor)
    *
    * Ficheros requeridos:
    *   No requiere ficheros.
    *
    * Excepciones comprobadas:
    *   Ninguna.
    *
    *********************************************************************/
    public Peticion(String requestID,
                    String playerID,
                    boolean premiumSubscription,
                    int skillLevel,
                    char matchType) {

        this.requestID = requestID;
        this.playerID = playerID;
        this.premiumSubscription = premiumSubscription;
        this.skillLevel = skillLevel;
        this.matchType = matchType;
    }

    /*********************************************************************
    *
    * Nombre del método: readData
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Carga en el objeto Peticion los datos leídos de una línea del
    *   fichero CSV. La línea ya viene troceada en campos (split por ';').
    *   El orden esperado de los campos es:
    *     [0] requestID
    *     [1] playerID
    *     [2] premiumSubscription ("true"/"false")
    *     [3] skillLevel (entero)
    *     [4] matchType ('L' o 'S')
    *
    * Argumentos de entrada:
    *   String[] data -> array de Strings con los campos de la línea CSV.
    *
    * Valor de retorno:
    *   void. El propio objeto actualiza sus atributos internos.
    *
    * Ficheros requeridos:
    *   Se asume que la lectura del fichero ya ha sido realizada por
    *   otra clase (por ejemplo FicheroSecuencial) y que aquí sólo se
    *   interpretan los datos.
    *
    * Excepciones comprobadas:
    *   NumberFormatException -> si skillLevel no es un entero válido.
    *   ArrayIndexOutOfBoundsException -> si la línea no tiene todos
    *                                     los campos esperados.
    *
    *********************************************************************/
    @Override
    public void readData(String[] data) {

        // data[0] -> requestID
        // data[1] -> playerID
        // data[2] -> premiumSubscription ("true"/"false")
        // data[3] -> skillLevel (ej. "87")
        // data[4] -> matchType ('L' o 'S')

        this.requestID = data[0];
        this.playerID = data[1];
        this.premiumSubscription = Boolean.parseBoolean(data[2]);
        this.skillLevel = Integer.parseInt(data[3]);
        this.matchType = data[4].charAt(0);
    }

    /*********************************************************************
    *
    * Nombre del método: compareTo
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Permite ordenar Peticion en estructuras con prioridad
    *   (por ejemplo PriorityQueue). En colas premium, interesa que el
    *   jugador con mayor nivel de habilidad tenga más prioridad.
    *
    *   Criterio típico:
    *   - Devuelve valor negativo si esta petición tiene MÁS prioridad
    *     que la otra.
    *   - Devuelve valor positivo si esta petición tiene MENOS prioridad.
    *   - Devuelve 0 si son equivalentes.
    *
    * Argumentos de entrada:
    *   Peticion otra -> la petición con la que se compara.
    *
    * Valor de retorno:
    *   int -> resultado de la comparación de niveles de habilidad.
    *          Normalmente (otra.skillLevel - this.skillLevel) para
    *          que el más hábil salga primero.
    *
    * Ficheros requeridos:
    *   No requiere ficheros.
    *
    * Excepciones comprobadas:
    *   Ninguna.
    *
    *********************************************************************/
    @Override
    public int compareTo(Peticion otra) {
        // Queremos prioridad más alta para skillLevel más alto.
        // Si this.skillLevel = 95 y otra.skillLevel = 80,
        // this debe ir "antes", así que devolvemos número negativo.
        return Integer.compare(otra.skillLevel, this.skillLevel);
    }

    /*********************************************************************
    *
    * Nombre del método: getRequestID
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Devuelve el identificador único de la petición.
    *
    * Argumentos de entrada:
    *   (ninguno)
    *
    * Valor de retorno:
    *   String -> identificador de la petición.
    *
    *********************************************************************/
    public String getRequestID() {
        return requestID;
    }

    /*********************************************************************
    *
    * Nombre del método: setRequestID
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Establece el identificador único de la petición.
    *
    * Argumentos de entrada:
    *   String requestID -> nuevo identificador.
    *
    * Valor de retorno:
    *   void
    *
    *********************************************************************/
    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    /*********************************************************************
    * getPlayerID / setPlayerID
    * Métodos de acceso para el identificador del jugador.
    *********************************************************************/
    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    /*********************************************************************
    * isPremiumSubscription / setPremiumSubscription
    * Devuelve o modifica si el jugador es premium.
    *********************************************************************/
    public boolean isPremiumSubscription() {
        return premiumSubscription;
    }

    public void setPremiumSubscription(boolean premiumSubscription) {
        this.premiumSubscription = premiumSubscription;
    }

    /*********************************************************************
    * getSkillLevel / setSkillLevel
    * Devuelve o modifica el nivel de habilidad del jugador.
    *********************************************************************/
    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    /*********************************************************************
    * getMatchType / setMatchType
    * Devuelve o modifica el tipo de partido solicitado ('L' o 'S').
    *********************************************************************/
    public char getMatchType() {
        return matchType;
    }

    public void setMatchType(char matchType) {
        this.matchType = matchType;
    }

    /*********************************************************************
    *
    * Nombre del método: toString
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Devuelve una representación en texto de la petición, útil para
    *   depuración y trazas por consola.
    *
    * Argumentos de entrada:
    *   (ninguno)
    *
    * Valor de retorno:
    *   String -> cadena con los datos principales de la petición.
    *
    *********************************************************************/
    @Override
    public String toString() {
        return "Peticion{" +
               "requestID='" + requestID + '\'' +
               ", playerID='" + playerID + '\'' +
               ", premium=" + premiumSubscription +
               ", skillLevel=" + skillLevel +
               ", matchType=" + matchType +
               '}';
    }

}
