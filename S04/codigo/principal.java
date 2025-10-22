import java.io.*;
import java.util.Scanner;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.PriorityBlockingQueue;


public class principal {
    //compare to para las colas con prioridad
    final static Scanner LECTOR = new Scanner(System.in);
    public static void main(String[] args)  {

        //los que no tienen subscripcion premium no tienen prioridad
        Queue<Peticion> partidosPremiumLargos = new PriorityBlockingQueue <Peticion>();
        Queue<Peticion> partidosPremiumCortos = new PriorityBlockingQueue<Peticion>();
        Queue<Peticion> partidosNoPremiumLargos = new LinkedBlockingQueue<Peticion>();
        Queue<Peticion> partidosNoPremiumCortos = new LinkedBlockingQueue<Peticion>();
        
        System.out.println("======================");

        System.out.println("Procesando peticion");
        System.out.println("======================");

        procesarFile(partidosPremiumLargos, partidosPremiumCortos, partidosNoPremiumLargos, partidosNoPremiumCortos );
        System.out.println("======================");

        gestionarFlujoPartidos(partidosNoPremiumCortos, partidosNoPremiumLargos, partidosPremiumCortos, partidosPremiumLargos);

    }


    public static void procesarFile(Queue<Peticion> premiumL, 
        Queue<Peticion> premiumC, 
        Queue<Peticion> noPremiumL, 
        Queue<Peticion> noPremiumC){ 
        
        String separador = ";";
        String listado = "player_requests.csv";
        try {
            
            FicheroSecuencial<Peticion> ft;
            ft = new FicheroSecuencial<Peticion>(listado, separador);
            
            ft.skip();
            
            Peticion peticionActual; 

            while (!ft.isEndOfFile()) {

                peticionActual = new Peticion(); 
                ft.read(peticionActual); 

                System.out.println(peticionActual.toString());

                //Filtra la peticion dentro de si es premium
                if (peticionActual.isPremiumSubscription()) {

                    if (peticionActual.getMatchType() == 'L') {
                        //Si el partido es corto
                        premiumL.offer(peticionActual);
                        
                    } else if (peticionActual.getMatchType() == 'S'){
                        //si el partido es largo
                        premiumC.offer(peticionActual);
                    } 

                //Filtra la peticion dentro de que no es premium
                } else if (!peticionActual.isPremiumSubscription()) {
                    if (peticionActual.getMatchType() == 'L'){
                        //Si el partido es largo
                        noPremiumL.offer(peticionActual);
                       

                    } else if (peticionActual.getMatchType() == 'S'){
                        //si el partido es corto
                        noPremiumC.offer(peticionActual);
                    }
                }
                

            }
            System.out.println("Tamaño premiC " + premiumC.size());
                System.out.println("Tamaño premiL " + premiumL.size());
                System.out.println("Tamaño NPpremiC " + noPremiumC.size());
                System.out.println("Tamaño NOpremiL " + noPremiumL.size());
            
        } catch (FileNotFoundException e) {

           System.out.println(e.getMessage());; 
        } catch (IOException r){

            System.out.println(r.getMessage());
        }
    }

    public static void gestionarFlujoPartidos(Queue<Peticion> noPremiumC, Queue<Peticion> noPremiumL, Queue<Peticion> premiumC, Queue<Peticion> premiumL){
        System.out.println("Generando flujo de partidos, lo que se muestra es el ID de los jugadores enfrentados");
        int numPartido = 1;
        int numiteracion = 1;
        while (premiumC.size() >= 2 ||premiumL.size() >= 2 ||noPremiumC.size() >= 2 ||noPremiumL.size() >= 2) {
            //
            System.out.println("=============  Iterarion "+numiteracion+"  ==============");
            
            //Partidos premium cortos
            if (premiumC.size() >= 2) {
                int numCorto = 1;
                if (premiumC.size() >= 4) {
                    Peticion petPremCorto1 = premiumC.poll();
                    Peticion petPremCorto2 = premiumC.poll();

                    System.out.println("Partido premium corto nº"+numCorto+": " + petPremCorto1.getPlayerID() + " Vs " + petPremCorto2.getPlayerID());
                    numCorto++;

                    Peticion petPremCorto3 = premiumC.poll();
                    Peticion petPremCorto4 = premiumC.poll();

                    System.out.println("Partido premium corto nº"+numCorto+": " + petPremCorto3.getPlayerID() + " Vs " + petPremCorto4.getPlayerID()+"\n");
                    numCorto++;

                } else {
                    Peticion petPremCorto1 = premiumC.poll();
                    Peticion petPremCorto2 = premiumC.poll();
                    System.out.println("Partido premium corto nº"+numPartido+": " + petPremCorto1.getPlayerID() + " Vs " + petPremCorto2.getPlayerID()+"\n");
                    
                }
            }

            //Partidos premium largos
            if (premiumL.size() >= 2) {
                int numLargo = 1;
                if (premiumL.size() >= 4) {
                    Peticion petPremLargo1 = premiumL.poll();
                    Peticion petPremLargo2 = premiumL.poll();

                    System.out.println("Partido premium largo nº"+numLargo+": " + petPremLargo1.getPlayerID() + " Vs " + petPremLargo2.getPlayerID());
                    numLargo++;

                    Peticion petPremLargo3 = premiumL.poll();
                    Peticion petPremLargo4 = premiumL.poll();

                    System.out.println("Partido premium largo nº"+numLargo+": " + petPremLargo3.getPlayerID() + " Vs " + petPremLargo4.getPlayerID()+"\n");
                    numLargo++;
                    
                } else {
                    Peticion petPremLargo1 = premiumL.poll();
                    Peticion petPremLargo2 = premiumL.poll();
                    System.out.println("Partido premium largo nº"+numLargo+": " + petPremLargo1.getPlayerID() + " Vs " + petPremLargo2.getPlayerID()+"\n");
                    
                }
            }

            //Partidos no premium cortos
            if(noPremiumC.size() >= 2){
              
                Peticion nPremCorto1 = noPremiumC.poll();
                Peticion nPremCorto2 = noPremiumC.poll();
                System.out.println("Partido no premium corto: "+ nPremCorto1.getPlayerID() + " Vs " +nPremCorto2.getPlayerID()+"\n");
                
            }

            if(noPremiumL.size() >= 2){
               
                Peticion nPremLargo1 = noPremiumL.poll();
                Peticion nPremLargo2 = noPremiumL.poll();
                System.out.println("Partido no premium largo: "+ nPremLargo1.getPlayerID() + " Vs " +nPremLargo2.getPlayerID()+"\n");
                
            }
            
            numiteracion++;
            System.out.println("=======================================================");
        }

        if (premiumC.size() == 1 ) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos premium cortos.\n");
        }

        if (premiumL.size() == 1) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos premium largos.\n");
        }

        if (noPremiumC.size() == 1 ||noPremiumC.isEmpty()) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos no premium cortos.\n");
        }

        if (noPremiumL.size() == 1 ||noPremiumL.isEmpty()) {
            System.out.println("Ya no quedan peticiones suficientes para crear partidos no premium largos.\n");
        }
        System.out.println("=========================FIN=========================");
    }
    
}
