/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package practical2;

/**
 *
 * @author theLennisters
 */
public class ConditionTest implements DogCondition{

    @Override
    public boolean dogCond(Dog dog) {
        if(dog.getName().contains("h")){
            return true;
        }
        return false;
    }
    
}
