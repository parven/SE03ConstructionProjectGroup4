/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Practical03Patient;

import java.text.DecimalFormat;

/**
 *
 * @author theLennisters
 */
public class Patient implements Comparable<Patient>{
    private String name;
    private int severity;
    private double time;

    public void setName(String name) {
        this.name = name;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }

    public void setTime(double time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getSeverity() {
        return severity;
    }

    public double getTime() {
        return time;
    }

    public Patient(String name, int severity, double time) throws PatientException {
        if(severity >=0 && severity <=10){
            this.name = name;
            this.severity = severity;
            this.time = time;
        }
        else{
            throw new PatientException();
        }
    }
    
    
    
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        
        sb.append("Name : ");
        sb.append(name);
        sb.append(", Severity : ");
        sb.append(severity);
        sb.append(", Time of Arrival : ");
        sb.append(time);
        sb.append("\n");
        
        return sb.toString();
    }

    @Override
    public int compareTo(Patient o) {
//        int diff = o.severity - this.severity;
//        //System.out.println("FIRST : "+ diff);
//        if(diff==0){
//            //double diff02 = this.time - o.time;
//            if(this.time < o.time){
//                return -1;
//            }
//            else{
//                return 1;
//            }
//
//        }
//        return diff;
        if(o.severity ==  this.severity){
            if(o.time > this.time){
                return -1;
            }
            else{
                return 1;
            }
            
        }
        else{
            return o.severity - this.severity;
        }
    }
}
