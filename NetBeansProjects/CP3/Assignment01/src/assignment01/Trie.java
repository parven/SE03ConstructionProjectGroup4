package assignment01;

import java.util.*;
import java.io.*;

public class Trie<E> {

    private TrieNode<E> root = new TrieNode<E>();
    List<String> list = null;
    int count = 0;

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
     * in the trie, but not as a whole word.
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
     * in the trie, but not as a whole word.
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
     * whole word.
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
        
        try {
            Set<Character> keySet = lastNode.trieNodeMap.keySet();

            Iterator<Character> itD =  keySet.iterator();

            for(int i=0; i<keySet.size(); i++){
                char ch = itD.next();

                if(get(prefix+ch)!= null){
                    list.add(prefix + ch);
                }
                TrieNode tempNode = (TrieNode)lastNode.trieNodeMap.get(ch);
                if(!tempNode.isTerminal==true || !tempNode.trieNodeMap.isEmpty()){
                alphabeticalListWithPrefixRecursion(tempNode,prefix + ch);
                }
            }
        }
        catch(NullPointerException e){
        }
        return list;
        
    }
    
    public void alphabeticalListWithPrefixRecursion(TrieNode node, String prefix){
        Set<Character> keySet = node.trieNodeMap.keySet();
        Iterator<Character> itD = keySet.iterator();
        
        for(int i=0; i<keySet.size(); i++){
            char ch = itD.next();
            
            if(get(prefix+ch)!= null){
                list.add(prefix + ch);
                //Collections.sort(list);
            }
            TrieNode tempNode = (TrieNode)node.trieNodeMap.get(ch);
            if(!tempNode.isTerminal==true || !tempNode.trieNodeMap.isEmpty()){
            alphabeticalListWithPrefixRecursion(tempNode,prefix + ch);
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
        
        List<String> wordList = getAlphabeticalListWithPrefix(prefix);
        List<TrieNode> list = new ArrayList<>();

        //Using a for-loop to get the nodes into a new "list" using the "get()"
        //function of this class for the strings in "wordList"
        for(int i=0; i < wordList.size(); i++){
            list.add(get(wordList.get(i)));
        }

        //Sorting the list in increasing order of "rank", because higher the
        //rank(1 being heigher than 2) higher the frequency.
        Collections.sort(list);

//        for(int i=0; i<list.size();i++){
//            System.out.println(list.get(i).getData().getWord() + " " + list.get(i).getData().getFrequency());
//        }

        //following try block will handle the IndexOutOfBoundsException at
        //the run time.
        try{
            //Using StringBuilder to remove the "prefix" from the most frequent word.
            StringBuilder suffix = new StringBuilder(list.get(0).getData().getWord());
            int prefixLength = prefix.length();
            suffix.delete(0, prefixLength);

            return prefix + suffix.toString();
        }
        catch(IndexOutOfBoundsException e){
        }
        return "";
    }

    /**
     * Reads in a dictionary from file and places all words into the trie.
     *
     * @param fileName the file to read from
     * @return the trie containing all the words
     */
    public static Trie<TrieData> readInDictionary(String fileName) {
        Trie<TrieData> t = new Trie<TrieData>();
        BufferedReader reader = null;
        try {
                reader = new BufferedReader(new FileReader(new File(fileName)));

                while (reader.ready()) {
                        String nextLine = reader.readLine();
                        TrieData data = makeDataObjectFromLine(nextLine);
                        //System.out.print ("Line = "+data.getRank()+data.getWord()+data.getFrequency() + "\n");
                        // TODO: call insert() here to insert the data object into the dictionary! 
                        t.insert(data.getWord(), data);
                }
                if (reader!= null) {
                        reader.close();
                }
        }
        catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
        }
        catch (IOException ioe) {
                ioe.printStackTrace();
        }
        return t;
    }
    
    /**
     * makes a data object of type TrieData containing the word and information
     * associated with it, such as its rank and frequency.
     * 
     * @param line is the line of text.
     * 
     * @return data is the data object of type TrieData
     */
    private static TrieData makeDataObjectFromLine(String line) {
        TrieData data = new TrieData();
        Scanner sc = new Scanner(line);
        ArrayList<String> al = new ArrayList<>();

        while(sc.hasNext()){ 
            al.add(sc.next());
        }
        data.setRank(Integer.parseInt(al.get(0)));
        data.setWord(al.get(1));
        data.setFrequency(Integer.parseInt(al.get(2)));

        return data;
    }
}
