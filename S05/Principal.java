import java.util.List;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*********************************************************************
*
* Class Name: Principal
* Author/s name: RMM, HJG, PMS 
* Release/Creation date: 
* Class version: 1.0
* Class description:
*   Clase principal del programa. Se encarga de:
*     - Leer los artistas desde el fichero "artists.csv".
*     - Clasificarlos en listas según día (1,2,3) y escenario (Main/River).
*     - Ordenar cada lista siguiendo el criterio definido en Artista.compareTo()
*       (popularidad asc, duración asc, nombre alfabético).
*     - Generar y mostrar por consola el horario de cada escenario:
*         hora de inicio de cada actuación,
*         tiempo total de música,
*         popularidad media
*         y artista principal (último del día).
*
**********************************************************************/

public class Principal {
	
	 /*********************************************************************
    *
    * Method name: main
    *
    * Description of the Method:
    *   Punto de entrada del programa. Prepara las listas de artistas por
    *   escenario y día, procesa el fichero CSV, ordena las listas y genera
    *   la salida del horario del festival.
    *
    * Calling arguments:
    *   args (String[]) Argumentos de línea de comandos. No se usan.
    *
    * Return value: none
    *
    * Required Files:
    *   Debe existir el fichero "artists.csv" con los datos de los artistas.
    *
    * Checked Exceptions:
    *   No se propagan excepciones; la lectura del fichero se gestiona
    *   internamente con bloques try/catch en procesarFile().
    *
    *********************************************************************/

    public static void main(String[] args) {
    	
    	// Listas de artistas por escenario y día.
        List<Artista> MainStageDia1 = new ArrayList <Artista>();
        List<Artista> MainStageDia2 = new ArrayList <Artista>();
        List<Artista> MainStageDia3 = new ArrayList <Artista>();
        List<Artista> RiverStageDia1 = new ArrayList <Artista>();
        List<Artista> RiverStageDia2 = new ArrayList <Artista>();
        List<Artista> RiverStageDia3 = new ArrayList <Artista>();
        
        // Cargar las listas leyendo el fichero CSV y clasificando cada artista
        procesarFile(MainStageDia1, MainStageDia2, MainStageDia3, RiverStageDia1, RiverStageDia2, RiverStageDia3);
        
        // Ordenar las listas usando compareTo() de Artista
        Collections.sort(MainStageDia1);
        Collections.sort(MainStageDia2);
        Collections.sort(MainStageDia3);
        Collections.sort(RiverStageDia1);
        Collections.sort(RiverStageDia2);
        Collections.sort(RiverStageDia3);
        
        // Configuración de horas de inicio para cada escenario
        int inicioMain = 14*60;  // Main Stage empieza a las 14:00 (en minutos)
        int inicioRiver = (13*60) + 30; // River Stage empieza a las 13:30
        
     // Generar y mostrar horarios por escenario y día
        generarHorarios(MainStageDia1, "MAIN STAGE DIA 1", inicioMain);
        generarHorarios(MainStageDia2, "MAIN STAGE DIA 2", inicioMain);
        generarHorarios(MainStageDia3, "MAIN STAGE DIA 3", inicioMain);
        generarHorarios(RiverStageDia1, "RIVER STAGE DIA 1", inicioRiver);
        generarHorarios(RiverStageDia2, "RIVER STAGE DIA 3", inicioRiver);
        generarHorarios(RiverStageDia3, "RIVER STAGE DIA 3", inicioRiver);

    }
    
    /*********************************************************************
    *
    * Method name: procesarFile
    *
    * Description of the Method:
    *   Lee el fichero de artistas (artists.csv), crea objetos Artista,
    *   y los clasifica en las listas correspondientes según:
    *     - escenario ("main" / "river"), ignorando mayúsculas/minúsculas
    *     - día (1, 2 o 3)
    *
    *   También salta la primera línea (cabecera del CSV).
    *
    * Calling arguments:
    *   mainD1   (List<Artista>) Lista Main Stage día 1
    *   mainD2   (List<Artista>) Lista Main Stage día 2
    *   mainD3   (List<Artista>) Lista Main Stage día 3
    *   riverD1  (List<Artista>) Lista River Stage día 1
    *   riverD2  (List<Artista>) Lista River Stage día 2
    *   riverD3  (List<Artista>) Lista River Stage día 3
    *
    * Return value: none
    *
    * Required Files:
    *   Requiere el fichero "artists.csv" accesible para lectura.
    *   El fichero debe tener formato delimitado por ';' y cabecera en
    *   la primera línea.
    *
    * Checked Exceptions:
    *   FileNotFoundException:
    *       - Se lanza si el fichero no existe o no se puede abrir.
    *       - Aquí se captura e imprime el mensaje de error.
    *
    *   IOException:
    *       - Se lanza si ocurre algún problema al leer línea a línea el fichero.
    *       - También se captura y se imprime el mensaje.
    *
    *********************************************************************/

