/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author theLennisters
 */
public class AdjacencyListDirectedGraph implements Graph {
    
private Map<Vertex, List<Vertex>> adjacencyList = new LinkedHashMap<>();
 
    
    @Override
    public void addEdge(String v, String w) {
        addEdge(new GraphVertex(v), new GraphVertex(w));
    }

    @Override
    public void addEdge(Vertex v, Vertex w) {
        if(!adjacencyList.containsKey(v)){
            adjacencyList.put(v, new LinkedList<Vertex>());
        }
        if(!adjacencyList.containsKey(w)){
            adjacencyList.put(w, new LinkedList<Vertex>());
        }
        
        for(Vertex ver : adjacencyList.keySet()){
            if (ver.equals(w)){
                adjacencyList.get(v).add(ver);
            }
        }
        //adjacencyList.get(v).add(w);
        
        //System.out.println(adjacencyList);
        
    }

    @Override
    public Iterable<Vertex> adjacentTo(String v) {
        return adjacentTo(getVertex(v));
    }

    @Override
    public Iterable<Vertex> adjacentTo(Vertex v) {
        List<Vertex> vList = adjacencyList.get(v);
        return vList;
    }

    @Override
    public int degree(String v) {
        return degree(getVertex(v));
    }

    @Override
    public int degree(Vertex v) {
        List<Vertex> vList = adjacencyList.get(v);
        return vList.size();
    }

    @Override
    public Iterable<Vertex> getVertices() {
        return adjacencyList.keySet();
    }

    @Override
    public boolean hasEdge(String v, String w) {
        return hasEdge(getVertex(v), getVertex(w));
    }

    @Override
    public boolean hasEdge(Vertex v, Vertex w) {
        List<Vertex> vList = adjacencyList.get(v);
        
        return vList.contains(w);
    }

    @Override
    public boolean hasVertex(String v) {
        return hasVertex(getVertex(v));
    }

    @Override
    public boolean hasVertex(Vertex vertex) {
        return adjacencyList.containsKey(vertex);
    }

    @Override
    public Vertex getVertex(String v) {
        for(Vertex ver : adjacencyList.keySet()){
            if (ver.getLabel().equals(v)){
                return ver;
            }
        }
        return null;
    }
    
}

