/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment02;

import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author theLennisters
 */
public class AdjacencyListDirectedWeightedGraph {
    
    private List<Edge>[] edges;
    
    public AdjacencyListDirectedWeightedGraph(){
        
    }
    
    /**
     * Initializes graph to indicated size.
     * @param size : Number of vertices in the graph.
     */
    public AdjacencyListDirectedWeightedGraph(int size){
        if(size > 0){
            edges = new ArrayList[size + 1];
            for(int i = 0; i < size+1; i++){
                edges[i] = new ArrayList<Edge>();
            }
        }
        else{
            System.out.println("Invalid number of verties");
        }
    }
    
    public int getNumberOfVertices(){
        return edges.length - 1;
    }
    /**
     * Add edge from vertex v to vertex w with the specified weight.
     * @param v
     * @param w
     * @param weight 
     */
    public void addEdge(int v, int w, int weight){
        //System.out.println(edges.length);
        if(v<1 || w<1 || v >= edges.length || w>=edges.length){
            System.out.println("ERROR : Invalid Vertex");
        }
        else if(hasEdge(v, w)){
            System.out.println("ERROR : Edge already exists.");
        }
        else{
            edges[v].add(new Edge(v, w, weight));
        }
    }
    
    /**
     * 
     * @param v
     * @param w
     * @return 
     */
    public boolean hasEdge(int v, int w){
        return !(getWeight(v, w) == Integer.MAX_VALUE);
    }
    
    /**
     * 
     * @param v
     * @param w
     * @return 
     */
    public int getWeight(int v, int w){
        if(v<1 || w<1 || v>=edges.length || w>=edges.length){
            return Integer.MAX_VALUE;
        }
        else{
            if(getEdges(v) != null){
                Iterator<Edge> it = getEdges(v);
                while(it.hasNext()){
                    Edge e = it.next();
                    if(e.getVertexTo() == w){
                        return e.getWeight();
                    }
                }
            }
            return Integer.MAX_VALUE;
        }
    }
    
    /**
     * 
     * @param v
     * @return 
     */
    public Iterator<Edge> getEdges(int v){
        //System.out.println(v);
        if(v<1 || v>= edges.length){
            return null;
        }
        else{
            //List e = edges[v];
            //System.out.println(e);
            return edges[v].iterator();
        }
    }
    
    public Iterator<Integer> getAdjecentVertices(int v){
        //System.out.println(v);
        if(v<1 || v>= edges.length){
            return null;
        }
        else{
            ArrayList<Integer> list = new ArrayList<>();
            Iterator<Edge> it = getEdges(v);
            while(it.hasNext()){
                list.add(it.next().getVertexTo());
            }
            return list.iterator();
        }
    }
    
    public String toString() {
    String res = "Edges:\n";
    Iterator<Edge> iter;
    Edge e;
    boolean first;
    for (int i = 0; i < edges.length; i++) {
      res += "  from " + i + " to: [";
      iter = getEdges(i);
      first = true;
      while (iter.hasNext()) { 
        e = iter.next();
        if (first) first = false;
        else {
          res += ", ";
        }
        res += e.getVertexTo() + " weight: " + e.getWeight();
      }
      res += "]\n";
      }
      return res;
    }
    
    public AdjacencyListDirectedWeightedGraph readGraphFromFile(String file){
        AdjacencyListDirectedWeightedGraph graph = null;
        Scanner in;
        try {
            in = new Scanner(new File(file));
            String line = in.nextLine();
            int size = Integer.parseInt(line);
            graph = new AdjacencyListDirectedWeightedGraph(size);
            line = in.nextLine();
            while(in.hasNextLine()){
                Scanner s = new Scanner(line);
                int v = Integer.parseInt(s.next());
                while(s.hasNext()){
                    int w = Integer.parseInt(s.next());
                    int distance = Integer.parseInt(s.next());
                    graph.addEdge(v, w, distance);
                }
                line = in.nextLine();
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File Not Found");
        }
        return graph;
    }
    
}
