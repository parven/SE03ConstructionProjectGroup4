/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment01;

/**
 *
 * @author lewi0146
 */
public class TrieTester {

    private static final int TEST_LEVEL = 5;

    public static void test1() {
        if (TEST_LEVEL >= 1) {
            TrieNode<Integer> n1 = new TrieNode<Integer>();
            TrieNode<Integer> n2 = new TrieNode<Integer>();
            TrieNode<Integer> n3 = new TrieNode<Integer>();

            n2.addData(12);
            n3.addData(34);
            n1.addChild('a', n2);
            n1.addChild('b', n3);

            TrieNode<Integer> x = n1.getChild('a');
            TrieNode<Integer> y = n1.getChild('b');
            TrieNode<Integer> z = n3.getChild('z');

            System.out.println("n1 is " + n1);
            System.out.println("Child a of n1 is " + x);
            System.out.println("Child b of n1 is " + y);
            System.out.println("z is " + z);

        }
    }

    public static void test2() {
        if (TEST_LEVEL >= 2) {
            String[] ss = new String[]{"abacus", "abasement", "abase", "abroad",
                "aloud", "all", "allowed", "allow", "allowance", "abroad"};
            Trie<Integer> t = new Trie<Integer>();
            int counter = 1;
            for (String s : ss) {
                t.insert(s, counter++);
            }
            String[] ss2 = new String[]{"aba", "abase", "abasement", "abroad", "aloud", "broad"};
            for (String s : ss2) {
                System.out.println(s + ": " + t.get(s));
            }
            System.out.println();
            for (String s : ss2) {
                System.out.println(s + ": " + t.getNode(s.toCharArray()));
            }
        }
    }

    public static void test3() {
        if (TEST_LEVEL >= 3) {
            String[] ss = new String[]{"abacus", "abasement", "abase", "abroad",
                "aloud", "all", "allowed", "allow", "allowance", "abroad"};
            Trie<Integer> t = new Trie<Integer>();
            int counter = 1;
            for (String s : ss) {
                t.insert(s, counter++);
            }
            String[] ss2 = new String[]{"aba", "all"};
            for (String s : ss2) {
                System.out.println(s + ": " + t.getAlphabeticalListWithPrefix(s));
            }
            System.out.println();
        }
    }

    public static void test4and5() {
        if (TEST_LEVEL >= 4) {
            String fileName = "word-freq.expanded.txt";
            System.out.print("Reading in trie...");
            Trie<TrieData> dt = Trie.readInDictionary(fileName);
            System.out.println("done");

            test4(dt);
            test5(dt);
        }
    }

    public static void test4(Trie<TrieData> dt) {
        if (TEST_LEVEL >= 4) {
            String[] ss2 = new String[]{"anomaly", "aba", "hoodie"};
            for (String s : ss2) {
                System.out.println(s + ": " + dt.getNode(s));
            }
            System.out.println();
            for (String s : ss2) {
                System.out.println(s + ": " + dt.get(s));
            }
        }
    }

    public static void test5(Trie<TrieData> dt) {
        if (TEST_LEVEL >= 5) {
            String[] more = new String[]{"aba", "the"};
            for (String mm : more) {
                System.out.println("PREFIX =" + mm);
                String f = dt.getMostFrequentWordWithPrefix(mm);
                System.out.println("Most Frequent Word is " + f);
            }
        }
    }

    public static void main(String[] args) {
        test1();
        test2();
        test3();
        test4and5();
    }
}
