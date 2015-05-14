/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment01;

import java.util.Scanner;

/**
 *
 * @author lewi0146
 */
public class SuffixTrieTester {
    private static final int TEST_LEVEL = 1;

    public static void main(String[] args) throws InterruptedException {
        test1();
        test2();
        test3(); //takes input from console.
    }
    
    /*
    test1 is for testing over the Frank.txt file, which is very small in size.
    Queries being tested here are being supplied internally.
    Also, Queries here are of different sized, are of complete words as well as
    of partial words. Additionally, some queries contain more than one words with
    spaces and punctuations  like comma.
    */
    public static void test1(){
        if(TEST_LEVEL == 1){
            long startTime, stopTime, runTime;
            Runtime s_runtime = Runtime.getRuntime();
            String fileName = "Frank.txt";
            long usedMemory = s_runtime.totalMemory() - s_runtime.freeMemory();
            
            startTime = System.nanoTime();
            SuffixTrie st = SuffixTrie.readInFromFile(fileName);
            stopTime = System.nanoTime();
            usedMemory = (s_runtime.totalMemory() - s_runtime.freeMemory()) - usedMemory;
            runTime = stopTime - startTime;
            
            System.out.println("Memory used by the Suffix Trie : " + usedMemory);
            System.out.println("Suffix Trie was created in (s) : " + runTime/1000000000.0);
            System.out.println("Suffix Trie was created in (ms) : " + runTime/1000000.0 + "\n");
            
            String[] ss = {"hideous", "the only", "onster", ", the", "ngeuhhh",", and the","by degrees", "such evil forebodings"};
            
            for (String s : ss) {
                // using toLowerCase to make all queries case insensitive.
                SuffixTrieNode sn = st.getNode(s.toLowerCase()); 
                System.out.println("[" + s + "]: " + sn);
            }
        
        }
    }
    
    /*
    test2 is for testing over a bit larger file FrankLarge.txt, which is a little
    larger in size than Frank.txt.
    Here queries are also supplied internally, and are of different sizes and
    different types.
    */
    public static void test2(){
        if(TEST_LEVEL == 2){
            long startTime, stopTime, runTime;
            Runtime s_runtime = Runtime.getRuntime();
            String fileName = "FrankLarge.txt";
            long usedMemory = s_runtime.totalMemory() - s_runtime.freeMemory();
            
            startTime = System.nanoTime();
            SuffixTrie st = SuffixTrie.readInFromFile(fileName);
            stopTime = System.nanoTime();
            usedMemory = (s_runtime.totalMemory() - s_runtime.freeMemory()) - usedMemory;
            runTime = stopTime - startTime;
            
            System.out.println("Memory used by the Suffix Trie : " + usedMemory);
            System.out.println("Suffix Trie was created in (s) : " + runTime/1000000000.0);
            System.out.println("Suffix Trie was created in (ms) : " + runTime/1000000.0 + "\n");
            
            String[] ss = {"observed","she", "remember", "em", "people", "oung", ", the first", ", but I", "young wo", "Uncle Thomas' library"};
            
            for (String s : ss) {
                // using toLowerCase to make all queries case insensitive.
                SuffixTrieNode sn = st.getNode(s.toLowerCase()); 
                System.out.println("[" + s + "]: " + sn );
            }
        
        }
    }
    
    /*
    test03  uses a much larger file FrankLarger.txt. And gets its queries from the user
    through the console.
    This test method has been used to compare Suffix Trie with Boyer-Moore
    */
    public static void test3() throws InterruptedException{
        if(TEST_LEVEL == 3){
            long startTime, stopTime, runTime;
            Runtime s_runtime = Runtime.getRuntime();
            
            String fileName = "FrankLarger.txt";
            System.out.println("BUILDING THE SUFFIX TRIE...");
            
            long usedMemory = s_runtime.totalMemory() - s_runtime.freeMemory();
            startTime = System.nanoTime();
            SuffixTrie st = SuffixTrie.readInFromFile(fileName);
            stopTime = System.nanoTime();
            usedMemory = (s_runtime.totalMemory() - s_runtime.freeMemory()) - usedMemory;
            runTime = stopTime - startTime;
            
            System.out.println("DONE!\n");
            System.out.println("Memory used by the Suffix Trie : " + usedMemory);
            Thread.sleep(1000);
            System.out.println("Suffix Trie was created in (s) : " + runTime/1000000000.0);
            System.out.println("Suffix Trie was created in (ms) : " + runTime/1000000.0 + "\n");
            Thread.sleep(1000);
            
            System.out.println("ENTER YOUR QUERIES.\n\n");
            Scanner scan = new Scanner(System.in);
            String pattern = scan.nextLine();


            while (!pattern.isEmpty())
            {
                startTime = System.nanoTime();
                SuffixTrieNode sn = st.getNode(pattern.toLowerCase()); 
                stopTime = System.nanoTime();
                runTime = stopTime - startTime;
                System.out.println("[" + pattern + "]: " + sn); 
                
                System.out.println("Substring found in (s) : " + runTime/1000000000.0);
                System.out.println("Substring found in (ms) : " + runTime/1000000.0);
            
                pattern = scan.nextLine();
            }
        }
    }
}
