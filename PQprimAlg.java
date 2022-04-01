/*----------------PRIM ALGORITHM USING PRIORITY QUEUE---------------------------
*/

package cpcs324_project1_phase1;

import java.util.PriorityQueue;

/**
 * this class implement a prim algorithm using an adjacency list graph as an input 
 * and PriorityQueue to detriment the minimum spanning tree edges
 * @author razanali, tahni , asma 
 */
public class PQprimAlg extends MSTAlgorithm{
    /**
     * cost of MST 
     */
    int cost;
    /**
     * number of edges in the MST (counter)
     */
    int inTree;
    
    
    /**
     * empty constructor 
     */
    public PQprimAlg() {
    }
    /**
     * this method take the graph as its parameter to find MST of a graph
     * @param graph of a graph input
     */
    public void PrimPQ(Graph graph){
        
        PriorityQueue<Edge> pq = new PriorityQueue<Edge>();//create a PriorityQueue to store edges beased on their wight 
        //Edge class implment the interface compareable and ovierride the compareTo method 
        graph.vertices[0].isVisited= true;//make the first vertic as visited
        //we already know that we need V-1 edges to create the MST of a graph 
        //for thst this array size is [verticesNo-1]
        MSTResultList= new Edge[graph.verticesNo-1];//new empty edges list for MST edges Result  
        //---------------------------------
        Vertex source = graph.vertices[0];
        inTree =1;//incrment the number of Vt of the MST
        //add all source adjacent edges to the queue
        for(int i =0;i<source.adjList.size();i++){
            pq.add(source.adjList.get(i));
        }
        //----------------------------------
        //condtion: if there still edges in the graph or if  V-Vt > 0 (there are still vertices not visited in the MST)
        while(!pq.isEmpty() && inTree < graph.vertices.length){
            Edge edge = pq.remove();//dequeue the edge which has the most priority (who has minimum weight )
            //check target only, since the edge will not be in the queue if the source is not visited
            if(edge.target.isVisited){//if the target is visited there is no need add that edge to MST
                continue; //continue to check another edge   
            }
            //if the target vertex is not visited 
            //add that edge to the Et liist 
            MSTResultList[inTree - 1] = edge;
            inTree++;//increment number of Vt 
            edge.target.isVisited=true;//make the target as visited 
            cost+=edge.weight;//sum the weigth of current edge to the summation of cost of all edges of MST 
            //take the source vertex and add all the edges of it to the queue
            Vertex sourceVertex = graph.vertices[edge.target.label];
            for(int i =0;i<sourceVertex.adjList.size();i++){
            pq.add(sourceVertex.adjList.get(i));
            } 
        } 
      
    }
        
    
    /**
     * display all edges in the Et MST 
     */
    @Override
    public void displayResultingMST() {
        for(int i=0;i<MSTResultList.length;i++){
             System.out.println(MSTResultList[i].source.label+"-"+MSTResultList[i].target.label +" weight : "+MSTResultList[i].weight );
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
