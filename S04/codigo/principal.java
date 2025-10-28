/*********************************************************************
*
* Nombre de la clase: principal
* Iniciales autores / Grupo prácticas: RMM, HJC, PMS / G05
* Fecha de creación: 
* Versión de la clase: 
* Descripción de la clase:
*   Clase principal del programa. Se encarga de:
*     - Leer el fichero CSV de peticiones de partida.
*     - Clasificar cada petición en la cola adecuada:
*         * premium, partido largo
*         * premium, partido corto
*         * no premium, partido largo
*         * no premium, partido corto
*       Usando PriorityBlockingQueue para premium (prioridad = mayor skillLevel)
*       y LinkedBlockingQueue (FIFO) para no premium, tal y como exige la
*       práctica de laboratorio de colas (Curso 2025/26).
*     - Simular la creación de partidos siguiendo el orden:
*         1. premium largos
*         2. premium cortos
*         3. no premium largos
*         4. no premium cortos
*       generando varios emparejamientos premium por cada emparejamiento
*       no premium, respetando la política del sistema descrita.
*     - Imprimir por consola el proceso de lectura y de emparejamiento.
*
**********************************************************************/

import java.io.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;

public class principal {

    // LECTOR: Scanner global, actualmente no se utiliza en la simulación principal
    final static Scanner LECTOR = new Scanner(System.in);

    /*********************************************************************
    *
    * Nombre del método: main
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Punto de entrada del programa. Declara y crea las colas necesarias
    *   para gestionar las peticiones de juego (premium/no premium,
    *   largo/corto), muestra mensajes informativos por consola, procesa
    *   el fichero CSV de entrada y finalmente lanza la simulación de
    *   emparejamientos de jugadores.
    *
    * Argumentos de entrada:
    *   String[] args -> argumentos de la línea de comandos (no utilizados).
    *
    * Valor de retorno:
    *   void
    *
    * Ficheros requeridos:
    *   Se espera que exista un fichero "player_requests.csv" con las
    *   peticiones de los jugadores, siguiendo el formato:
    *     RequestID;PlayerID;PremiumSuscription;SkillLevel;MatchType
    *   donde:
    *     - PremiumSuscription es "true"/"false"
    *     - MatchType es 'L' (largo) o 'S' (corto)
    *
    * Excepciones comprobadas:
    *   No se declaran aquí porque se gestionan con bloques try/catch
    *   dentro de otros métodos llamados (por ejemplo procesarFile()).
    *
    *********************************************************************/
    public static void main(String[] args)  {

        // partidosPremiumLargos:
        // Cola con prioridad para jugadores premium que piden partidos largos.
        // PriorityBlockingQueue usa compareTo de Peticion para priorizar nivel.
        Queue<Peticion> partidosPremiumLargos = new PriorityBlockingQueue<Peticion>();

        // partidosPremiumCortos:
        // Cola con prioridad para jugadores premium que piden partidos cortos.
        Queue<Peticion> partidosPremiumCortos = new PriorityBlockingQueue<Peticion>();

        // partidosNoPremiumLargos:
        // Cola FIFO (orden de llegada) para jugadores SIN premium que piden largo.
        Queue<Peticion> partidosNoPremiumLargos = new LinkedBlockingQueue<Peticion>();

        // partidosNoPremiumCortos:
        // Cola FIFO (orden de llegada) para jugadores SIN premium que piden corto.
        Queue<Peticion> partidosNoPremiumCortos = new LinkedBlockingQueue<Peticion>();
        
        System.out.println("======================");
        System.out.println("Procesando peticion");
        System.out.println("======================");

        // Carga de datos desde el fichero y clasificación en colas
        procesarFile(partidosPremiumLargos, partidosPremiumCortos,
                     partidosNoPremiumLargos, partidosNoPremiumCortos);

        System.out.println("======================");

        // Simulación del flujo de emparejamientos
        gestionarFlujoPartidos(partidosNoPremiumCortos,
                               partidosNoPremiumLargos,
                               partidosPremiumCortos,
                               partidosPremiumLargos);
    }

