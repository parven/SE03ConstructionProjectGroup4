/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment02;

import java.util.*;

/**
 *
 * @author theLennisters
 */
public class DijkstraWithStandardList {
    AdjacencyListDirectedWeightedGraph graph;
    private int vNum;
    boolean[] known;
    int[] distance;
    int[] predecessor;
    
    public DijkstraWithStandardList(AdjacencyListDirectedWeightedGraph graph, int source){
        this.graph = graph;
        vNum = graph.getNumberOfVertices();
        known = new boolean[vNum+1];
        distance = new int[vNum+1];
        predecessor = new int[vNum+1];
        
        for (int i=1; i<vNum+1; i++){
            known[i] = false;
            distance[i] = Integer.MAX_VALUE;
            predecessor[i] = -1;
        }
        
        distance[source] = 0;
        dijkstra();
    }
    
    private void dijkstra(){
        for (int i=1; i<vNum+1; i++){
                int v = getMinDistanceVertex();
                known[v]=true;
                
                Iterator<Integer> it = graph.getAdjecentVertices(v);
                while(it.hasNext()){
                    int w = it.next();
                    if(!known[w]){
                        if((distance[v]+graph.getWeight(v, w)) < distance[w]){
                            distance[w] = distance[v]+graph.getWeight(v, w);
                            predecessor[w] = v;
                        }
                    }
                }
        }
    }
    
    public int getMinDistanceVertex(){
        int min = Integer.MAX_VALUE;
        int v = Integer.MAX_VALUE;
        for(int i=1; i<distance.length;i++){
            if(distance[i] < min && known[i]==false){
                v = i;
                min = distance[i];
            }
        }
        return v;
    }
    
    public int getPredecessor(int v){
        return predecessor[v];
    }
    
    public Iterator<Integer> getShortestPathTo(int v){
        List<Integer> path = new ArrayList<>();
        path.add(v);
        while(v>-1){
            //System.out.println("SHORTEST");
            if(predecessor[v]!= -1){
                path.add(predecessor[v]);
            }
                v = predecessor[v];
            
        }
        Collections.reverse(path);
        return path.iterator();
    }
    
//    private void dijkstra(){
//        for (int i=1; i<vNum+1; i++){
//            System.out.println("FOR : "+i);
//            if(known[i]==false){
//                int v = getMinDistanceVertex();
//                System.out.println("MINIMUM DISTANCE VERTEX : "+v);
//                known[v]=true;
//                
//                Iterator<Integer> it = graph.getAdjecentVertices(v);
//                while(it.hasNext()){
//                    int w = it.next();
//                    System.out.println("ADJECENT : "+ w);
//                    if(!known[w]){
//                        System.out.println("SHIT");
//                        if((distance[v]+graph.getWeight(v, w)) < distance[w]){
//                            distance[w] = distance[v]+graph.getWeight(v, w);
//                            predecessor[w] = v;
//                            System.out.println("Current"+distance[w]);
//                        }
//                    }
//                }
//            }
//        }
//    }
    
}
