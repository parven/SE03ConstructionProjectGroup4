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
public class StringMatcherBruteForce extends StringMatcher {
    String target;
    String pattern;
    int targetLen;
    int patternLen;
    int steps;
    
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
        steps = 0;
        for(int i=0; i <= targetLen - patternLen; i++){
            int j = 0;
            while ((j < patternLen) && (target.charAt(i + j) == pattern.charAt(j))){
                j++;
                steps++;
            }
            steps++;
            if (j == patternLen){
                return i;
            }
            
        }
        return -1;
    }

    @Override
    public int getNumberOfSteps() {
        return steps;
    }
    
}
