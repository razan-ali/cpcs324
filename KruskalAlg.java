/*----------------PRIM ALGORITHM USING PRIORITY QUEUE---------------------------
--------
*/
package cpcs324_project1_phase1;

import java.util.PriorityQueue;

/**
 * his class implement a kruskal algorithm using an adjacency list graph as an input 
 * using quick find method of store the representative for all vertices -to optimizes the time efficiency of the find operation
 * 
 * @author razanali , tahani , asma 
 */
public class KruskalAlg extends MSTAlgorithm{
    /**
     * cost of MST
     */
    int cost;
    /**
     * 
     * @param graph input graph 
     */
    public void Kurskal(Graph graph){
        
        int verticesNo = graph.verticesNo;//take the number of vertices from the graph 
        MSTResultList = new Edge[verticesNo-1];//we already know that we need V-1 edges to fine the MST 
        //create a PriorityQueue to store edges beased on their wight 
        //Edge class implment the interface compareable and ovierride the compareTo method 
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();
        //add all the edges in the adjacency list to the priorityQueue
        for(int i=0;i<verticesNo;i++){//for all vertices
            for(int j =0;j<graph.vertices[i].adjList.size();j++) //for all edges of verices 
                pq.add(graph.vertices[i].adjList.get(j)); //add to the priorityQueue
        }
        //create an array for subsets 
        Edge [] subsets = new Edge[verticesNo];
        makeSet(subsets);//make sets of each vertex in the graph 
        int MSTedges =0;//to indicate the number of edges in the graph Et
        
        while(MSTedges<verticesNo && !pq.isEmpty()){
            Edge edge = pq.remove(); //dequeue the edge which has the most priority (who has minimum weight )
            
            int source_subset = find(subsets, edge.source.label); //find the subset of the source 
            int target_subset = find(subsets, edge.target.label); //find the subset of the targe 
            //if they form diffrent subsets that imples this edge will not create a cycle 
            if(source_subset != target_subset){
                MSTResultList[MSTedges] =  edge;//add the edge to the edges result
                cost += edge.weight; //add the cost two 
                MSTedges++;//increment number of edges Et
                union(subsets, source_subset, target_subset);// union the disjoint subset 
                  
                }
            }
        
        
        //------------ 
    }//end of kruskal method 
    //-------------------
    
    
    
    /**
     * this method used to create one-element set{x} for all the V in the graph 
     * @param edges 
     */
    public void makeSet(Edge [] edges ) {
        //for all the graph vertices
        //create new set 
        for (int i = 0; i < edges.length; i++) {
            //set the parent and source as a i 
            edges[i] = new Edge();
            edges[i].source.label = i;
            edges[i].parent.label = i;
           
        }
    } 
    /**
     * this method used to find the subset containing x 
     * 
     * 
     * @param edges take all edges
     * @param vertex that need to find its parent(subset)
     * @return parent represent the representatives of the subsets
     */
    public int find(Edge [] edges, int vertex) {
        //if the parent of the vertic != the same label of vertex 
        if (edges[vertex].parent.label != vertex) {
            //recursivly call find the set of its parent 
            return find(edges, edges[vertex].parent.label);
        }
        //return the set  
        return vertex;
    }
    
    
    /**
     * union the disjoint subset of source_vertex and target_vertex 
     * @param edges
     * @param source_vertex 
     * @param target_vertex 
     */
    public void union(Edge [] edges, int source_vertex, int target_vertex) {
        //first find the subset of source_vertex and subset of target_vertex 
        int x_set_parent = find(edges, source_vertex);
        int y_set_parent = find(edges, target_vertex);
        //make source_vertex as the target_vertex subset  
        edges[y_set_parent].parent.label = x_set_parent;
    } 
    /**
     * display all edges in the Et MST 
     */
    @Override
    public void displayResultingMST() {
        for(int i = 0;i<MSTResultList.length;i++){  
                System.out.println(MSTResultList[i].source.label+"  "+ MSTResultList[i].target.label+" w: "+ MSTResultList[i].weight);
        
        }  
    } 
    /**
     * 
     * @return cost of MST 
     */
    public int getCost(){
        return cost;
    }
}
