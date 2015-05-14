package assignment01;

import java.util.Map;
import java.util.*;

public class TrieNode<F> implements Comparable<TrieNode> {

    F data = null;
    boolean isTerminal = false;
    int numChildren = 0;
    
    /**
    * I chose to use HashMap data structure here, because performing insertion 
    * and search is much faster than TreeMap. Same was practically demonstrated
    * by Dr. Trent Lewis in the App Development lecture.
    */
    Map<Character, TrieNode<F>> trieNodeMap = new HashMap<Character, TrieNode<F>>();
    //TrieData data = new TrieData();

    /**
     * Lookup a child node of the current node that is associated with a
     * particular character label.
     *
     * @param label The label to search for
     * @return The child node associated with the provided label
     */
    public TrieNode<F> getChild(char label) {
        if(trieNodeMap.containsKey(label)){
            return trieNodeMap.get(label);
        }
        return null;
    }

    /**
     * Add a child node to the current node, and associate it with the provided
     * label.
     *
     * @param label The character label to associate the new child node with
     * @param node The new child node that is to be attached to the current node
     */
    public void addChild(char label, TrieNode<F> node) {
        trieNodeMap.put(label, node);
        numChildren++;
    }

    /**
     * Add a new data object to the node.
     *
     * @param dataObject The data object to be added to the node.
     */
    public void addData(F dataObject) {
        this.data = dataObject;
    }
    
    /**
     * get a data object.
     *
     * @return the dataObject
     */
    public TrieData getData(){
        TrieData dataObject = (TrieData) this.data;
        return dataObject;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("TrieNode: isTerminal = ");
        sb.append(isTerminal);
        sb.append(", data = ");
        sb.append(data);
        sb.append(", #Children = ");
        sb.append(numChildren);
        
        return sb.toString();
    }

    @Override
    public int compareTo(TrieNode o) {
       return getData().getRank() - o.getData().getRank();
    }


}
