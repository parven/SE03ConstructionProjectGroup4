package trie;

import java.util.*;

public class Trie<E> {

    private TrieNode<E> root = new TrieNode<E>();
    List<String> list = null;

    /**
     * Inserts a string into the trie and returns the last node that was
     * inserted. This is a convenience method that calls insert(char[], E).
     *
     * @param str The string to insert into the trie
     * @param data	The data associated with the string
     * @return The last node that was inserted into the trie
     */
    public TrieNode<E> insert(String str, E data) {
        return insert(str.toCharArray(), data);
    }

    /**
     * Inserts a string into the trie and returns the last node that was
     * inserted.
     *
     * @param str The string to insert into the trie, as an array of characters
     * @param data	The data associated with the string
     * @return The last node that was inserted into the trie
     */
    public TrieNode<E> insert(char[] str, E data) {
        
        TrieNode<E> currentNode = root;

        for (int i = 0; i < str.length; i++) {

            if (i != str.length - 1) {
                if (currentNode.getChild(str[i]) == null) {
                   
                    TrieNode<E> newNode = new TrieNode<E>();
                    currentNode.addChild(str[i], newNode);
                    currentNode = newNode;
                } else {
                   
                    currentNode = currentNode.getChild(str[i]);
                }
            } else {
                if (currentNode.getChild(str[i]) == null) {
                  
                    TrieNode<E> lastNode = new TrieNode<E>();
                    currentNode.addChild(str[i], lastNode);
                    lastNode.isTerminal = true;
                    lastNode.addData(data);
                    return lastNode;
                } else {
                   
                    currentNode.getChild(str[i]).isTerminal = true;
                    currentNode.getChild(str[i]).addData(data);
                    return currentNode.getChild(str[i]);

                }
            }
          

        }
        return null;
    }

    /**
     * Search for a particular prefix in the trie, and return the final node in
     * the path from root to the end of the string, i.e. the node corresponding
     * to the final character. getNode() differs from get() in that getNode()
     * searches for any prefix starting from the root, and returns the node
     * corresponding to the final character of the prefix, whereas get() will
     * search for a whole word only and will return null if it finds the pattern
     * in the trie, but not as a whole word.  A "whole word" is a path in the 
     * trie that has an ending node that is a terminal node.
     *
     * This is a convenience method around getNode(char[]).
     *
     * @param str The string to search for
     * @return the final node in the path from root to the end of the prefix, or
     * null if prefix is not found
     */
    public TrieNode<E> getNode(String str) {
        return getNode(str.toCharArray());
    }

    /**
     * Search for a particular prefix in the trie, and return the final node in
     * the path from root to the end of the string, i.e. the node corresponding
     * to the final character. getNode() differs from get() in that getNode()
     * searches for any prefix starting from the root, and returns the node
     * corresponding to the final character of the prefix, whereas get() will
     * search for a whole word only and will return null if it finds the pattern
     * in the trie, but not as a whole word.  A "whole word" is a path in the 
     * trie that has an ending node that is a terminal node.
     *
     * @param str The string to search for, as an array of characters
     * @return the final node in the path from root to the end of the prefix, or
     * null if prefix is not found
     */
    public TrieNode<E> getNode(char[] str) {
        TrieNode<E> currentNode = root;
        
        for(int i = 0; i< str.length; i++){
            
                if(currentNode.getChild(str[i]) == null){
                    return null;
                }
                else 
                    currentNode = currentNode.getChild(str[i]);
        }
        return currentNode;
    }

    /**
     * Searches for a word in the trie, and returns the final node in the search
     * sequence from the root, i.e. the node corresponding to the final
     * character in the word.
     *
     * getNode() differs from get() in that getNode() searches for any prefix
     * starting from the root, and returns the node corresponding to the final
     * character of the prefix, whereas get() will search for a whole word only
     * and will return null if it finds the pattern in the trie, but not as a
     * whole word. A "whole word" is a path in the 
     * trie that has an ending node that is a terminal node.
     *
     * This is a convenience method around get(char[]).
     *
     * @param str The word to search for
     * @return The node corresponding to the final character in the word, or
     * null if word is not found
     */
    public TrieNode<E> get(String str) {
        return get(str.toCharArray());
    }