    /*********************************************************************
    *
    * Nombre del método: procesarFile
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Lee el fichero con las peticiones de partida ("player_requests.csv"),
    *   omite la cabecera, y para cada línea:
    *     - Crea un nuevo objeto Peticion y lo rellena
    *     - Muestra la petición por consola (traza)
    *     - Inserta la petición en la cola correspondiente según:
    *          · si el jugador es premium o no
    *          · si ha pedido partido largo ('L') o corto ('S')
    *
    * Argumentos de entrada:
    *   Queue<Peticion> premiumL   -> cola premium de partidos largos
    *   Queue<Peticion> premiumC   -> cola premium de partidos cortos
    *   Queue<Peticion> noPremiumL -> cola NO premium de partidos largos
    *   Queue<Peticion> noPremiumC -> cola NO premium de partidos cortos
    *
    * Valor de retorno:
    *   void
    *
    * Ficheros requeridos:
    *   "player_requests.csv" debe existir y ser legible en el mismo
    *   directorio de ejecución o ruta esperada por FicheroSecuencial.
    *   El método abre el fichero mediante FicheroSecuencial y lo recorre
    *   línea a línea.
    *
    * Excepciones comprobadas:
    *   FileNotFoundException -> si no se puede abrir el fichero CSV.
    *   IOException           -> si ocurre un error de E/S al leer.
    *   NumberFormatException -> puede propagarse internamente al parsear
    *                            campos numéricos (por ejemplo skillLevel).
    *
    *********************************************************************/
    public static void procesarFile(Queue<Peticion> premiumL, 
                                    Queue<Peticion> premiumC, 
                                    Queue<Peticion> noPremiumL, 
                                    Queue<Peticion> noPremiumC){ 
        
        String separador = ";";                // Separador de campos en el CSV
        String listado = "player_requests.csv"; // Nombre del fichero CSV de entrada

        try {
            FicheroSecuencial<Peticion> ft;
            ft = new FicheroSecuencial<Peticion>(listado, separador);
            
            // Se asume que la primera línea del CSV es cabecera y no contiene datos de jugadores
            ft.skip();
            
            Peticion peticionActual; // Objeto Peticion que iremos rellenando en cada iteración

            // Recorremos el fichero hasta que no queden más líneas
            while (!ft.isEndOfFile()) {

                peticionActual = new Peticion(); 
                ft.read(peticionActual);  // peticionActual.readData(campos) internamente

                // Traza por consola de la petición recién leída
                System.out.println(peticionActual.toString());

                // Clasificación según si el jugador es premium
                if (peticionActual.isPremiumSubscription()) {

                    // Jugador premium
                    if (peticionActual.getMatchType() == 'L') {
                        // Partido solicitado largo
                        premiumL.offer(peticionActual);
                        
                    } else if (peticionActual.getMatchType() == 'S'){
                        // Partido solicitado corto
                        premiumC.offer(peticionActual);
                    } 

                } else {
                    // Jugador NO premium
                    if (peticionActual.getMatchType() == 'L'){
                        // Partido solicitado largo
                        noPremiumL.offer(peticionActual);

                    } else if (peticionActual.getMatchType() == 'S'){
                        // Partido solicitado corto
                        noPremiumC.offer(peticionActual);
                    }
                }
            }

            // Estado final de las colas tras la lectura del fichero
            System.out.println("Tamaño premiC " + premiumC.size());
            System.out.println("Tamaño premiL " + premiumL.size());
            System.out.println("Tamaño NPpremiC " + noPremiumC.size());
            System.out.println("Tamaño NOpremiL " + noPremiumL.size());
            
        } catch (FileNotFoundException e) {
            // Error al abrir el fichero
            System.out.println(e.getMessage());
        } catch (IOException r){
            // Error genérico de E/S durante la lectura
            System.out.println(r.getMessage());
        }
    }

