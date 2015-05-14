/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment02;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 *
 * @author theLennisters
 */
public class Assignment02Main {
    
    public static void main(String[] args){
        AdjacencyListDirectedWeightedGraph graph = null;
        //graph = readGraphFromFile("data/graphPosLittle.txt");
       // graph = readGraphFromFile("data/pdfExample.txt");
        //graph = readGraphFromFile("data/graphTutorialExample.txt");
        graph = readGraphFromFile("data/graphPosMes.txt");
        System.out.println(graph.getNumberOfVertices());
        
        //printGraphEdges(graph);

        
        DijkstraWithStandardList d = new DijkstraWithStandardList(graph, 1);
        
        
        
        for(int i =1; i <= graph.getNumberOfVertices();i++){
            System.out.print("Shortest path to "+i+" : ");
            Iterator<Integer> it = d.getShortestPathTo(i);
            while(it.hasNext()){
                System.out.print(it.next()+" ");
            }
            System.out.println(": cost = "+ d.distance[i]);
        }
    }
    
    public static AdjacencyListDirectedWeightedGraph readGraphFromFile(String file){
        AdjacencyListDirectedWeightedGraph graph = null;
        Scanner in;
        try {
            in = new Scanner(new File(file));
            String line = in.nextLine();
            int size = Integer.parseInt(line);
            graph = new AdjacencyListDirectedWeightedGraph(size);
            //line = in.nextLine();
            while(in.hasNextLine()){
                line = in.nextLine();
                Scanner s = new Scanner(line);
                int v = Integer.parseInt(s.next());
                //System.out.println("VERTEX : "+ v);
                while(s.hasNext()){
                    int w = Integer.parseInt(s.next());
                    int distance = Integer.parseInt(s.next());
                    graph.addEdge(v, w, distance);
                }
                
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        return graph;
    }
    
    public static void printGraphEdges(AdjacencyListDirectedWeightedGraph graph){
        for(int i=1; i<=graph.getNumberOfVertices();i++){
            Iterator<Edge> it1 = graph.getEdges(i);

            while(it1.hasNext()){
                System.out.println(it1.next());
            }
        }
    }
    
}