    public static void procesarFile(List<Artista> mainD1, List<Artista> mainD2, List<Artista> mainD3, List<Artista> riverD1, List<Artista> riverD2, List<Artista> riverD3){
        String separador = ";";
        String listaArtistas = "artists.csv";

        try {

            FicheroSecuencial<Artista> fArtist;
            fArtist = new FicheroSecuencial<Artista> (listaArtistas, separador);
            
         // Saltar la cabecera del fichero (primera línea)
            fArtist.skip();

            Artista artistaLeido;
            
			// Leer hasta llegar al final del fichero
            while(!fArtist.isEndOfFile()){
            	
            	// Crear un artista vacío para cargarle los datos
                artistaLeido = new Artista();
                
                // Leer una línea del fichero y asignarla al objeto
                fArtist.read(artistaLeido);

                // Clasificar el artista según su escenario y día
                if((artistaLeido.getEscenario()).equalsIgnoreCase("main")){

                    if((artistaLeido.getDia()) == 1){
                        mainD1.add(artistaLeido);

                    } else if((artistaLeido.getDia()) == 2){
                        mainD2.add(artistaLeido);

                    } else if((artistaLeido.getDia()) == 3){
                        mainD3.add(artistaLeido);

                    }
                }

                if ((artistaLeido.getEscenario()).equalsIgnoreCase("river")) {
                    
                    if ((artistaLeido.getDia()) == 1) {
                        riverD1.add(artistaLeido);
                    
                    } else if((artistaLeido.getDia()) == 2){
                        riverD2.add(artistaLeido);

                    } else if((artistaLeido.getDia()) == 3){
                        riverD3.add(artistaLeido);

                    } 
                }
            }

        } catch (FileNotFoundException e) {
            
            System.out.println(e.getMessage());

        } catch (IOException s) {

            System.out.println(s.getMessage());
        }
    }
    
    /*********************************************************************
    *
    * Method name: aHora
    *
    * Description of the Method:
    *   Convierte una cantidad de minutos (desde 00:00) a formato hh:mm.
    *   Ejemplo: 14*60 -> "14:00", 13*60+30 -> "13:30".
    *
    * Calling arguments:
    *   minutos (int) Minutos totales desde las 00:00.
    *
    * Return value:
    *   String → Cadena con la hora formateada "hh:mm".
    *
    * Required Files: none
    *
    * Checked Exceptions: none
    *
    *********************************************************************/
    
    public static String aHora(int minutos) {
        int h = minutos / 60;
        int m = minutos % 60;
        return String.format("%02d:%02d", h, m);
    }
   
    /*********************************************************************
    *
    * Method name: generarHorarios
    *
    * Description of the Method:
    *   Muestra por consola el horario completo de un escenario concreto.
    *   Para cada artista de la lista:
    *     - Imprime la hora de inicio de su actuación y sus datos.
    *     - Suma su duración al tiempo total de música.
    *     - Acumula su popularidad para luego calcular la media.
    *   Entre actuaciones se añaden 30 minutos de pausa técnica.
    *
    *   Al final, también imprime:
    *     - Tiempo total de música en el escenario.
    *     - Popularidad media del cartel.
    *     - El artista principal (el último en tocar).
    *
    * Calling arguments:
    *   escenario (List<Artista>) Lista de artistas ya ORDENADOS
    *                    para ese escenario y día concreto.
    *   titulo (String) Título descriptivo que se imprimirá antes
    *                    del horario (ej. "MAIN STAGE DIA 1").
    *   horadeInicio (int) Minuto inicial del primer concierto
    *                    (por ejemplo, 14*60 para 14:00).
    *
    * Return value: none
    *
    * Required Files: none
    *
    * Checked Exceptions: none
    *
    *********************************************************************/

    public static void generarHorarios(List<Artista> escenario, String titulo,int horadeInicio){
        System.out.println("\n" +titulo); // Ponemos el nombre del escenario que vamos a tratar
        System.out.println("-----------------------------");

        if(escenario.isEmpty()){
            System.out.println("No se esperan artistas para este escenario");
        }
        
        // Variables para generar el horario
        int horaActual = horadeInicio; // Minutos actuales desde medianoche
        int tiempoTotalMusica = 0; // Suma de duraciones de todas las actuaciones
        double popularidadTotal=0.0; // Suma de popularidad para luego hacer la media
      
        // Recorrer todos los artistas del escenario en orden
        for(int i =0; i<escenario.size();i++){
            Artista artista = escenario.get(i); // Seleccionamos el Artista i
            
            // Imprimir hora de inicio y datos del artista
            System.out.println(aHora(horaActual)+ "-" + artista.toString());
            
            // Acumular estadísticas
            tiempoTotalMusica = tiempoTotalMusica+artista.getDuracion();
            popularidadTotal = popularidadTotal+artista.getPopularidad();
            
            // Avanzar el reloj: duración del set + 30 minutos de pausa
            horaActual = horaActual+artista.getDuracion()+30;

        }
        
        // Calcular popularidad media del escenario
        double popularidadMedia = popularidadTotal/escenario.size();
        
        // El último artista de la lista es el cabeza de cartel
        Artista artistaPrincipal = escenario.get(escenario.size() -1); 

        System.out.println("--------------");
        System.out.println("Tiempo total de musica = " +tiempoTotalMusica + "min" );
      
        System.out.printf("Popularidad media = %.2f ",popularidadMedia);
        System.out.println("\n");
        System.out.println("Artista principal = " +artistaPrincipal.getNombre());


    }


}
