/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package trie;

import java.util.*;

/**
 *
 * @author theLennisters
 */
public class test {
    
    public static void main(String[] args){
        
        char[] str = "a".toCharArray();
        System.out.println(str);
        char[] str1 = Arrays.copyOfRange(str, 1, str.length);
        System.out.println(str1.length);
    }
    
    
    
//    public TrieNode<E> insert(char[] str, E data) {
//        
//        TrieNode<E> n1 = new TrieNode<E>();
//        n1.addData(data);
//        
//        if(str.length > 0){
//            char ch = str[0];
//            char[] str1 = Arrays.copyOfRange(str, 1, str.length);
//            n1.addChild(ch, insert(str1, data));
//            return n1;
//        }
//        
//        return null;
//    }
}
