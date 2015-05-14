/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.util.Objects;

/**
 *
 * @author theLennisters
 */
public class GraphVertex implements Vertex, Comparable<Vertex> {
    
    String label;
    String colour;
    
    GraphVertex(String label){
        this.label = label;
        //colour = "white";
    }

    @Override
    public String getLabel() {
        return label;
    }

    @Override
    public boolean isUndiscovered() {
        if(this.colour.equalsIgnoreCase("white")){
            return true;
        }
        return false;
    }

    @Override
    public void setToDiscovered() {
        this.colour = "lightblue";
    }

    @Override
    public void setToFinished() {
        this.colour = "darkblue";
    }

    @Override
    public void setToUndiscovered() {
        this.colour = "white";
    }
    
    @Override
    public String toString(){
        return label;
    }

    @Override
    public int compareTo(Vertex o) {
        return this.label.compareTo(o.getLabel());
    }
    
    @Override
    public boolean equals(Object o){
        GraphVertex ver = (GraphVertex) o;
        return this.label.equals(ver.label);
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.label);
        return hash;
    }
}
