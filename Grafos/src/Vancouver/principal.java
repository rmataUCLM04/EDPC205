package Vancouver;
import java.util.*;
import java.io.*;
import graphsDS_ESI_UCLM_v2.*;

public class principal {
    public static void main(String[] args) {

        Graph<Intersection, Segment> graph = new TreeMapGraph<>(); // Graph<Vertice, Arista>
        File fichero = new File("bikeways.csv");
        CrearGrafo(fichero, graph);
    }

    public static void CrearGrafo(File fichero, Graph<Intersection, Segment> graph) {
        
    }
    
}
