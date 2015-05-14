/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author lewi0146
 */
public class People {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Person p = new Person("Trent",1999);
        
        ArrayList<Person> people = new ArrayList<>();
        
        people.add(p);
        people.add(new Person("Bahraeih",2007));
        
        Collections.sort(people);
        System.out.println(people);
        
        ArrayList<String> s = new ArrayList();
        s.add("c");
        s.add("b");
        s.add("z");
        Collections.sort(s);
        System.out.println(s);
        
        
    }
}
