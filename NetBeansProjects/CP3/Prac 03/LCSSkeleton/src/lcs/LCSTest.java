/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package lcs;

import java.util.Collection;

/**
 *
 * @author lewi0146
 */
public class LCSTest {

    public static void test(String pattern, String target) {
        System.out.println("Testing: " + pattern + ", " + target);

        LCS lcs = new LCS(pattern, target);

        lcs.calculateMatrix();
        lcs.printMatrix();
        System.out.println("Length of LCS = " + lcs.getLongestLength());
        System.out.println("LCS: " + lcs.getLCS());


        Collection<String> seqs = lcs.getAllLCS(pattern.length(),target.length());
        System.out.println("All sequences (" + (seqs == null ? -1 : seqs.size()) + ")");
        if (seqs != null) {
            for (String s : seqs) {
                System.out.println(s);
            }
        }

    }

    public static void main(String[] args) {

        test("lullabybabies","skullandbones");
        System.out.println();
        test("GTTCCTAATA","CGATAATTGAGA");

    }
}
