
package cpcs324_project1_phase1;

import java.util.LinkedList;


/**
 * this class represent graph vertices 
 * @author razanali, tahani, asma
 */
public class Vertex {
    /**
     * label of vertex 
     */
    int label;
    /**
     * boolean to determine if the vertex is visited or not 
     */
    boolean isVisited;
    /**
     * to indicate the edges of vertex of adjacency list
     */
    LinkedList<Edge> adjList; //each vertex 

  
    /**
     * empty constructor 
     */
    public Vertex() {
        adjList = new LinkedList<Edge>() ;
    }
    /**
     * 
     * @param label of a vertex
     */
    public Vertex(int label) {
        this.label = label;
        adjList = new LinkedList<Edge>() ;
       
    }
 }
