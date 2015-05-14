/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical2;

import java.util.*;
import java.util.Scanner;

/**
 *
 * @author theLennisters
 */
public class test02 {
     public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       /* read line of input and store it in s1 */
       String st = input.nextLine() ;
       /* your code goes after this line */
       	char[] ch = st.toCharArray();
        ArrayList<Character> list = new ArrayList<>();
	
        Map<Character, Integer> map = new HashMap<>();
        
        for(int i=0; i<ch.length; i++){
            
            if(!map.containsKey(ch[i])){
                map.put(ch[i], 1);
                list.add(ch[i]);
            }
        }
        
        for(int j=0; j<list.size();j++){
            System.out.print(list.get(j));
        }

  }
    
}
