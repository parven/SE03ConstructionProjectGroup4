/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lewi0146
 */
public class GraphTester {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //test1(); // chpt 16
        //test2(); // chpt 16
        //bacon(); // chpt 16, Fun time!
        //test3(); // chpt 17
        test4(); // chpt 18, not in here but should do a topological sort
        

    }

    public static void printGraph(Graph g) {
        Iterable<Vertex> vs = g.getVertices();
        System.out.println("Vertices: " + vs);
        for (Vertex v : vs) {
            System.out.print(v + ": ");
            Iterable<Vertex> adjVList = g.adjacentTo(v);
            for (Vertex av : adjVList) {
                System.out.print(av + " ");
            }
            System.out.println();

        }
    }

    public static void test1() {

        System.out.println("test 1: Build a graph");

        // build a sample graph and print it
        //Graph g = null;
        Graph g = new AdjacencyListUndirectedGraph();
        //Graph g = new AdjacencyListDirectedGraph();

        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("c", "d");
        g.addEdge("c", "e");
        g.addEdge("d", "a");
        
        printGraph(g);

    }
    
     public static void test2() {

        System.out.println("test 2: Breadth First Search");

        // build a sample graph and print it
        //Graph g = null;
        Graph g = new AdjacencyListUndirectedGraph();
        //Graph g = new AdjacencyListDirectedGraph();

        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("c", "d");
        g.addEdge("c", "e");
        g.addEdge("d", "a");

        printGraph(g);

        // perform a breadth first traversal from vertex "a"
        BreadthFirstSearch bfs = new BreadthFirstSearch(g, g.getVertex("a"));
        System.out.println(bfs.getBreadFirstTraversalList());
        
        // print out the distances/frontiers from vertex a
        Iterator<Vertex> vit = g.getVertices().iterator();
        Vertex source = vit.next();
        System.out.println("Source"+ source);
        
        while (vit.hasNext()) {
            Vertex dest = vit.next();
            System.out.println("dist from " + source.getLabel() + " to " + dest + ": " + bfs.getDistanceTo(dest));
        }

    }
    

    public static void bacon() {
        try {
            Scanner fs = new Scanner(new File("cast.rated.txt"));
            //Graph g = null;
            Graph g = new AdjacencyListUndirectedGraph();


            System.out.println("reading in file");
            while (fs.hasNext()) {
                String line = fs.nextLine();
                Scanner movie = new Scanner(line);
                movie.useDelimiter("/");
                String movieName = movie.next();
                while (movie.hasNext()) {
                    // add each actor
                    g.addEdge(movieName, movie.next());
                }
            }
            //printGraph(g);

            System.out.println("calculating bfs for Bacon, Kevin");
            Vertex source = g.getVertex("Bacon, Kevin");
            BreadthFirstSearch bfs = new BreadthFirstSearch(g, source);

            Scanner in = new Scanner(System.in);
            System.out.print("Enter an actor <lastname, firstname>: ");
            
            String actor = in.nextLine();
            while (!actor.isEmpty()) {
                

                Vertex dest = g.getVertex(actor);
                System.out.println("dist from " + source.getLabel() + " to " + dest + ": " + bfs.getDistanceTo(dest) / 2);

                for (Vertex v : bfs.pathTo(dest)) {
                    System.out.println("  " + v.getLabel());
                }
                
                System.out.print("Enter an actor <lastname, firstname>: ");
                actor = in.nextLine();
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(GraphTester.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void test3() {

        System.out.println("test 3 - Depth First Search");
        // build a sample graph and print it
        //Graph g = null;
        //Graph g = new AdjacencyListUndirectedGraph();
        Graph g = new AdjacencyListDirectedGraph();

        g.addEdge("a", "b");
        g.addEdge("b", "c");
        g.addEdge("b", "d");
        g.addEdge("c", "d");
        g.addEdge("c", "e");
        g.addEdge("d", "a");

        printGraph(g);

        System.out.println("Depth First");
        //List<Vertex> ld = null;
        //ld = DepthFirstTraversal.traverse(g);
        DepthFirstSearch dfs = new DepthFirstSearch(g, g.getVertex("a"));
        System.out.println(dfs.getDepthFirstTraversalList());
        
        //System.out.println(ld);


    }
    
    public static void test4() {
        System.out.println("test 4 - Topological Sort");
        Graph g = new AdjacencyListDirectedGraph();
        
        g.addEdge("LA15", "LA16");
        g.addEdge("LA15", "LA31");
        g.addEdge("LA16", "LA127");
        g.addEdge("LA16", "LA141");
        g.addEdge("LA16", "LA32");
        g.addEdge("LA22", "LA126");
        g.addEdge("LA22", "LA141");
        g.addEdge("LA31", "LA32");
        g.addEdge("LA32", "LA126");
        g.addEdge("LA32", "LA169");
        //g.addEdge("LA22","LA21");
        
        printGraph(g);
        
        System.out.println();
        System.out.println("-- Topological Sort --");
        
        Iterable<Vertex> vit = g.getVertices();
        for(Vertex ver : vit){
            if(!chkVertex(ver, g)){
                System.out.println("Source is : "+ ver.getLabel());
                DepthFirstSearch dfs = new DepthFirstSearch(g, g.getVertex(ver.getLabel()));
                List<Vertex> list = dfs.getDepthFirstTraversalList();
                Collections.reverse(list);
                System.out.println(list);
                System.out.println();
            }
        }
    }
    
    public static boolean chkVertex(Vertex v, Graph g){
        Iterable<Vertex> vit = g.getVertices();
        for(Vertex ver : vit){
            if(g.hasEdge(ver, v)){
                return true;
            }
        }
        return false;
    }

    
}
