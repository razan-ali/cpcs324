
package cpcs324_project1_phase1;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * prints the main menu , takes the user choice, call the algorithm  and print results
 * @author razanali, tahani, asma 
 */
public class Application {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        
        Scanner input = new Scanner(System.in);//scanner to read user choices
        
        //intilize variables
        int verticesNo = 0; //number of vertices 
        int edgesNo = 0;    //number of edges 
        int userChoice = 0; //choice of algorithms 
        int caseNumber =0;  //choice of casses 
        //----------------------
        // print menu and ask user to choice 
        System.out.println("-----------------Test and compare diffrent Minimum Spanning Tree Algorithms---------------");
        System.out.println("\t1-Kruskal's Algorithm & Prim's Algorithm (based on Priority Queue).");
        System.out.println("\t2-Prim's Algorithm (based on Priority Queue) & Prim's Algorithm(based on Min Heap).");
        // ensure that user enter 1 or 2 
        while (userChoice != 1 && userChoice!= 2){
            System.out.print(">Enter your choice: ");//ask user to enter a number 
            userChoice =  input.nextInt();//read user  input 
          
        }//end of while loop 
        //printthe menue of casses
        System.out.println("\tCases (where n represnt # of vertices and m represent # of edges):");
        System.out.println("1-  n =  1,000  , m =   10,000");
        System.out.println("2-  n =  1,000  , m =   15,000");
        System.out.println("3-  n =  1,000  , m =   25,000");
        System.out.println("4-  n =  5,000  , m =   15,000");
        System.out.println("5-  n =  5,000  , m =   25,000");
        System.out.println("6-  n = 10,000  , m =   15,000");
        System.out.println("7-  n = 10,000  , m =   25,000");
        System.out.println("8-  n = 20,000  , m =  200,000");
        System.out.println("9-  n = 20,000  , m =  300,000");
        System.out.println("10- n = 50,000  , m =1,000,000");
        
        //let user choose the case
        //ensure that user enter a number between 0 and 10
        while(caseNumber<=0 || caseNumber>10){
            System.out.print(">Enter your choice: ");//aske user to enter a number 
            caseNumber=input.nextInt();//read user input 
        }//end of while loop
        
        switch(caseNumber){
            case 1:
                verticesNo = 1000;
                edgesNo = 10000;
                break;
            case 2:
                verticesNo = 1000;
                edgesNo = 15000;
                break;
            case 3:
                verticesNo = 1000;
                edgesNo = 25000;
                break;
            case 4:
                verticesNo = 5000;
                edgesNo = 15000;
                break;
            case 5:
                verticesNo = 5000;
                edgesNo = 25000;
                break;
            case 6:
                verticesNo = 10000;
                edgesNo = 15000;
                break;
            case 7:
                verticesNo = 10000;
                edgesNo = 25000;
                break;
            case 8:
                verticesNo = 20000;
                edgesNo = 200000;
                break;
            case 9:
                verticesNo = 20000;
                edgesNo = 300000;
                break;
            case 10:
                verticesNo = 50000;
                edgesNo = 1000000;
                break;
        }//end of switvh 
        System.out.print("is the graph directed(yes/no): ");
        boolean digraphBoolean = false;//intiate the varible with false 
        String digraph = input.next();//take user input 
        if(digraph.equalsIgnoreCase("yes"))//if user enter yes   
            digraphBoolean=true;//change the value of digraph to ture < otherwise it will remain false 
        //------------------------------------------------------------
        Graph newGhraph = new Graph(verticesNo,edgesNo,digraphBoolean); //create new graph object 
        
        newGhraph.makeGraph();//create  graph
        if(userChoice==1){
            KruskalAlg kruskalObj = new KruskalAlg();//create new object 
            double KruskalStartTime = System.currentTimeMillis();//start time of kruskal 
            kruskalObj.Kurskal(newGhraph);//send graph to that kruskal method to start finding the MST
            double KruskalFinishTime = System.currentTimeMillis();//start time of kruskal 
            double totalTimeKruskal = KruskalFinishTime - KruskalStartTime;//find the time taken by kruskal algorithm 
            System.out.println("_____________________________________________________________");
            System.out.println("Minimum Spanning Tree cost: "+kruskalObj.getCost());
            System.out.println("Total runtime of kruskal Algorithm: "+ totalTimeKruskal);
            
            System.out.println("");
            PQprimAlg pqPrimObj = new PQprimAlg();
            double PrimStartTime = System.currentTimeMillis();//start time of primPQ 
            pqPrimObj.PrimPQ(newGhraph);//send graph to that primPQ method to start finding the MST
            double PrimFinishtime = System.currentTimeMillis();//start time of primPQ 
            double totalTimePrim = PrimFinishtime - PrimStartTime;//find the time taken by primPQ algorithm 
            System.out.println("Minimum Spanning Tree cost: "+pqPrimObj.getCost());
            System.out.println("Total runtime of Prim Algorithm(based on Priority Queue): "+ totalTimePrim);
           
        
        
        }
        //---------------------------------------------------------------------
        else if(userChoice==2){
            MHPrimAlg MHObject = new MHPrimAlg(verticesNo);//create new object 
            double minHeapStartTime = System.currentTimeMillis();//start time of minHeap 
            MHObject.PrimMH(newGhraph);//call minheap method 
            double minHeapFinishTime = System.currentTimeMillis();//end time of minHeap
            double totalTimeMH = minHeapFinishTime - minHeapStartTime;//find the time taken by minHeap algorithm 
            //print results 
            System.out.println("_____________________________________________________________");
            System.out.println("Prim's Algorithm(using Min Heap):");
            System.out.println("Minimum Spanning Tree cost: "+MHObject.getCost());
            System.out.println("Total runtime of Prim Algorithm(based on Min Heap): "+ totalTimeMH);
            
            System.out.println("");
            PQprimAlg pqPrimObj = new PQprimAlg();
            double PrimStartTime = System.currentTimeMillis();//start time of primPQ
            pqPrimObj.PrimPQ(newGhraph);//send graph to that prim method to start finding the MST
            double PrimFinishtime = System.currentTimeMillis();//start time of primPQ 
            double totalTimePrim = PrimFinishtime - PrimStartTime;//find the time taken by primPQ algorithm
             //print results 
            System.out.println("Prim's Algorithm(using Priority Queue):");
            System.out.println("Minimum Spanning Tree cost: "+pqPrimObj.getCost());
            System.out.println("Total runtime of Prim Algorithm(based on Priority Queue): "+ totalTimePrim);
            
        
        }
         
       
    
    }
   
 }
    

