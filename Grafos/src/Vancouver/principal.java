package Vancouver;

import java.util.*;
import java.io.*;
import graphsDS_ESI_UCLM_v2.*;

public class principal {
    public static void main(String[] args) {

        Graph<String, Segment> graph = new TreeMapGraph<>(); // Graph<Vertice, Arista>
        File fichero = new File("bikeways.csv");
        CrearGrafo(fichero, graph);
    }

    public static void CrearGrafo(File fichero, Graph<String, Segment> graph) {

        FicheroSecuencial<Segment> fs = null;

        try {
            fs = new FicheroSecuencial<>(fichero.getPath(), ";");

            while (!fs.isEndOfFile()) {

                Segment seg = new Segment();
                fs.read(seg);

                String origin = seg.getOriginId();
                String dest = seg.getDestinationId();

                Vertex<String> vO = new Vertex<>(origin);
                Vertex<String> vD = new Vertex<>(dest);

                if (!graph.containsVertex(vO)) graph.insertVertex(vO);
                if (!graph.containsVertex(vD)) graph.insertVertex(vD);

                Edge<Segment> e = new Edge<>(vO, vD, seg);
                graph.insertEdge(e);
            }

        } catch (Exception e) {
            System.err.println("Error: " + e.getMessage());
        } finally {
            if (fs != null) fs.close();
        }
    }
}
