package assignment01;

import java.util.*;
import java.io.*;

public class SuffixTrie {

    private SuffixTrieNode root = new SuffixTrieNode();

    public SuffixTrieNode insert(String str, String startIndex) {
        return insert(str.toCharArray(), startIndex);
    }

    public SuffixTrieNode insert(char[] str, String startIndex) {
        SuffixTrieNode currentNode = root;

        for (int i = 0; i < str.length; i++) {

            if (i != str.length - 1) {
                if (currentNode.getChild(str[i]) == null) {
                   
                    SuffixTrieNode newNode = new SuffixTrieNode();
                    currentNode.addChild(str[i], newNode);
                    currentNode = newNode;
                    currentNode.addData(startIndex);
                } else {
                   
                    currentNode = currentNode.getChild(str[i]);
                    currentNode.addData(startIndex);
                }
            } else {
                if (currentNode.getChild(str[i]) == null) {
                  
                    SuffixTrieNode lastNode = new SuffixTrieNode();
                    currentNode.addChild(str[i], lastNode);
                    lastNode.isTerminal = true;
                    lastNode.addData(startIndex);
                    return lastNode;
                } else {
                   
                    currentNode.getChild(str[i]).isTerminal = true;
                    currentNode.getChild(str[i]).addData(startIndex);
                    return currentNode.getChild(str[i]);

                }
            }
          

        }
        return null;
    }

    public SuffixTrieNode getNode(String str) {
        return getNode(str.toCharArray());
    }

    public SuffixTrieNode getNode(char[] str) {
        SuffixTrieNode currentNode = root;
        
        for(int i = 0; i< str.length; i++){
            
                if(currentNode.getChild(str[i]) == null){
                    return null;
                }
                else 
                    currentNode = currentNode.getChild(str[i]);
        }
        return currentNode;
    }

    public SuffixTrieNode get(String str) {
        return get(str.toCharArray());
    }

    public SuffixTrieNode get(char[] str) {
        if(getNode(str) != null){    
            SuffixTrieNode lastNode = getNode(str);
            if(lastNode.isTerminal == true){
                return lastNode;
            }
        }
        return null;
    }
    
        public static SuffixTrie readInFromFile(String fileName) {
        
            SuffixTrie t = new SuffixTrie();

            Scanner sc = null;
            //BufferedReader reader = null;
            try {
                    sc = new Scanner(new File(fileName));
                    sc.useDelimiter("[.!?]");
                    int sentenceNum = 0;    //sentenceNum to keep count of the 
                                            //sentences in the text.
                    while (sc.hasNext()) {
                        String nextSentence = sc.next();
                        String tempStr = nextSentence;
                            //System.out.println(tempStr);

                        for(int i=0; i<nextSentence.length(); i++){
                            // inserting everything in lowecase by using 
                            // toLowerCase to make all queries case insensitive.
                            t.insert(tempStr.toLowerCase(), sentenceNum + "-" + i);
                            
                            // removing the first character of the current string
                            //to get the next suffix.
                            tempStr = tempStr.substring(1);
                        }
                        sentenceNum++;
                    }
                    if (sc!= null) {
                        sc.close();
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

//    public static SuffixTrie readInFromFile(String fileName) {
//        
//        SuffixTrie t = new SuffixTrie();
//		
//        BufferedReader reader = null;
//        try {
//                reader = new BufferedReader(new FileReader(new File(fileName)));
//
//                while (reader.ready()) {
//                    String nextLine = reader.readLine();
//                    String tempStr = nextLine;
//                        System.out.println(tempStr);
//                        
//                    for(int i=0; i<nextLine.length(); i++){
//                        System.out.println(i);
//                        t.insert(tempStr, Integer.toString(i));
//                        tempStr = tempStr.substring(1);
//                        System.out.println(tempStr);
//                    }
//                    //input(nextLine);
//                }
//                if (reader!= null) {
//                    reader.close();
//                }
//        }
//        catch (FileNotFoundException fnfe) {
//            fnfe.printStackTrace();
//        }
//        catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        return t;
//    }
}