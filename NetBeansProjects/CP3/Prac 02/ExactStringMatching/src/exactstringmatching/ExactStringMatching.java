/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exactstringmatching;

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
        
        //String file = "dickens.txt";
        String file = "repaper.txt";
        //String file = "Frank.txt";
        
        Scanner s = new Scanner(new File(file));
        StringBuilder targetBuilder = new StringBuilder();
        while (s.hasNextLine())
        {
            targetBuilder.append(s.nextLine());            
        }
        String target = targetBuilder.toString();
        
        StringMatcher matcher = new StringMatcherKMP();
        
        matcher.setTargetTimed(target);

        Scanner in = new Scanner(System.in);
        System.out.print("Enter pattern: ");
        String pattern = in.nextLine();
        
        
        while (!pattern.isEmpty())
        {
            matcher.setPatternTimed(pattern);
            int index = matcher.matchTimed();
            System.out.print("index: " + index + ", ");
            System.out.print("steps: " + matcher.getNumberOfSteps()+ ", ");
            System.out.println(matcher.toTimingString());
            pattern = in.nextLine();
        }
    }
}
