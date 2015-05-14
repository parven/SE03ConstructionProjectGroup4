package assignment01;

import java.util.*;

public class SuffixTrieNode {

    SuffixTrieData data = new SuffixTrieData();
    boolean isTerminal = false;
    int numChildren = 0;
    
    /**
    * I chose to use HashMap data structure here, because performing insertion 
    * and search is much faster than TreeMap. Same was practically demonstrated
    * by Dr. Trent Lewis in the App Development lecture.
    */
    Map<Character, SuffixTrieNode> trieNodeMap = new HashMap<Character, SuffixTrieNode>();

    public SuffixTrieNode getChild(char label) {
        if(trieNodeMap.containsKey(label)){
            return trieNodeMap.get(label);
        }
        return null;
    }

    public void addChild(char label, SuffixTrieNode node) {
        trieNodeMap.put(label, node);
        numChildren++;
    }

    public void addData(String startIndex) {
            this.data.addStartIndex(startIndex);
        
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
}
