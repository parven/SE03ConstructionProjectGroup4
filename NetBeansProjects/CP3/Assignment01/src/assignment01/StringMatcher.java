/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment01;

/**
 *
 * @author lewi0146
 */
public abstract class StringMatcher {
    
    private double timeToSetTarget;
    private double timeToSetPattern;
    private double timeToCreateTable;
    private double timeToMatch;
    
    public void setTargetTimed(String target)
    {
        long tic = System.nanoTime();
        this.setTarget(target);
        long toc = System.nanoTime();
        timeToSetTarget = (toc-tic)/1000000.0;
    }
    
    public void setPatternTimed(String pattern)
    {
        long tic = System.nanoTime();
        this.setPattern(pattern);
        long toc = System.nanoTime();
        timeToSetPattern = (toc-tic)/1000000.0;
    }
    
    public void createFailTableTimed(){
        long tic = System.nanoTime();
        this.lastOccurenceFunction();
        long toc = System.nanoTime();
        timeToCreateTable = (toc-tic)/1000000.0;
    }
    
    public int matchTimed()
    {
        long tic = System.nanoTime();
        int m = this.match();
        long toc = System.nanoTime();
        timeToMatch = (toc-tic)/1000000.0;
        
        return m;
    }
    
    public String toTimingString()
    {
        return  "time to create Table: " + this.timeToCreateTable + "ms, " +
                "time to Match: " + this.timeToMatch +"ms, " + "\n" +
                "Total time: " + (this.timeToCreateTable+this.timeToMatch) + "ms";
    }
    
    public abstract void setTarget(String target);
    public abstract void setPattern(String pattern);  
    public abstract void lastOccurenceFunction();
    public abstract int match();
    public abstract int getNumberOfSteps();
    
}

