/*********************************************************************
*
* Nombre de la clase: Peticion
* Autores: RMM, HJG, PMS
* Fecha de creación:
* Versión de la clase:
* Descripción de la clase:
*   Representa una solicitud (petición) de un jugador para formar parte
*   de un partido. Contiene información sobre el jugador, su nivel de
*   habilidad, si es usuario premium y el tipo de partido solicitado.
*   Implementa la interfaz SequentialFileReader para poder leer sus datos
*   desde un fichero secuencial, y la interfaz Comparable<Peticion> para
*   establecer un criterio de ordenación entre peticiones.
*
*********************************************************************/

public class Peticion implements SequentialFileReader, Comparable<Peticion> {
    
    // Atributos que almacenan la información de cada petición.
    private String requestID;             // Identificador único de la petición.
    private String playerID;              // Identificador del jugador.
    private boolean premiumSubscription;  // Indica si el jugador tiene suscripción premium.
    private int skillLevel;               // Nivel de habilidad del jugador.
    private char matchType;               // Tipo de partido ('L' largo, 'C' corto, etc.).

    /*********************************************************************
    *
    * Nombre del método: Peticion (constructor por defecto)
    *
    * Descripción del método:
    *   Constructor vacío que crea un objeto Peticion sin inicializar
    *   sus atributos. Útil para cargar datos posteriormente con readData().
    *
    *********************************************************************/
    public Peticion() {
        
    }

    /*********************************************************************
    *
    * Nombre del método: Peticion (constructor parametrizado)
    *
    * Descripción del método:
    *   Inicializa un objeto Peticion con los valores recibidos por parámetro.
    *
    * Argumentos de entrada:
    *   - String requestID: identificador único de la petición.
    *   - String playerID: identificador del jugador.
    *   - boolean premiumSubscription: true si el jugador es premium.
    *   - int skillLevel: nivel de habilidad del jugador.
    *   - char matchType: tipo de partido solicitado.
    *
    *********************************************************************/
    public Peticion(String requestID, String playerID, boolean premiumSubscription, int skillLevel, char matchType) {
        this.requestID = requestID;
        this.playerID = playerID;
        this.premiumSubscription = premiumSubscription;
        this.skillLevel = skillLevel;
        this.matchType = matchType;
    }

    /*********************************************************************
    *
    * Nombre del método: compareTo
    *
    * Descripción del método:
    *   Define el criterio de comparación entre objetos Peticion para
    *   permitir su ordenación. Si la petición es de un jugador premium,
    *   se compara según el nivel de habilidad (mayor skillLevel tiene
    *   más prioridad). Si no es premium, no se aplica prioridad.
    *
    * Argumentos de entrada:
    *   - Peticion o: otra petición con la que comparar.
    *
    * Valor de retorno:
    *   int -> negativo si this tiene más prioridad, positivo si la tiene 'o',
    *   cero si no hay diferencia.
    *
    *********************************************************************/
    public int compareTo(Peticion o) {
        int ordenar = 0;
        if (premiumSubscription) {
            ordenar = Integer.compare(o.getSkillLevel(), skillLevel); // Orden descendente por nivel.
        } else {
            ordenar = 0; // No se aplica orden especial si no es premium.
        }
        return ordenar; 
    }

    /*********************************************************************
    *
    * Nombre del método: readData
    *
    * Descripción del método:
    *   Carga los datos de la petición a partir de un vector de cadenas
    *   (normalmente leído desde un fichero CSV mediante FicheroSecuencial).
    *
    * Argumentos de entrada:
    *   - String[] data: vector con los datos de la línea leída.
    *                    data[0] → requestID
    *                    data[1] → playerID
    *                    data[2] → premiumSubscription
    *                    data[3] → skillLevel
    *                    data[4] → matchType
    *
    *********************************************************************/
    public void readData(String[] data) {
        this.requestID = data[0];
        this.playerID = data[1];
        this.premiumSubscription = Boolean.parseBoolean(data[2]);
        this.skillLevel = Integer.parseInt(data[3]);
        this.matchType = data[4].charAt(0);
    }

    /*********************************************************************
    *
    * Nombre del método: toString
    *
    * Descripción del método:
    *   Devuelve una representación textual con toda la información
    *   de la petición, útil para depuración o mostrar resultados.
    *
    * Valor de retorno:
    *   String -> descripción completa de la petición.
    *
    *********************************************************************/
    public String toString() {
        return "Información de cada Petición: [Id Petición: " + requestID 
            + ", Nivel de habilidad: " + skillLevel 
            + ", Premium: " + premiumSubscription
            + ", Tipo de partido: " + matchType 
            + ", ID jugador: " + playerID + " ]\n";
    }

    // Métodos getters y setters de cada atributo:
    public String getRequestID() {
        return requestID;
    }

    public void setRequestID(String requestID) {
        this.requestID = requestID;
    }

    public String getPlayerID() {
        return playerID;
    }

    public void setPlayerID(String playerID) {
        this.playerID = playerID;
    }

    public boolean isPremiumSubscription() {
        return premiumSubscription;
    }

    public void setPremiumSubscription(boolean premiumSubscription) {
        this.premiumSubscription = premiumSubscription;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public char getMatchType() {
        return matchType;
    }

    public void setMatchType(char matchType) {
        this.matchType = matchType;
    }
}
