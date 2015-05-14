package people;
import java.util.*;
import java.util.HashMap;

public class A22 {
    /** Add static methods here if required */
	static String pattern;
        static Map<Character, Integer> map = new HashMap<>();
        static ArrayList<Character> aList = new ArrayList<>();
        
    public static void main(String[] args){
       Scanner input = new Scanner(System.in);
       /* your code goes after this line */
		pattern = input.nextLine();

       for(int i=0; i<pattern.length();i++){
           if(!map.containsKey(pattern.charAt(i))){
               map.put(pattern.charAt(i), 1);
               aList.add(pattern.charAt(i));
           }
           else{
               map.put(pattern.charAt(i), map.get(pattern.charAt(i))+1);
           }
       }
       
       for(int i = 0; i<aList.size();i++){
                Character ch = aList.get(i);
           
                   System.out.println(ch+ " " + map.get(ch));
               
                  
           
           
           
       }

       
       
    } /* end main */

    

} /* end A22 */

/** you may want to declare an additional class here **/

