/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package exactstringmatching;

import java.util.Arrays;

/**
 *
 * @author theLennisters
 * https://weblogs.java.net/blog/potty/archive/2012/05/21/string-searching-algorithms-part-iii
 * http://algs4.cs.princeton.edu/53substring/BoyerMoore.java.html
 */
public class StringMatcherBM extends StringMatcher {
    String target;
    String pattern;
    int targetLen;
    int patternLen;
    int steps;
    int [] occurence = new int[256];
    
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
        lastOccurenceFunction();
        int skip;
        steps = 0;
        
        for(int i = 0; i <= targetLen - patternLen; i += skip){
            skip = 0;
            for(int j = patternLen - 1; j >= 0; j--){
                if(pattern.charAt(j) != target.charAt(i+j)){    //checking to see if there is a missmatch
                    skip = Math.max(1, j - occurence[target.charAt(i+j)]);
                    break;
                }
                steps++;
            }
            if(skip == 0)
                return i;
            
            steps++;
        }
        return -1;
    }

    @Override
    public int getNumberOfSteps() {
        return steps;
    }
    
    public void lastOccurenceFunction(){
        for(int k = 0; k < 256; k++)
            occurence[k] = -1;
        
        for(int p = 0; p < patternLen; p++)
            occurence[pattern.charAt(p)] = p;
        
    }
    
}
