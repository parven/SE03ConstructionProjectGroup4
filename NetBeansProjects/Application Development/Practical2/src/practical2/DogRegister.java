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
public class DogRegister {
    
    private List<Dog> dogList = new ArrayList<Dog>();
    
    public void addDog(Dog dog){
        dogList.add(dog);
    }
    
    public Dog getDog(int dogSeqNum){
       Dog dog = null;
        
       if (dogList != null) {
            Iterator<Dog> itD =  dogList.iterator();
            
            for(int i=0; i<dogList.size(); i++){
                dog = itD.next();
                if(dog.getDogSeqNum() == dogSeqNum){
                    return dog;
                }
            }
        }
        return null;
    }
    
    public Dog deleteDog(int dogSeqNum){
       Dog dog = null;
        
       if (dogList != null) {
            Iterator<Dog> itD =  dogList.iterator();
            
            for(int i=0; i<dogList.size(); i++){
                dog = itD.next();
                if(dog.getDogSeqNum() == dogSeqNum){
                    int index = dogList.lastIndexOf(dog);
                    //System.out.println("index is : "+ index);
                    dogList.remove(index);
                    return dog;
                    
                }
            }
        }
        return null;
    }
    

    public Collection<Dog> getDogsWhoseNameContains(String charSequence){
        Dog dog = null;
        List<Dog> tempDogList = new ArrayList<Dog>();
        
       if (dogList != null) {
            Iterator<Dog> itD =  dogList.iterator();
            
            for(int i=0; i<dogList.size(); i++){
                dog = itD.next();
                if(dog.getName().toLowerCase().contains(charSequence.toLowerCase())){
                    tempDogList.add(dog);
                    
                }
            }
            return tempDogList;
        }
        return null;
    }
    
    public void GroupByBreed(){
        Collections.sort(dogList, new Dog());
    }
    
//    public Collection<Dog> getByCondition(DogCondition c){
//        Collection<Dog> newDogColl= new ArrayList<Dog>();
//        ConditionTest ct = new ConditionTest();
//        ct.dogCond(null);
//        return newDogColl;
//    }
    
    public String toString(){
        Dog dog = null;
        
        if (dogList != null) {
            Iterator<Dog> itD =  dogList.iterator();
            StringBuilder sb = new StringBuilder();
            
            for(int i=0; i<dogList.size(); i++){
                dog = itD.next();
                
                sb.append("Dog ");
                sb.append(dog.getName());
                sb.append(", Breed = ");
                sb.append(dog.getDogBreed());
                sb.append(", #owners = ");
                sb.append(dog.getOwners().size());
                sb.append(", Registration # = ");
                sb.append(dog.getRegNum());
                sb.append(", Sequence # = ");
                sb.append(dog.getDogSeqNum());
                sb.append("\n");
        
                }
            return sb.toString();
            }
        return null;
        }

        
}

