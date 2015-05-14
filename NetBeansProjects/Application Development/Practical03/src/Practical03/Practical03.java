/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical03;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

/**
 *
 * @author theLennisters
 */
public class Practical03 {
    
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println("Starting Bracket Checker Application");
        
        char[] opening = {'{', '('};
        char[] closing = {'}', ')'};
        BracketChecker bc = new BracketChecker(opening, closing);
        Queue<String> qu = new ArrayDeque<>(); 
        
        //Chk : 17 and 18
//        Scanner in = new Scanner(System.in);
//        String line = in.nextLine();
//        
//        while(!line.isEmpty()){
//            
//            if(bc.check(line)){
//                System.out.println("Syntax Correct");
//            }
//            else{
//                System.out.println("Syntax Error");
//            }
//            
//            line = in.nextLine();
//        }
//        
//        System.out.println("Exiting Checker");
        
        Scanner in = new Scanner(new File("brackets02.txt"));
        String line = in.nextLine();
        
        while(!line.isEmpty()){
            qu.offer(line);
            line = in.nextLine();
        }
        
        while(qu.peek()!= null){
            String s = qu.poll();
            System.out.println(s);
            
            if(bc.check(s)){
                System.out.println("Syntax Correct");
            }
            else{
                for(int i = 0; i<bc.getPosition();i++){
                    System.out.print(" ");
                }
                System.out.println("|");
                
                System.out.println("Syntax Error at position "+bc.getPosition());
            }
        }
        
        System.out.println("Exiting Checker");
        
    }
    
}
