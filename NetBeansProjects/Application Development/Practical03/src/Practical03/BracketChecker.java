/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical03;

import java.util.*;

/**
 *
 * @author theLennisters
 */
public class BracketChecker {
    
    private Stack<Character> stack = new Stack<>();
    private int position;
    private char[] opening;
    private char[] closing;
    
    public BracketChecker(char[] opening, char[] closing){
        this.opening = opening;
        this.closing = closing;
//        opening[0] = '{';
//        opening[1] = '(';
//        opening[2] = '[';
//        
//        closing[0] = '}';
//        closing[1] = ')';
//        closing[2] = ']';
    }
    
    public int getPosition(){
        return position;
    }
    
    boolean check(String text){
        
        for(int i=0; i<text.length();i++){
            char ch = text.charAt(i);
            if(isOpening(ch)>-1){
                stack.push(ch);
            }
            else if(isClosing(ch)>-1){
                if(stack.empty()){
                    position = i;
                    return false;
                }
                else{
                    int index = isClosing(ch);
                    char top = stack.peek();
                    if(top!=this.opening[index])
                    {
                        //ERROR, not matching brackets
                        position = i;
                        return false;
                    }
                    stack.pop();
                }
            }
        }
        
        //in case of error, if position in the string is required to be
        //sent, send the last position in the string.
        if(stack.empty()){
            return true;
        }
        else{
            stack.clear();
            return false;
        }
    }
    
    /**
     * Determine if the parameter 'in' is in the opening array and returns
     * the position if found.
     * @param in
     * @return the position in the array if found, -1 otherwise.
     */
    private int isOpening(char in){
        for (int i = 0; i<opening.length; i++){
            if(in == opening[i]){
                return i;
            }
        }
        return -1;
    }
    
    /**
     * Determine if the parameter 'in' is in the closing array and returns
     * the position if found.
     * @param in
     * @return the position in the array if found, -1 otherwise.
     */
    private int isClosing(char in){
        for (int i = 0; i<closing.length; i++){
            if(in == closing[i]){
                return i;
            }
        }
        return -1;
    }
    
}
