/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical2;

import java.util.*;

/**
 *
 * @author theLennisters
 */
public class Dog implements Comparator<Dog> {

//class variables
    private static int dogCounter = 0;
    
//instance variables
    private int regNum = -1;
    private String dogBreed = "unknown";
    private String name = "unknown";
    private Set<String> owners =  new HashSet<String>(); //HashSet offers constant time performance for the basic operations
    private int dogSeqNum = 0;
    
//getters
    public int getRegNum() {
        return regNum;
    }

    public String getDogBreed() {
        return dogBreed;
    }

    public String getName() {
        return name;
    }
    
    public Set getOwners(){
        return owners;
    }
    
    public int getDogSeqNum() {
        return dogSeqNum;
    }
    
    public Set getOwners(String owner){
        return owners;
    }


//setters
    public void setRegNum(int regNum) {
        this.regNum = regNum;
    }

    public void setDogBreed(String dogBreed) {
        this.dogBreed = dogBreed;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addOwners(String owner){
        owners.add(owner);
    }
   
//constructors
    public Dog(String name, String breed, int regNum){
        this.name = name;
        this.dogBreed = breed;
        this.regNum = regNum;
        
        dogCounter ++;
        dogSeqNum = dogCounter;
    }
    
    public Dog(){
        dogCounter ++;
        dogSeqNum = dogCounter;
    }

    
//methods
    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Dog ");
        sb.append(name);
        sb.append(", Breed = ");
        sb.append(dogBreed);
        sb.append(", #owners = ");
        sb.append(owners.size());
        sb.append(", Registration # = ");
        sb.append(regNum);
        sb.append(", Sequence # = ");
        sb.append(dogSeqNum);
        
        return sb.toString();
    }
    
    public static String printTotal(){
        return "Total number of Dogs bred = " + dogCounter;
    }



    @Override
    public int compare(Dog o1, Dog o2) {
        return o1.dogBreed.compareTo(o2.getDogBreed());
    }
}
