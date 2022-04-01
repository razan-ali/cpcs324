
package cpcs324_project1_phase1;

/**
 * represent the edge of graph
 * 
 * implemented the Comparable interface to compare the weights of edges 
 * @author razan, tahani, asma 
 */
public class Edge implements Comparable<Edge>  {
    /**
     * source vertex of edge 
     */
    Vertex source; 
    /**
     * target vertex of edge 
     */
    Vertex target; 
    /**
     * parent vertex of edge 
     */
    Vertex parent; 
    /**
     * edge weight 
     */
    int weight;    
    /**
     * empty constructor of class
     */
    public Edge() {
        source = new Vertex(); //create new source
        target = new Vertex(); //create new target
        parent = new Vertex(); //create new parent
    }
    /**
     * constructor with parameters
     * 
     * @param source source vertex of edge 
     * @param target target vertex of edge 
     * @param weight edge weight 
     */
    public Edge(Vertex source, Vertex target, int weight) {
        this.source = source;
        this.target = target;
        this.weight = weight;
        parent= new Vertex();
    }   
    /**
     * compare the edges weight
     * 
     * @param o the other edge to compare 
     * @return 1 if first edge has bigger wight, 0 if they equals , otherwise 0
     */
    @Override
    public int compareTo(Edge o) {
        if(this.weight > o.weight)
            return 1;
        else if (this.weight == o.weight)
            return 0;
        else return -1;
      
    }
    /**
     * 
     * @return string that print edge 
     */
    @Override
    public String toString() {
        return "source "+source.label + "-" +"destenation "+ target.label + ": " + weight;
    }
}
