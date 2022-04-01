
package cpcs324_project1_phase1;


/**
 * class to create a graph as an adjacency list
 * 
 * @author razan, tahani, asma 
 */
public class Graph {
    /**
     * number of vertices in a graph 
     */
    int verticesNo;
    /**
     * number of edge in a graph 
     */
    int edgeNo;
    /**
     * true if its directed graph, false if undirected
     */
    boolean isDigraph; 
    /**
     * array of vertices of the adjacency list
     */
    Vertex [] vertices; 
    
    /**
     * empty constructor of graph 
     */
    public Graph() {
    }
   
    /**
     * constructor with three parameter (number of vertices , number of edges , boolean to indicate  
     * if graph is directed or not
     * 
     * @param verticesNo
     * @param edgeNo
     * @param isDigraph 
     */
    public Graph(int verticesNo, int edgeNo, boolean isDigraph  ) {
        this.verticesNo = verticesNo;
        this.edgeNo = edgeNo;
        this.isDigraph= isDigraph;
        vertices = new Vertex[verticesNo];      
    }
    /**
     * method that generate graph and ensure that graph is connected 
     */
    public void makeGraph(){
        
        //assign new Vertex object for all vertices in the graph
        //which will replace the default null of array 
        for (int i = 0; i < verticesNo; i++) {
            vertices[i] = new Vertex(i);  
        }
        //--------------------
        //this part to ensure that graph is connected 
        //to connect V vetex we ned V-1 edge 
        //be connected each vetex to the next vertex adjacent to it , we ensuring that graph is connected 
        for (int i = 1; i < verticesNo ; i++) {
            int randomWeight = (int) (1 + Math.random() * 20); //first generate random wight to assign it to the edge 
            addEdge(vertices[i-1], vertices[i], randomWeight); //then add that edge graph
        }
        //remove that V-1 edge from the total
        //the remaing edges is totalNumberEdge
        int totalNumberEdge = edgeNo -( verticesNo-1 );
        //create edges randomaly and add them
        int i = 0; //counter of edges 
        while( i <totalNumberEdge ){
            //randomly choose a source 
            int sourceLable = (int) (Math.random() * (verticesNo));
            //randomly choose a target 
            int targetLabel = (int) (Math.random() * (verticesNo));
            if (sourceLable == targetLabel ) {//if the source is equal to the 
                continue; 
            }
            //if they are diffrent 
            //find both vertices 
            Vertex sourceV = vertices[sourceLable];
            Vertex sourceT = vertices[targetLabel];
            if (isDuplicated(sourceLable, targetLabel)) { //if the graph is undirected i need to check only the source 
                                                          //since the target will have the same edge too 
                continue; 
            }
            if(isDigraph && isDuplicated(targetLabel, sourceLable)){ //if the graph is directed i need to seach for both
                continue;                                            //taret and source becouse they have diffrent value 
            }
           
            int randomWeight = (int) (1 + Math.random() * 20);//generate randomWight 
            addEdge(sourceV, sourceT, randomWeight); //add the edge to the graph 
            i++; //increment counter of edges 
        
        }      
    }//end of makeGraph method 
    
    //--------------
    
    /**
     * this method used to add edge to the graph 
     * 
     * @param v edge source vertex 
     * @param u edge target vertex 
     * @param weight weight of edge 
     */
    public void addEdge(Vertex v,Vertex  u, int weight){
        Edge newEdge = new Edge(v, u, weight); //create new edge 
        v.adjList.add(newEdge);//add ghe edge to the source pare 
        if(!isDigraph){//if the graph is undirected 
            u.adjList.add(new Edge(u, v, weight)); //add the same edge to the source too    
            edgeNo++;
        }    
    }//end of addEdge method 
    
    //--------------
    /**
     * 
     * @param SourceId
     * @param targetID
     * @return true if the source has an edge with the target, otherwise false 
     */
    public boolean isDuplicated(int SourceId, int targetID) {
        
        for(int i=0;i<vertices[SourceId].adjList.size();i++){//for all edges of source vertex
             if ((vertices[SourceId].adjList.get(i).target.label == targetID)) {//if there is an edge with the taget id 
                return true;//return true 
            }
        }
        return false;//if target not found return false 
    }//end of isDuplicated method 
}
