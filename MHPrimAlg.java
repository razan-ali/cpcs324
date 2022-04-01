
package cpcs324_project1_phase1;

import java.util.LinkedList;
/**
 * this class implement a prim algorithm using an adjacency list graph as an input 
 * and minHeap to detriment the minimum MST
 * 
 * @author razanali, tahani, asma
 */
public class MHPrimAlg extends MSTAlgorithm {
     
    /**
     * capacity of the heap maxSize
     */
    int capacity;
    /**
     * current  size of the heap 
     */
    int currentSize;
    /**
     * minHeap Array 
     */
    Edge [] MinHeapArr; 
    /**
     * indices -position- of heap element in the Heap
     */
    int [] indexes; 
    /**
     * cost of MST 
     */
    int cost;
    /**
     * empty constructor 
     */
    public MHPrimAlg() {
        
    }
    /**
     * constructor 
     * @param verticesNo 
     */
    public MHPrimAlg(int verticesNo){
        capacity =  verticesNo;//capacity of minHeap equal to the number of vertices in graph
        MinHeapArr = new Edge[capacity+1]; //create HeapNode for all the vertices
        indexes = new int[capacity]; //intilize the index array 
        //first element of array not used assging it to -1 and infinity
        MinHeapArr[0] = new Edge(); 
        MinHeapArr[0].weight = Integer.MIN_VALUE;//minumn value to do not use it 
        MinHeapArr[0].source.label = -1;
        currentSize = 0; 
      
    }
    
    /**
     * this method take the graph as its parameter to find MST of a graph
     * @param graph 
     */    
    public void PrimMH(Graph graph){
        MSTResultList= new Edge[capacity];
        
        
        //-----------
        //intillize all edges 
        for (int i = 0; i < graph.verticesNo; i++) {
            //intilize all heap node 
            MSTResultList[i] = new Edge();
            MSTResultList[i].source.label = i;//with lable 
            MSTResultList[i].parent.label = -1;//with lable 
            MSTResultList[i].weight = Integer.MAX_VALUE;//and infinity as the key 
           
        }
        //let the first node has weight of 0  
        MSTResultList[0].weight = 0;


        //add all vertices to the minHeap 
        for (int i = 0; i < graph.verticesNo; i++) {
            insert(MSTResultList[i]);
        }
        //-------
        //while heap node is not empty 
        while (!isEmpty()) {
            //extract the min top element 
            Edge extractedMinNode = extractMin();
            //extracted vertex lable 
            int extractedVertex = extractedMinNode.source.label;
            MSTResultList[extractedVertex].source.isVisited = true; //mark the extracted vertex ad visited 

            LinkedList<Edge> list = graph.vertices[extractedVertex].adjList;//for all edges of the vertex 
            for (int i = 0; i < list.size(); i++) {
                Edge edge = list.get(i);
                //only if edge destination is present in heap (doesnt visited yet)
                if (!MSTResultList[edge.target.label].source.isVisited) {
                    int destination = edge.target.label;
                    int newKey = edge.weight;
                    //check if updated key < existing key, if yes, update if
                    if (MSTResultList[destination].weight > newKey) {
                        decreaseKey(newKey, destination);//change thee wight of the node 
                        //update the wight and the parent
                        MSTResultList[destination].parent.label = extractedVertex;
                        MSTResultList[destination].weight = newKey;
                       
                        
                    }
                }
            }
        
        }   
    }
       
    
    /**
     * remove the min value form the min heap 
     * @param newKey
     * @param vertex 
     */
    public void decreaseKey(int newKey, int vertex) {
        //get the index which key's needs a decrease;
        int index = indexes[vertex];
        //get the node and update its value
        Edge node = MinHeapArr[index];
        node.weight = newKey;
        bubbleUp(index);
        
    }
    /**
     * to add new node to the minHeap
     * @param Node to  added 
     */
    public void insert(Edge Node) {
        currentSize++; //frst increment size of heao 
        int Index = currentSize; 
        MinHeapArr[Index] = Node; //update the minHeap
        indexes[Node.source.label] = Index; //update the place of the node 
        bubbleUp(Index);
        
    }
    /**
     * put the new value added to the correct position
     * 
     * @param Number 
     */
    public void bubbleUp(int Number) {
      
        int parentIndex = Number / 2; //find parent of node 
        int currentIndex = Number;
        //while parent is bigger than child 
        //buble the child up
        while (currentIndex > 0 && MinHeapArr[parentIndex].weight > MinHeapArr[currentIndex].weight) {
           
            Edge currentNode = MinHeapArr[currentIndex];
            Edge parentNode = MinHeapArr[parentIndex];
            //swap the positions
            indexes[currentNode.source.label] = parentIndex;
            indexes[parentNode.source.label] = currentIndex;
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = parentIndex / 2;
        }
        //otherwise do nothing=g
    }
    /**
     * extract the node with the last weight
     * @return the minimum node which is the root 
     */
    public Edge extractMin() {
        Edge min = MinHeapArr[1];//take the root of heap first element 
        Edge lastNode = MinHeapArr[currentSize]; //save the last element 
        indexes[lastNode.source.label] = 1; //put the last emlent as the first element in the hrap 
        MinHeapArr[1] = lastNode; //replace the first element by the root 
        MinHeapArr[currentSize] = null; //delete the last element >> = null
        sinkDown(1);
        currentSize--;//decrement the current size of heap 
        return min;  //reruen the deleted node 
    }
    /**
     * this method rearrange the heap again  after deleting the top 
     * @param k 
     */
    public void sinkDown(int k) {
        int smallest = k;
        int leftChildIndex = 2 * k;
        int rightChildIndex = 2 * k + 1;
        if (leftChildIndex < heapSize() && MinHeapArr[smallest].weight > MinHeapArr[leftChildIndex].weight) {
            smallest = leftChildIndex;
        }
        if (rightChildIndex < heapSize() && MinHeapArr[smallest].weight > MinHeapArr[rightChildIndex].weight) {
            smallest = rightChildIndex;
        }
        if (smallest != k) {

            Edge smallestNode = MinHeapArr[smallest];
            Edge kNode = MinHeapArr[k];

            //swap the positions
            indexes[smallestNode.source.label] = k;
            indexes[kNode.source.label] = smallest;
            swap(k, smallest);
            sinkDown(smallest);
        }
    }

    /**
     * swap the position of two nodes
     * @param a index of first node
     * @param b index of second node 
     */
    public void swap(int a, int b) {
        Edge temp = MinHeapArr[a];
        MinHeapArr[a] = MinHeapArr[b];
        MinHeapArr[b] = temp;
    }

    /**
     * 
     * @return true if minHeap is empty 
     */
    public boolean isEmpty() {
        return currentSize == 0;
    }

    /**
     * 
     * @return the currentSize of minHeap
     */ 
    public int heapSize() {
            return currentSize;
    }
    /**
     * display all edges in the Et MST 
     */
    @Override
    public void displayResultingMST() {
       
        for(int i=0; i<MSTResultList.length;i++){
            if(MSTResultList[i].parent.label!=-1)
                System.out.println(MSTResultList[i].parent.label+"-"+MSTResultList[i].source.label +" weight : "+MSTResultList[i].weight );
        
        }
    }
    /**
     * 
     * @return cost of MST
     */
    public int getCost() {
        for(int i=0; i<MSTResultList.length;i++){
            cost+=MSTResultList[i].weight;
        }
        return cost;

    }
    }




