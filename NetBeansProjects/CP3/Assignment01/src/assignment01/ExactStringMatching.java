/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment01;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author lewi0146
 */
public class ExactStringMatching {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        String file = "FrankLarger.txt";
        
        Scanner s = new Scanner(new File(file));
        StringBuilder targetBuilder = new StringBuilder();
        while (s.hasNextLine())
        {
            targetBuilder.append(s.nextLine());            
        }
        String target = targetBuilder.toString();
        
        StringMatcher matcher = new StringMatcherBM();
        
        matcher.setTargetTimed(target.toLowerCase());

        Scanner in = new Scanner(System.in);
        System.out.print("Enter pattern: ");
        String pattern = in.nextLine();
        
        
        while (!pattern.isEmpty())
        {
            matcher.setPatternTimed(pattern.toLowerCase());
            matcher.createFailTableTimed();
            
            int index = matcher.matchTimed();
            
            System.out.print("index: " + index + ", ");
            System.out.print("steps: " + matcher.getNumberOfSteps()+ ", ");
            System.out.println(matcher.toTimingString());
            
            pattern = in.nextLine();
        }
    }
}

