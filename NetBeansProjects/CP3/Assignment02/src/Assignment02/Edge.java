/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Assignment02;

/**
 *
 * @author theLennisters
 */
public class Edge {
    
    private int from;
    private int to;
    private int weight;
    
    public Edge(int v, int w, int weight){
        this.from = v;
        this.to = w;
        this.weight = weight;
    }
    
    public int  getWeight(){
        return weight;
    }
    
    public int getVertexTo(){
        return to;
    }
    
    @Override
    public String toString(){
        return "From " + from + " to " + to + " with weight "+ weight;
    }
}
