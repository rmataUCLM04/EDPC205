import java.io.*;
import java.util.*;
public class main {
    //compare to para las colas con prioridad
    public static void main(String[] args) {
        
        Queue<Peticion> partidosPremiumLargos = new PriorityBlockingQueue<Peticion>();
        Queue<Peticion> partidosPremium = new PriorityBlockingQueue<Peticion>();
        Queue<Peticion> partidosNoPremiumLargos = new PriorityBlockingQueue<Peticion>();
        Queue<Peticion> partidosNoPremiumCortos = new PriorityBlockingQueue<Peticion>();
         
    }

    public static void procesarFile(){
        Peticion nPet;
        try {
            FicheroSecuencial<Peticion> ft = new FicheroSecuencial<Peticion>("player_requests.csv");
            ft.skip();

            while (!ft.isEndOfFile()) {
                
                ft.read(nPet);     
            }
            ft.close();
            System.out.println(nPet.toString());


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
}
