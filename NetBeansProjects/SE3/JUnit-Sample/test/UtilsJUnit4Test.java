/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.concurrent.TimeoutException;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import sample.Utils;

/**
 *
 * @author theLennisters
 */
public class UtilsJUnit4Test {
    
    public UtilsJUnit4Test() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        System.out.println("* UtilsJUnit4Test: setUp() method");
    }
    
    @After
    public void tearDown() {
        System.out.println("* UtilsJUnit4Test: tearDown() method");
    }

    /**
     * Test of concatWords method, of class Utils.
     */
    
    public void testHelloWorld(){
        System.out.println("* UtilsJUnit3Test: test method 1 - testHelloWorld()");
        assertEquals("Hello, world!", Utils.concatWords("Hello", ", ", "world", "!"));
    }

    /**
     * Test of computeFactorial method, of class Utils.
     */
    public void testWithTimeout() throws InterruptedException, TimeoutException { 
        System.out.println("* UtilsJUnit3Test: test method 2 - testWithTimeout()");
        final int factorialOf = 1 + (int) (30000 * Math.random()); 
        System.out.println("computing " + factorialOf + "!");

        Thread testThread = new Thread() { 
            public void run() {
              System.out.println(factorialOf + "! = " + Utils.computeFactorial(factorialOf));
            } 
        };
        
        testThread.start();
        Thread.sleep(1000);
        testThread.interrupt();
        
        if(testThread.isInterrupted()){
            throw new TimeoutException("the test took too long to completer");
        }
    }

    /**
     * Test of normalizeWord method, of class Utils.
     */
    @Test
    public void testNormalizeWord() {
        System.out.println("normalizeWord");
        String word = "";
        String expResult = "";
        String result = Utils.normalizeWord(word);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
