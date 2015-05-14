/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exactstringmatching;

/**
 *
 * @author theLennisters
 */
public class StringMatcherKMP extends StringMatcher {

    String target;
    String pattern;
    int targetLen;
    int patternLen;
    int steps;
    //int [] occurence = new int[256];
    int[] failTable;
    
    @Override
    public void setTarget(String target) {
        this.target = target;
        this.targetLen = this.target.length();
    }

    @Override
    public void setPattern(String pattern) {
       this.pattern = pattern;
       this.patternLen = this.pattern.length();
    }

    @Override
    public int match() {
        failTableFunction();
        int i = 0; 
        int j = 0;
        steps = 0;
        
        while(i < targetLen){
            if(pattern.charAt(j) == target.charAt(i)){
                steps++;
                if(j == patternLen-1){
                    return i - patternLen + 1;
                }
                i = i+1;
                j = j+1;
            }
            else if(j > 0){
                steps++;
                j = failTable[j-1];
            }
            else{
                steps++;
                i = i+1;
            }
        }
        
        return -1;
    }

    @Override
    public int getNumberOfSteps() {
        return steps;
    }
    
    public void failTableFunction(){
        failTable = new int[patternLen];
        failTable[0] = 0;
        int i = 1;
        int j = 0;
        
        while(i < patternLen){
            if(pattern.charAt(i) == pattern.charAt(j)){
                failTable[i] = j+1;
                i = i+1;
                j = j+1;
            }
            else if(j > 0){
                j = failTable[j-1];
            }
            else{
                failTable[i] = 0;
                i = i+1;
            }
        }
    }
    
}
