/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package people;

/**
 *
 * @author lewi0146
 */
public class Person implements Comparable<Person> {
    
    private String name;
    private int dob;

    public Person(String name, int dob) {
        this.name = name;
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public int getDob() {
        return dob;
    }

    @Override
    public int compareTo(Person t) {
     
        return this.dob - t.dob;
        
    }
    
    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        sb.append(", ");
        sb.append(dob);
        
        return sb.toString();
        //return name + ", " + dob;
    }
    
    
    
}