    /**
     * Searches for a word in the trie, and returns the final node in the search
     * sequence from the root, i.e. the node corresponding to the final
     * character in the word.
     *
     * getNode() differs from get() in that getNode() searches for any prefix
     * starting from the root, and returns the node corresponding to the final
     * character of the prefix, whereas get() will search for a whole word only
     * and will return null if it finds the pattern in the trie, but not as a
     * whole word. A "whole word" is a path in the 
     * trie that has an ending node that is a terminal node.
     *
     * @param str The word to search for, as an array of characters
     * @return The node corresponding to the final character in the word, or
     * null if word is not found
     */
    public TrieNode<E> get(char[] str) {
        
        // hint use getNode to find the end node and then check to see if it is
        // not null and a terminal
        if(getNode(str) != null){    
            TrieNode<E> lastNode = getNode(str);
            if(lastNode.isTerminal == true){
                return lastNode;
            }
        }
        return null;
    }

    /**
     * Retrieve from the trie an alphabetically sorted list of all words
     * beginning with a particular prefix.
     *
     * @param prefix The prefix with which all words start.
     * @return The list of words beginning with the prefix, or an empty list if
     * the prefix was not found.
     */
    public List<String> getAlphabeticalListWithPrefix(String prefix) {
        list = new ArrayList<>();
        TrieNode<E> lastNode = getNode(prefix);
        if(get(prefix)!= null){
                    list.add(prefix);
                }
        Set<Character> keySet = lastNode.trieNodeMap.keySet();
        Iterator<Character> itD =  keySet.iterator();
        
            for(int i=0; i<keySet.size(); i++){
                char ch = itD.next();
                
                if(get(prefix+ch)!= null){
                    list.add(prefix + ch);
                }
                TrieNode tempNode = (TrieNode)lastNode.trieNodeMap.get(ch);
                if(!tempNode.isTerminal==true || !tempNode.trieNodeMap.isEmpty()){
                somthing(tempNode,prefix + ch);
            }
        }
        
//        while(lastNode.trieNodeMap.keySet() != null){
//            
//            Set<Character> keySet = lastNode.trieNodeMap.keySet();
//            
//            Iterator<Character> itD =  keySet.iterator();
//            System.out.println(keySet);
//            for(int i=0; i<keySet.size(); i++){
//                    char ch = itD.next();
//                    char[] charArray = Arrays.copyOf(prefix.toCharArray(), prefix.toCharArray().length + 1);
//                    charArray[charArray.length - 1] = ch;
//                    
//                    if(get(charArray)!= null){
//                        System.out.print("null executed");
//                        charArray = Arrays.copyOf(prefix.toCharArray(), prefix.toCharArray().length + 1);
//                        charArray[charArray.length - 1] = ch;
//                        System.out.println(new String(charArray));
//                        list.add(new String(charArray));
//                    }
//                    if(get(charArray)==null ){  //|| !node1.trieNodeMap.isEmpty())
//                            somthing(lastNode,new String(charArray));
//                    }
//            }
//            
//        }
        
        
//        TrieNode<E> lastNode = getNode(prefix);
//        
//        while(lastNode.trieNodeMap.keySet() != null){
//            
//            Set<Character> keySet = lastNode.trieNodeMap.keySet();
//            
//            Iterator<Character> itD =  keySet.iterator();
//            for(int i=0; i<keySet.size(); i++){
//                    char ch = itD.next();
//                    char[] charArray = Arrays.copyOf(prefix.toCharArray(), prefix.toCharArray().length + 1);
//                    charArray[charArray.length - 1] = ch;
//                    
//                    if(get(charArray)!= null){
//                        list.add(new String(charArray));
//                    }
//                    else{
//                        System.out.println(new String(charArray));
//                        getAlphabeticalListWithPrefix(new String(charArray));
//                    }
//            }
//            
//        }
        return list;
    }
    
    public void somthing(TrieNode node, String prefix){
        Set<Character> keySet = node.trieNodeMap.keySet();
        Iterator<Character> itD = keySet.iterator();
        
        for(int i=0; i<keySet.size(); i++){
            char ch = itD.next();
            
            if(get(prefix+ch)!= null){
                list.add(prefix + ch);
                Collections.sort(list);
            }
            TrieNode tempNode = (TrieNode)node.trieNodeMap.get(ch);
            if(!tempNode.isTerminal==true || !tempNode.trieNodeMap.isEmpty()){
            somthing(tempNode,prefix + ch);
            }
        }
    }
        
        
              
    

    /**
     * NOTE: TO BE IMPLEMENTED IN ASSIGNMENT 1 Finds the most frequently
     * occurring word represented in the trie (according to the dictionary file)
     * that begins with the provided prefix.
     *
     * @param prefix The prefix to search for
     * @return The most frequent word that starts with prefix
     */
    public String getMostFrequentWordWithPrefix(String prefix) {
        return prefix + "bogus";
    }

    /**
     * Reads in a dictionary from file and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    public static Trie<TrieData> readInDictionary(String fileName) {
        return null;
    }
}
