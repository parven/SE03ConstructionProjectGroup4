/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical03Patient;

import java.util.*;

/**
 *
 * @author theLennisters
 */
public class Practical03Patient {
    
    public static void main(String[] args){
        //ArrayList<Patient> pQ = new ArrayList<>();
        Queue<Patient> pQ = new PriorityQueue<>();
        Scanner in = new Scanner(System.in);
        String line = in.nextLine();
        
        while(!line.equalsIgnoreCase("QUIT")){
            
            if(line.equalsIgnoreCase("process")){
                //Collections.sort(pQ);
               
                while(pQ.peek() != null){
                    System.out.println(pQ.poll());
                }
            }
            else{
                String name;
                int severity;
                double time;
                Scanner scan = new Scanner(line);
                name = scan.next();
                severity = scan.nextInt();
                time = scan.nextDouble();

                try{
                    pQ.add(new Patient(name, severity, time));
                }
                catch (PatientException pe){
                    System.out.println("Severity is not within 1-10");
                }
            }
            line = in.nextLine();
        } 
    }  
}
