/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical2;

import java.util.*;
import static practical2.Dog.printTotal;

/**
 *
 * @author theLennisters
 */
public class DogBreeder {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        System.out.println("Starting Dog Application");
//        
//   ChkPoints 9 and 10     
//        Dog myDog = new Dog();
//        System.out.println(myDog.toString());
//        
//        myDog.setRegNum(3946);
//        myDog.setDogBreed("Poodle");
//        myDog.setName("Fluffy");
//        
//        System.out.println(myDog.toString());

//   ChkPoints 11, 12 and 13
        System.out.println(printTotal());
        Dog myDog1 = new Dog();
        System.out.println(myDog1.toString());
        
        Dog myDog2 = new Dog("Fluffy", "Poodle", 3496);
        System.out.println(myDog2.toString());
        
        myDog1.setName("Fido");
        myDog1.addOwners("Anne");
        myDog1.addOwners("Bob");
        
        myDog2.addOwners("Martha");
        
        System.out.println(myDog1.toString());
        
        System.out.println(myDog1.getName());
        System.out.println(myDog2.getName());
        
        System.out.println(printTotal());
        
//creating more dogs       
        Dog myDog3 = new Dog("Pluto", "Poodle", 3489);
        myDog3.addOwners("Any");
        Dog myDog4 = new Dog("Rihno", "Poodle", 6459);
        myDog4.addOwners("rollingStones");
        myDog4.addOwners("Rihanna");
        Dog myDog5 = new Dog("King", "Lebra", 8559);
        myDog5.addOwners("JayZ");
        myDog5.addOwners("Beyonce");
        Dog myDog6 = new Dog("Rex", "Lebra", 2784);
        myDog6.addOwners("Sam");
        
//chkPoints 14, 15
        
        System.out.println("***********\nCheckpoint 14");
        DogRegister dogReg = new DogRegister();
        dogReg.addDog(myDog1);
        dogReg.addDog(myDog2);
        dogReg.addDog(myDog3);
        dogReg.addDog(myDog4);
        dogReg.addDog(myDog5);
        dogReg.addDog(myDog6);
        
        Dog tempDog = dogReg.getDog(2);
        System.out.println(tempDog.toString());
        
        System.out.println(dogReg.toString());
        
        System.out.println("***********\nCheckpoint 15");
        tempDog = dogReg.deleteDog(2);
        System.out.println("deleted Dog is : ");
        System.out.println(tempDog.toString());
        System.out.println("after deleting a Dog form the list");
        System.out.println(dogReg.toString());
        
        System.out.println("name contains");
        Collection<Dog> dogColl = dogReg.getDogsWhoseNameContains("o");
        System.out.println(dogColl.toString());
        
        System.out.println("chkpoint 16");
        dogReg.GroupByBreed();
        System.out.println(dogReg.toString());
        
        ConditionTest ct = new ConditionTest();
        boolean condition = ct.dogCond(myDog6);
        System.out.println(condition);
        
        
    }
    
}