    /*********************************************************************
    *
    * Nombre del método: gestionarFlujoPartidos
    *
    * Autor original: XX
    *
    * Descripción del método:
    *   Simula el sistema de emparejamiento descrito en la práctica:
    *   en cada iteración intenta crear partidos en este orden:
    *     1. Partidos premium cortos (hasta 2 partidos por iteración
    *        siempre que haya >=4 jugadores; si no, 1 partido si hay >=2)
    *     2. Partidos premium largos   (misma lógica que cortos)
    *     3. Un partido no premium corto (si hay >=2 jugadores)
    *     4. Un partido no premium largo (si hay >=2 jugadores)
    *
    *   Se imprime por consola cada partido generado mostrando los IDs
    *   de los jugadores emparejados. El bucle continúa mientras haya
    *   al menos dos jugadores disponibles en alguna cola.
    *
    * Argumentos de entrada:
    *   Queue<Peticion> noPremiumC -> cola de no premium para partidos cortos
    *   Queue<Peticion> noPremiumL -> cola de no premium para partidos largos
    *   Queue<Peticion> premiumC   -> cola premium    para partidos cortos
    *   Queue<Peticion> premiumL   -> cola premium    para partidos largos
    *
    * Valor de retorno:
    *   void
    *
    * Ficheros requeridos:
    *   No requiere ficheros.
    *
    * Excepciones comprobadas:
    *   Ninguna comprobada.
    *
    *********************************************************************/
    public static void gestionarFlujoPartidos(Queue<Peticion> noPremiumC,
                                              Queue<Peticion> noPremiumL,
                                              Queue<Peticion> premiumC,
                                              Queue<Peticion> premiumL){

        System.out.println("Generando flujo de partidos, lo que se muestra es el ID de los jugadores enfrentados");

        int numPartido = 1;   // Contador genérico de partidos premium cortos/largos cuando hay un único partido
        int numiteracion = 1; // Número de ciclo de emparejamiento

        // Mientras quede al menos una cola con tamaño >= 2, se pueden seguir creando partidos
        while (premiumC.size() >= 2 || premiumL.size() >= 2 ||
               noPremiumC.size() >= 2 || noPremiumL.size() >= 2) {

            System.out.println("=============  Iterarion " + numiteracion + "  ==============");

            // Partidos premium cortos
            if (premiumC.size() >= 2) {
                int numCorto = 1; // contador de partidos premium cortos en esta iteración

                if (premiumC.size() >= 4) {
                    // Se generan 2 partidos premium cortos
                    Peticion petPremCorto1 = premiumC.poll(); // jugador 1
                    Peticion petPremCorto2 = premiumC.poll(); // jugador 2

                    System.out.println("Partido premium corto nº" + numCorto + ": " +
                                       petPremCorto1.getPlayerID() + " Vs " +
                                       petPremCorto2.getPlayerID());
                    numCorto++;

                    Peticion petPremCorto3 = premiumC.poll();
                    Peticion petPremCorto4 = premiumC.poll();

                    System.out.println("Partido premium corto nº" + numCorto + ": " +
                                       petPremCorto3.getPlayerID() + " Vs " +
                                       petPremCorto4.getPlayerID() + "\n");
                    numCorto++;

                } else {
                    // Sólo hay jugadores suficientes para 1 partido corto premium
                    Peticion petPremCorto1 = premiumC.poll();
                    Peticion petPremCorto2 = premiumC.poll();

                    System.out.println("Partido premium corto nº" + numPartido + ": " +
                                       petPremCorto1.getPlayerID() + " Vs " +
                                       petPremCorto2.getPlayerID() + "\n");
                }
            }

            // Partidos premium largos
            if (premiumL.size() >= 2) {
                int numLargo = 1; // contador de partidos premium largos en esta iteración

                if (premiumL.size() >= 4) {
                    // Se generan 2 partidos premium largos
                    Peticion petPremLargo1 = premiumL.poll();
                    Peticion petPremLargo2 = premiumL.poll();

                    System.out.println("Partido premium largo nº" + numLargo + ": " +
                                       petPremLargo1.getPlayerID() + " Vs " +
                                       petPremLargo2.getPlayerID());
                    numLargo++;

                    Peticion petPremLargo3 = premiumL.poll();
                    Peticion petPremLargo4 = premiumL.poll();

                    System.out.println("Partido premium largo nº" + numLargo + ": " +
                                       petPremLargo3.getPlayerID() + " Vs " +
                                       petPremLargo4.getPlayerID() + "\n");
                    numLargo++;
                    
                } else {
                    // Sólo hay jugadores suficientes para 1 partido largo premium
                    Peticion petPremLargo1 = premiumL.poll();
                    Peticion petPremLargo2 = premiumL.poll();

                    System.out.println("Partido premium largo nº" + numLargo + ": " +
                                       petPremLargo1.getPlayerID() + " Vs " +
                                       petPremLargo2.getPlayerID() + "\n");
                }
            }

            // Partido no premium corto (FIFO)
            if(noPremiumC.size() >= 2){
                Peticion nPremCorto1 = noPremiumC.poll();
                Peticion nPremCorto2 = noPremiumC.poll();

                System.out.println("Partido no premium corto: " +
                                   nPremCorto1.getPlayerID() + " Vs " +
                                   nPremCorto2.getPlayerID() + "\n");
            }

            // Partido no premium largo (FIFO)
            if(noPremiumL.size() >= 2){
                Peticion nPremLargo1 = noPremiumL.poll();
                Peticion nPremLargo2 = noPremiumL.poll();

                System.out.println("Partido no premium largo: " +
                                   nPremLargo1.getPlayerID() + " Vs " +
                                   nPremLargo2.getPlayerID() + "\n");
            }
            
            numiteracion++;
            System.out.println("=======================================================");
        }

        // Mensajes finales cuando ya no se pueden generar más partidos
        if (premiumC.size() == 1 ) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos premium cortos.\n");
        }

        if (premiumL.size() == 1) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos premium largos.\n");
        }

        if (noPremiumC.size() == 1 || noPremiumC.isEmpty()) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos no premium cortos.\n");
        }

        if (noPremiumL.size() == 1 || noPremiumL.isEmpty()) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos no premium largos.\n");
        }

        System.out.println("=========================FIN=========================");
    }
}
